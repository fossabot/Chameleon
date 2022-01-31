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

package dev.hypera.chameleon.platforms.bungeecord.wrappers;

import dev.hypera.chameleon.core.wrappers.AudienceProvider;
import java.util.UUID;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.platform.bungeecord.BungeeAudiences;
import net.kyori.adventure.text.flattener.ComponentFlattener;
import org.jetbrains.annotations.NotNull;

public class BungeeCordAudienceProvider extends AudienceProvider {

	private final @NotNull BungeeAudiences provider;

	public BungeeCordAudienceProvider(@NotNull BungeeAudiences provider) {
		this.provider = provider;
	}


	@Override
	public @NotNull Audience all() {
		return provider.all();
	}

	@Override
	public @NotNull Audience console() {
		return provider.console();
	}

	@Override
	public @NotNull Audience player(@NotNull UUID id) {
		return provider.player(id);
	}

	@Override
	public @NotNull Audience permission(@NotNull Key permission) {
		return provider.permission(permission);
	}

	@Override
	public @NotNull Audience permission(@NotNull String permission) {
		return provider.permission(permission);
	}

	@Override
	public @NotNull Audience world(@NotNull Key world) {
		return provider.world(world);
	}

	@Override
	public @NotNull Audience server(@NotNull String serverName) {
		return provider.server(serverName);
	}

	@Override
	public @NotNull ComponentFlattener flattener() {
		return provider.flattener();
	}

	@Override
	public void close() {
		provider.close();
	}

}
