package org.op65n.chat.registry;

import org.jetbrains.annotations.NotNull;
import org.op65n.chat.ChaTPlugin;

public interface Registerable {

    void enable(final @NotNull ChaTPlugin plugin);

}
