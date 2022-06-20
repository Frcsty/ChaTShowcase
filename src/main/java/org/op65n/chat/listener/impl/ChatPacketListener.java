package org.op65n.chat.listener.impl;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.op65n.chat.ChaTPlugin;
import org.op65n.chat.data.DataRegistry;

public final class ChatPacketListener {

    private final ChaTPlugin plugin;

    public ChatPacketListener(final @NotNull ChaTPlugin plugin) {
        this.plugin = plugin;
    }

    public PacketAdapter call() {
        return new PacketAdapter(this.plugin, ListenerPriority.NORMAL, PacketType.Play.Client.CHAT) {
            @Override
            public void onPacketReceiving(final @NotNull PacketEvent event) {
                final PacketContainer packet = event.getPacket();
                if (packet.getType() != PacketType.Play.Client.CHAT) {
                    return;
                }

                final String rawContent = packet.getStrings().read(0);
                if (rawContent != null && rawContent.equalsIgnoreCase("chat toggle")) {
                    return;
                }

                final Player player = event.getPlayer();
                if (DataRegistry.status(player)) {
                    return;
                }

                event.setCancelled(true);
            }
        };
    }

}
