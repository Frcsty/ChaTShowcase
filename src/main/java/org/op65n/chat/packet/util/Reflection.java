package org.op65n.chat.packet.util;

import org.bukkit.Bukkit;

public final class Reflection {

    private static final String VERSION = Bukkit.getServer().getClass().getPackage().getName().split(".")[3];

    public static Class<?> getNMSClass(final String className) throws ClassNotFoundException {
        return Class.forName("net.minecraft.server." + VERSION + "." + className);
    }

    public static Class<?> getCraftBukkitClass(final String className) throws ClassNotFoundException {
        return Class.forName("org.bukkit.craftbukkit." + VERSION + "." + className);
    }

}
