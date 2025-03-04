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
package dev.hypera.chameleon.platforms.spigot.events;

import dev.hypera.chameleon.core.events.impl.common.UserChatEvent;
import dev.hypera.chameleon.core.events.impl.common.UserConnectEvent;
import dev.hypera.chameleon.core.events.impl.common.UserDisconnectEvent;
import dev.hypera.chameleon.core.users.platforms.ServerUser;
import dev.hypera.chameleon.platforms.spigot.SpigotChameleon;
import dev.hypera.chameleon.platforms.spigot.user.SpigotUser;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Spigot listener
 */
public class SpigotListener implements Listener {

	private final @NotNull SpigotChameleon chameleon;

	public SpigotListener(@NotNull SpigotChameleon chameleon) {
		this.chameleon = chameleon;
	}


	@EventHandler
	public void onPlayerJoinEvent(@NotNull PlayerJoinEvent event) {
		chameleon.getEventManager().dispatch(new UserConnectEvent(wrap(event.getPlayer())));
	}

	@EventHandler
	public void onAsyncPlayerChatEvent(@NotNull AsyncPlayerChatEvent event) {
		if (!chameleon.getEventManager().dispatch(new UserChatEvent(wrap(event.getPlayer()), event.getMessage()))) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerQuitEvent(@NotNull PlayerQuitEvent event) {
		chameleon.getEventManager().dispatch(new UserDisconnectEvent(wrap(event.getPlayer())));
	}


	private @NotNull ServerUser wrap(@NotNull Player player) {
		return new SpigotUser(chameleon, player);
	}

}
