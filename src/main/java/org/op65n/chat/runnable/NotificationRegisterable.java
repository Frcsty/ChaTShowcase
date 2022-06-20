package org.op65n.chat.runnable;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.jetbrains.annotations.NotNull;
import org.op65n.chat.ChaTPlugin;
import org.op65n.chat.registry.Registerable;
import org.op65n.chat.runnable.impl.NotificationRunnable;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class NotificationRegisterable implements Registerable {

    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1, new ThreadFactoryBuilder().setNameFormat("notification").build());

    @Override
    public void enable(final @NotNull ChaTPlugin plugin) {
        this.scheduledExecutorService.scheduleAtFixedRate(
                new NotificationRunnable(), 0L, 5L, TimeUnit.SECONDS
        );
    }

}
