package dev.astralplugins.laas.tags.api;

import com.google.common.base.Preconditions;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

public class Title {

    @Deprecated
    public static boolean DEBUG;
    private JSONObject title, subtitle;
    private int fadeIn, fadeOut, stay;

    public Title(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        this.title = convert(title);
        this.subtitle = convert(subtitle);
        this.fadeIn = fadeIn;
        this.fadeOut = fadeOut;
        this.stay = stay;
    }

    public Title(JSONObject title, JSONObject subtitle, int fadeIn, int fadeOut, int stay) {
        this.title = title;
        this.subtitle = subtitle;
        this.fadeIn = fadeIn;
        this.fadeOut = fadeOut;
        this.stay = stay;
    }

    static JSONObject convert(String text) {
        JSONObject json = new JSONObject();
        json.put("text", text);
        return json;
    }

    public void send(Player player) {
        Preconditions.checkNotNull(player);
        try {
            Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
            Object playerConnection = entityPlayer.getClass().getField("playerConnection").get(entityPlayer);
            Class<?> clsPacketPlayOutTitle = TitleManager.MINECRAFT.getClass("PacketPlayOutTitle");
            Class<?> clsPacket = TitleManager.MINECRAFT.getClass("Packet");
            Class<?> clsIChatBaseComponent = TitleManager.MINECRAFT.getClass("IChatBaseComponent");
            Class<?> clsChatSerializer = TitleManager.MINECRAFT.getClass("IChatBaseComponent$ChatSerializer");
            Class<?> clsEnumTitleAction = TitleManager.MINECRAFT.getClass("PacketPlayOutTitle$EnumTitleAction");
            Object timesPacket = clsPacketPlayOutTitle.getConstructor(int.class, int.class, int.class).newInstance(fadeIn, stay, fadeOut);
            playerConnection.getClass().getMethod("sendPacket", clsPacket).invoke(playerConnection, timesPacket);
            if (title != null && !title.isEmpty()) {
                Object titleComponent = clsChatSerializer.getMethod("a", String.class).invoke(null, title.toString());
                Object titlePacket = clsPacketPlayOutTitle.getConstructor(clsEnumTitleAction, clsIChatBaseComponent).newInstance(clsEnumTitleAction.getField("TITLE").get(null), titleComponent);
                playerConnection.getClass().getMethod("sendPacket", clsPacket).invoke(playerConnection, titlePacket);
            }
            if (subtitle != null && !subtitle.isEmpty()) {
                Object subtitleComponent = clsChatSerializer.getMethod("a", String.class).invoke(null, subtitle.toString());
                Object subtitlePacket = clsPacketPlayOutTitle.getConstructor(clsEnumTitleAction, clsIChatBaseComponent).newInstance(clsEnumTitleAction.getField("SUBTITLE").get(null), subtitleComponent);
                playerConnection.getClass().getMethod("sendPacket", clsPacket).invoke(playerConnection, subtitlePacket);
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public void sendToAll() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            send(player);
        }
    }

    public JSONObject getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = convert(title);
    }

    public void setTitle(JSONObject title) {
        this.title = title;
    }

    public JSONObject getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = convert(subtitle);
    }

    public void setSubtitle(JSONObject subtitle) {
        this.subtitle = subtitle;
    }

    public int getFadeIn() {
        return fadeIn;
    }

    public void setFadeIn(int fadeIn) {
        this.fadeIn = fadeIn;
    }

    public int getFadeOut() {
        return fadeOut;
    }

    public void setFadeOut(int fadeOut) {
        this.fadeOut = fadeOut;
    }

    public int getStay() {
        return stay;
    }

    public void setStay(int stay) {
        this.stay = stay;
    }

}