/*
 * Chameleon - Cross-platform Minecraft plugin creation library
 *  Copyright (c) 2021 SLLCoding <luisjk266@gmail.com>
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

package dev.hypera.chameleon.spigot;

import dev.hypera.chameleon.core.Chameleon;
import dev.hypera.chameleon.core.Plugin;
import dev.hypera.chameleon.core.commands.Command;
import dev.hypera.chameleon.core.users.ChatUser;
import dev.hypera.chameleon.spigot.commands.SpigotCommand;
import dev.hypera.chameleon.spigot.users.ChameleonCommandSender;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

public class SpigotChameleon extends Chameleon {

    private final @NotNull JavaPlugin spigotPlugin;
    private final @NotNull BukkitAudiences adventure;

    public SpigotChameleon(@NotNull Class<? extends Plugin> pluginClass, @NotNull JavaPlugin spigotPlugin) throws InstantiationException {
        super(pluginClass);
        this.spigotPlugin = spigotPlugin;
        this.adventure = BukkitAudiences.create(spigotPlugin);
    }

    public @NotNull JavaPlugin getPlugin() {
        return spigotPlugin;
    }

    public @NotNull BukkitAudiences getAdventure() {
        return adventure;
    }

    @Override
    public void registerCommand(@NotNull Command command) {
        try {
            Field commandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            commandMap.setAccessible(true);
            CommandMap map = (CommandMap) commandMap.get(Bukkit.getServer());
            SpigotCommand spigotCommand = new SpigotCommand(this, command);
            map.register(command.getName(), spigotPlugin.getName(), spigotCommand);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public @NotNull ChatUser getConsoleSender() {
        return new ChameleonCommandSender(this, Bukkit.getConsoleSender());
    }

}
