package org.op65n.chat.command;

import me.mattstudios.mf.base.CommandManager;
import org.jetbrains.annotations.NotNull;
import org.op65n.chat.ChaTPlugin;
import org.op65n.chat.command.impl.ChatToggleCommand;
import org.op65n.chat.registry.Registerable;

public final class CommandRegisterable implements Registerable {

    @Override
    public void enable(final @NotNull ChaTPlugin plugin) {
        final CommandManager commandManager = new CommandManager(plugin);

        commandManager.register(
                new ChatToggleCommand()
        );
    }

}
