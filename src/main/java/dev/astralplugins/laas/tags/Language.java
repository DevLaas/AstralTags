package dev.astralplugins.laas.tags;

public class Language {

    public static String group$setado = Main.getInstance().getConfig().getString("mensagens.sucess.grupo-setado").replace("&", "§");
    public static String group$alterado = Main.getInstance().getConfig().getString("mensagens.sucess.grupo-alterado").replace("&", "§");
    public static String group$kick = Main.getInstance().getConfig().getString("mensagens.sucess.grupo-kick").replace("&", "§");

    public static String group$command = Main.getInstance().getConfig().getString("config.command");
    public static String config$title = Main.getInstance().getConfig().getString("config.title-grupo").replace("&", "§");
    public static String config$subtitle = Main.getInstance().getConfig().getString("config.subtitle-grupo").replace("&", "§");

    public static boolean ativar$sound = Main.getInstance().getConfig().getBoolean("config.ativar-sound");
    public static boolean ativar$titulos = Main.getInstance().getConfig().getBoolean("config.ativar-titulos");
    public static boolean ativar$kick = Main.getInstance().getConfig().getBoolean("config.ativar-kick");

    public static String jogador$nao$encontrado = Main.getInstance().getConfig().getString("mensagens.error.jogador-nao-encontrado").replace("&", "§");
    public static String grupo$nao$encontrado = Main.getInstance().getConfig().getString("mensagens.error.grupo-nao-encontrado").replace("&", "§");
    public static String grupo$nao$informado = Main.getInstance().getConfig().getString("mensagens.error.grupo-nao-informado").replace("&", "§");
    public static String sem$permissao = Main.getInstance().getConfig().getString("mensagens.error.sem-permissao").replace("&", "§");
}
