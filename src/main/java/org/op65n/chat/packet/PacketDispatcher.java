package org.op65n.chat.packet;

import org.bukkit.entity.Player;
import org.op65n.chat.packet.util.ReflectionUtils;

import java.util.Arrays;

public final class PacketDispatcher {

    public static void sendMessage(final Player player, final String... contents) {
        Arrays.stream(contents).forEach(it -> {
            try {
                final Object content = ReflectionUtils.createIChatBaseComponent(it);
                final Object messagePacket = ReflectionUtils.createChatPacket(content);

                ReflectionUtils.sendPacket(player, messagePacket);
            } catch (final Exception exception) {
                throw new RuntimeException(exception);
            }
        });
    }

}
