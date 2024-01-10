package dev.astralplugins.laas.tags.cmd;

import dev.astralplugins.laas.tags.Language;
import dev.astralplugins.laas.tags.Main;
import dev.astralplugins.laas.tags.api.Title;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SetgroupCommand implements CommandExecutor {

    private List<String> groups = Main.getInstance().getConfig().getStringList("config.grupos-ativos");

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("astral.cmd.setgroup")) {
            player.sendMessage(Language.sem$permissao);
            return true;
        }

        if (args.length == 1) {
            Player target = player.getServer().getPlayer(args[0]);
            if (target == null) {
                player.sendMessage(Language.jogador$nao$encontrado);
            } else {
                player.sendMessage(Language.grupo$nao$informado);
                player.sendMessage("§eTodos os grupos disponíveis: §f" + groups);
            }
            return true;
        }

        if (args.length != 2) {
            player.sendMessage("§cUso correto para setar grupo: §5§n/setargrupo {player} {grupo}§f.");
            return true;
        }

        Player target = player.getServer().getPlayer(args[0]);
        if (target == null) {
            player.sendMessage(Language.jogador$nao$encontrado);
            player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 0.5f, 2.0f);
            return true;
        }

        String group = args[1];
        if (!groups.contains(group)) {
            player.sendMessage(Language.grupo$nao$encontrado);
            player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 0.5f, 2.0f);
            return true;
        }

        this.setTag(target, group);
        player.playSound(player.getLocation(), Sound.SUCCESSFUL_HIT, 0.5f, 2.0f);
        player.sendMessage(Language.group$setado.replace("{target}", target.getName()).replace("{group}", group.toUpperCase()));
        player.sendMessage(Language.group$alterado.replace("{target}", target.getName()).replace("{group}", group.toUpperCase()));

        if (!group.equalsIgnoreCase("default") || group.equalsIgnoreCase("membro")) {
            if (Language.ativar$sound == true)
                player.playSound(player.getLocation(), Sound.ENDERDRAGON_GROWL, 1.0f, 1.0f);

            Title title = new Title(Language.config$title.replace("{target}", target.getName()), Language.config$subtitle.replace("{group}", group.toUpperCase()), 20, 20, 20);
            for (Player players : Bukkit.getOnlinePlayers()) {
                if (Language.ativar$titulos == true) title.send(players);
            }
        }
        return true;
    }

    public void setTag(Player target, String group) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Language.group$command.replace("{target}", target.getName()).replace("{group}", group.toUpperCase()));
        if (Language.ativar$kick == true) target.kickPlayer(Language.group$kick.replace("{group}", group.toUpperCase()));
    }
}
