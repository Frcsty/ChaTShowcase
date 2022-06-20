package org.op65n.chat;

import org.bukkit.plugin.java.JavaPlugin;
import org.op65n.chat.command.CommandRegisterable;
import org.op65n.chat.listener.ListenerRegisterable;
import org.op65n.chat.registry.Registerable;
import org.op65n.chat.runnable.NotificationRegisterable;

import java.util.Set;

public final class ChaTPlugin extends JavaPlugin {

    private static final Set<Registerable> REGISTERABLES = Set.of(
            new CommandRegisterable(), new ListenerRegisterable(), new NotificationRegisterable()
    );

    @Override
    public void onEnable() {
        REGISTERABLES.forEach(it -> it.enable(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
