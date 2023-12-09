package top.lqsnow.blockracing.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import top.lqsnow.blockracing.utils.ConsoleCommandHandler;

import static top.lqsnow.blockracing.listeners.EventListener.*;
import static top.lqsnow.blockracing.managers.GameManager.blueTeamPlayer;
import static top.lqsnow.blockracing.managers.GameManager.redTeamPlayer;
import static top.lqsnow.blockracing.managers.ScoreboardManager.blue;
import static top.lqsnow.blockracing.managers.ScoreboardManager.red;

public class BlockRacing implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(commandSender instanceof Player)) {
            return false;
        }
        Player player = (Player) commandSender;
        if (args.length == 0) {
            return false;
        }
        if (args[0].equals("join")) {
            if (args.length < 2) {
                return false;
            }
            if (args[1].equals("red")) {
                red.addEntry(player.getName());
                ConsoleCommandHandler.send("tellraw @a {\"text\": \"\\u00a7c" + player.getName() + "加入了红队！\"}");
                redTeamPlayer.add((Player) player);
                redTeamPlayerString.add(player.getName());
                if (blueTeamPlayer.contains(player)) {
                    blueTeamPlayer.remove(player);
                    blueTeamPlayerString.remove(player.getName());
                    blue.removeEntry(player.getName());
                }
            } else if (args[1].equals("blue")) {
                blue.addEntry(player.getName());
                ConsoleCommandHandler.send("tellraw @a {\"text\": \"\\u00a79" + player.getName() + "加入了蓝队！\"}");
                blueTeamPlayer.add((Player) player);
                blueTeamPlayerString.add(player.getName());
                if (redTeamPlayer.contains(player)) {
                    redTeamPlayer.remove(player);
                    redTeamPlayerString.remove(player.getName());
                    red.removeEntry(player.getName());
                }
            }
            return true;
        } else if (args[0].equals("prepare")) {
            if (!prepareList.contains(player)) {
                prepareList.add(player);
                ConsoleCommandHandler.send("tellraw @a \"\u00a7b" + player.getName() + "已准备！\"");
                if (prepareList.size() > 1 && prepareList.size() == Bukkit.getOnlinePlayers().size()) {
                    canStart = true;
                    ConsoleCommandHandler.send("tellraw @a \"\u00a7b所有人都准备好了，可以开始游戏了！\"");
                }
                return true;
            } else {
                ConsoleCommandHandler.send("tellraw @a \"\u00a7c您已经准备过了！\"");
                return true;
            }
        }
        return true;
    }
}
