package org.op65n.chat.data;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public final class DataRegistry {

    private static final Map<UUID, Boolean> CHAT_STATUS = new HashMap<>();

    /**
     * Updates the current status for the player
     *
     * @param player The sending player
     * @return New status of their chat visibility
     */
    public static boolean update(final @NotNull Player player) {
        final UUID playerIdentifier = player.getUniqueId();
        final boolean current = CHAT_STATUS.getOrDefault(playerIdentifier, true);

        CHAT_STATUS.put(playerIdentifier, !current);

        return !current;
    }

    /**
     * Returns the current status for the player's chat visibility
     *
     * @param player The sending player
     * @return Status of their chat visibility
     */
    public static boolean status(final @NotNull Player player) {
        return CHAT_STATUS.getOrDefault(player.getUniqueId(), true);
    }

    /**
     * Returns a set of notification recipients
     *
     * @return Set of recipients
     */
    public static Set<Player> notificationReceivers() {
        final Set<Player> result = new HashSet<>();

        for (final UUID identifier : CHAT_STATUS.keySet()) {
            final boolean value = CHAT_STATUS.get(identifier);

            if (!value) result.add(Bukkit.getPlayer(identifier));
        }

        return result;
    }

}
