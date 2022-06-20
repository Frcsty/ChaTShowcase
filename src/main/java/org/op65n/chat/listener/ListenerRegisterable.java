package org.op65n.chat.listener;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.jetbrains.annotations.NotNull;
import org.op65n.chat.ChaTPlugin;
import org.op65n.chat.listener.impl.ChatPacketListener;
import org.op65n.chat.registry.Registerable;

public final class ListenerRegisterable implements Registerable {

    @Override
    public void enable(final @NotNull ChaTPlugin plugin) {
        final ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();

        protocolManager.addPacketListener(
                new ChatPacketListener(plugin).call()
        );
    }

}
