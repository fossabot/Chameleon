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
package dev.hypera.chameleon.platforms.spigot.managers;

import dev.hypera.chameleon.core.managers.PluginManager;
import dev.hypera.chameleon.core.platform.objects.PlatformPlugin;
import dev.hypera.chameleon.platforms.spigot.platform.objects.SpigotPlugin;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

/**
 * Spigot plugin manager
 */
public final class SpigotPluginManager extends PluginManager {

	@Override
	public @NotNull Set<PlatformPlugin> getPlugins() {
		return Arrays.stream(Bukkit.getPluginManager().getPlugins()).map(SpigotPlugin::new).collect(Collectors.toSet());
	}

	@Override
	public @NotNull Optional<PlatformPlugin> getPlugin(@NotNull String name) {
		return Optional.ofNullable(Bukkit.getPluginManager().getPlugin(name)).map(SpigotPlugin::new);
	}

	@Override
	public boolean isPluginEnabled(@NotNull String name) {
		return Bukkit.getPluginManager().isPluginEnabled(name);
	}

}
