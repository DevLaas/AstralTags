package dev.astralplugins.laas.tags;

import dev.astralplugins.laas.tags.cmd.SetgroupCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    // Creditos: Astral Plugins - Laas
    // Discord (devlaas)

    public static Main instance;

    public static Main getInstance() {
        return instance;
    }

    public Main() {
        instance = this;
    }

    @Override
    public void onLoad() {
        Bukkit.getConsoleSender().sendMessage("§5Astral Tags §7: §fCarregando recursos do sistema.");
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Main.getInstance().getCommand("setgroup").setExecutor(new SetgroupCommand());
        Bukkit.getConsoleSender().sendMessage("§5Astral Tags §7: §fTodos os recursos foram §aessencialmente §fcarregados com §a§n§lSUCESSO.");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§5Astral Tags §7: §fTodos os recursos foram §cdescarregados §fcom §a§n§lSUCESSO.");
    }
}