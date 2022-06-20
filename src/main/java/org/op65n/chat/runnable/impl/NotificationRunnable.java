package org.op65n.chat.runnable.impl;

import org.op65n.chat.data.DataRegistry;
import org.op65n.chat.packet.PacketDispatcher;

public final class NotificationRunnable implements Runnable {

    @Override
    public void run() {
        DataRegistry.notificationReceivers().forEach(it -> PacketDispatcher.sendMessage(it, "Your chat is disabled!"));
    }

}
