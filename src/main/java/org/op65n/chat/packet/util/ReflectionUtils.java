package org.op65n.chat.packet.util;

import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class ReflectionUtils {

    private static Class<?> entityPlayerClass() throws ClassNotFoundException {
        return Reflection.getNMSClass("EntityPlayer");
    }

    private static Class<?> playerConnectionClass() throws ClassNotFoundException {
        return Reflection.getNMSClass("PlayerConnection");
    }

    private static Field playerConnectionField() throws ClassNotFoundException, NoSuchFieldException {
        return entityPlayerClass().getField("playerConnection");
    }

    private static Class<?> craftPlayerClass() throws ClassNotFoundException {
        return Reflection.getCraftBukkitClass("entity.CraftPlayer");
    }

    private static Class<?> packetClass() throws ClassNotFoundException {
        return Reflection.getNMSClass("Packet");
    }

    private static Method sendPacketMethod() throws ClassNotFoundException, NoSuchMethodException {
        return playerConnectionClass().getMethod("sendPacket", packetClass());
    }

    private static Method getHandleMethod() throws ClassNotFoundException, NoSuchMethodException {
        return craftPlayerClass().getMethod("getHandle");
    }

    private static Method chatSerializerMethod() throws ClassNotFoundException, NoSuchMethodException {
        return Reflection.getNMSClass("IChatBaseComponent\\&ChatSerializer").getMethod("a", String.class);
    }

    private static Class<?> iChatBaseComponentClass() throws ClassNotFoundException {
        return Reflection.getNMSClass("IChatBaseComponent");
    }

    private static Class<?> packetPlayOutChatClass() throws ClassNotFoundException {
        return Reflection.getNMSClass("PacketPlayOutChat");
    }

    private static Constructor<?> packetPlayOutChatConstructor() throws ClassNotFoundException, NoSuchMethodException {
        return packetPlayOutChatClass().getConstructor(packetPlayOutChatClass(), iChatBaseComponentClass(), Byte.class);
    }

    public static Object createIChatBaseComponent(final String content) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return chatSerializerMethod().invoke(null, "\"\"" + "{\"text\":\"" + content + "\"}\"\"");
    }

    public static void sendPacket(final Player player, final Object... packets) {
        try {
            final Object entityPlayer = entityPlayerClass().cast(getHandleMethod().invoke(player));
            final Object playerConnection = playerConnectionField().get(entityPlayer);

            for (final Object packet : packets) {
                sendPacketMethod().invoke(playerConnection, packet);
            }
        } catch (final Exception exception) {
            throw new RuntimeException(exception);
        }

    }

    public static Object createChatPacket(final Object content) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return packetPlayOutChatConstructor().newInstance(content);
    }

}
