/*
 * Chameleon Framework - Cross-platform Minecraft plugin framework
 *  Copyright (c) 2021-present The Chameleon Framework Authors.
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */
package dev.hypera.chameleon.core.commands;

import dev.hypera.chameleon.core.commands.annotations.CommandHandler;
import dev.hypera.chameleon.core.commands.annotations.Permission;
import dev.hypera.chameleon.core.commands.annotations.SubCommandHandler;
import dev.hypera.chameleon.core.commands.context.Context;
import dev.hypera.chameleon.core.commands.objects.Condition;
import dev.hypera.chameleon.core.commands.objects.Platform;
import dev.hypera.chameleon.core.exceptions.command.ChameleonCommandException;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Abstract command
 */
public abstract class Command {

	private final @NotNull String name;
	private final @NotNull Set<String> aliases = new HashSet<>();
	private final @NotNull Set<SubCommand> subCommands = new HashSet<>();
	private final @Nullable Permission permission;

	private @NotNull Platform platform = Platform.ALL;
	private @NotNull List<Condition> conditions = new ArrayList<>();
	private @Nullable Component permissionErrorMessage;

	public Command(@NotNull String... names) {
		try {
			if (names.length < 1) {
				if (!getClass().isAnnotationPresent(CommandHandler.class)) {
					throw new IllegalStateException("Classes extending Command must either be annotated with @CommandHandler or provide names in the constructor");
				}

				names = getClass().getAnnotation(CommandHandler.class).value().split("\\|");
			}

			this.name = names[0];
			this.aliases.addAll(
					names.length > 1
							? new HashSet<>(Arrays.asList(Arrays.copyOfRange(names, 1, names.length)))
							: Collections.emptySet()
			);
			this.permission = getClass().isAnnotationPresent(Permission.class) ? getClass().getAnnotation(Permission.class) : null;

			for (Method method : getClass().getDeclaredMethods()) {
				if (method.isAnnotationPresent(SubCommandHandler.class) && method.getParameterCount() == 1 && method.getParameterTypes()[0] == Context.class) {
					subCommands.add(new SubCommand(method.getAnnotation(SubCommandHandler.class).value(), method));
				}
			}
		} catch (Exception ex) {
			throw new ChameleonCommandException("Failed to create command", ex);
		}
	}

	public abstract void execute(@NotNull Context context);
	public @NotNull List<String> tabComplete(@NotNull Context context) {
		return Collections.emptyList();
	}


	public final void executeCommand(@NotNull Context context) {
		if (null != permission && !permission.value().isEmpty() && !context.getSender().hasPermission(Objects.requireNonNull(permission.value()))) {
			if (null != permissionErrorMessage) {
				context.getSender().sendMessage(permissionErrorMessage);
			}

			return;
		}

		if (!conditions.isEmpty()) {
			Optional<Condition> failedCondition = conditions.stream().filter(condition -> !condition.test(context)).findFirst();
			if (failedCondition.isPresent()) {
				failedCondition.get().getErrorMessage().ifPresent(errorMessage -> context.getSender().sendMessage(errorMessage));
				return;
			}
		}

		execute(context);
	}

	public final boolean executeSubCommand(@NotNull Context context, @NotNull String command) {
		Optional<SubCommand> subCommand = subCommands.stream().filter(c -> c.getNames().stream().anyMatch(n -> command.toLowerCase().matches(n))).findFirst();
		if (subCommand.isPresent()) {
			subCommand.get().execute(context, this);
			return false;
		} else {
			return true;
		}
	}


	public final @NotNull String getName() {
		return name;
	}

	public final @NotNull Set<String> getAliases() {
		return aliases;
	}

	public final void addAliases(@NotNull Collection<String> aliases) {
		this.aliases.addAll(aliases);
	}

	@Internal
	public final @NotNull Set<SubCommand> getSubCommands() {
		return subCommands;
	}

	public final @NotNull Optional<Permission> getPermission() {
		return Optional.ofNullable(permission);
	}

	public final @NotNull Platform getPlatform() {
		return platform;
	}

	public final void setPlatform(@NotNull Platform platform) {
		this.platform = platform;
	}

	public final @NotNull List<Condition> getConditions() {
		return conditions;
	}

	public final void setConditions(@NotNull Condition... conditions) {
		this.conditions = Arrays.asList(conditions);
	}

	public final @NotNull Optional<Component> getPermissionErrorMessage() {
		return Optional.ofNullable(permissionErrorMessage);
	}

	public final void setPermissionErrorMessage(@NotNull Component permissionErrorMessage) {
		this.permissionErrorMessage = permissionErrorMessage;
	}

}
