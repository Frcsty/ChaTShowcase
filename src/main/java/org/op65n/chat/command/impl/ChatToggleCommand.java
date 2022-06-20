package org.op65n.chat.command.impl;

import me.mattstudios.mf.annotations.Command;
import me.mattstudios.mf.annotations.SubCommand;
import me.mattstudios.mf.base.CommandBase;
import org.bukkit.entity.Player;
import org.op65n.chat.data.DataRegistry;
import org.op65n.chat.packet.PacketDispatcher;

@Command("chat")
public final class ChatToggleCommand extends CommandBase {

    @SubCommand("toggle")
    public void onChatToggle(final Player player) {
        final boolean result = DataRegistry.update(player);

        PacketDispatcher.sendMessage(player, String.format("You have %s chat", result ? "enabled" : "disabled"));
    }

}
