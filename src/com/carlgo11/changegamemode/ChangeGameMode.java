package com.carlgo11.changegamemode;

import com.carlgo11.changegamemode.metrics.Metrics;
import com.carlgo11.changegamemode.updater.Updater;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ChangeGameMode extends JavaPlugin {

    public static ChangeGameMode plugin;
    public final Logger logger = Logger.getLogger("Minecraft");

    @Override
    public void onEnable() {
        this.logger.info(getDescription().getName() + "is enabled!");
        checkConfig();
    }

    public void checkConfig() {
        File config = new File(this.getDataFolder(), "config.yml");
        if (!config.exists()) {
            this.saveDefaultConfig();
            System.out.println("[" + getDescription().getName() + "] No config.yml detected, config.yml created");
        }
    }

    public void checkMetrics() {
        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException e) {
            System.out.println("[" + getDescription().getName() + "] Error Submitting stats!");
        }
    }

    public void checkUpdate() {
        if (getConfig().getBoolean("auto-update") == true) {
            Updater updater = new Updater(this, "simpleautomessage/", this.getFile(), Updater.UpdateType.DEFAULT, true);
        } else {
        }
    }

    @Override
    public void onDisable() {
        this.logger.info(getDescription().getName() + "is now disabled!");
    }

    public boolean onCommandonCommand(CommandSender sender, Command cmd, String string, String[] args) {
        Player player = ((Player) sender);
        //if ( instanceof Player) {     Not working!

        if (cmd.getName().equalsIgnoreCase("gm")) {
            if (args.length == 0) {
                if (player.getGameMode().equals(GameMode.CREATIVE)) {
                    player.setGameMode(GameMode.SURVIVAL);
                    sender.sendMessage(ChatColor.YELLOW
                            + "You are now in Creative mode!");
                }
                if (player.getGameMode() == GameMode.SURVIVAL) {
                    sender.sendMessage(ChatColor.YELLOW
                            + "You are now in Survival mode!");
                }
            }
            if (args.length == 1) {
                if (!(args[0].equalsIgnoreCase("survival"))) {
                    if (!(args[0].equalsIgnoreCase("Creative"))) {
                        if (!(args[0].equalsIgnoreCase("Adventure"))) {
                            sender.sendMessage(ChatColor.GRAY + "-  /"
                                    + ChatColor.RED + "Gm"
                                    + ChatColor.WHITE
                                    + "   Creative/Survival/Adventure");
                        }
                    }
                }
                if (args[0].equalsIgnoreCase("survival")) {
                    if (!(player.getGameMode() == GameMode.SURVIVAL)) {
                        player.setGameMode(GameMode.SURVIVAL);
                        sender.sendMessage(ChatColor.YELLOW
                                + "You are now in Survival mode!");
                    }
                    if (player.getGameMode() == GameMode.SURVIVAL) {
                        sender.sendMessage(ChatColor.RED
                                + "Error: You are already in Survival mode!");
                    }
                }
                if (args[0].equalsIgnoreCase("creative")) {
                    if (!(player.getGameMode() == GameMode.CREATIVE)) {
                        player.setGameMode(GameMode.CREATIVE);
                        sender.sendMessage(ChatColor.YELLOW
                                + "You are now in Creative mode!");
                    }
                    if (player.getGameMode() == GameMode.SURVIVAL) {
                        sender.sendMessage(ChatColor.RED
                                + "Error: You are already in Creative mode!");
                    }
                }
                if (args[0].equalsIgnoreCase("adventure")) {
                    if (!(player.getGameMode() == GameMode.ADVENTURE)) {
                        player.setGameMode(GameMode.ADVENTURE);
                        sender.sendMessage(ChatColor.YELLOW
                                + "You are now in Adventure mode!");
                    }
                    if (player.getGameMode() == GameMode.SURVIVAL) {
                        sender.sendMessage(ChatColor.RED
                                + "Error: You arer already in Adventure mode!");
                    }
                }
            }
            if (args.length > 1) {
                sender.sendMessage(ChatColor.GRAY + "-  /" + ChatColor.RED
                        + "Gm" + ChatColor.WHITE
                        + "   Creative/Survival/Adventure");
            }
            // Creator command
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("data")) {
                    if (sender.getName().equalsIgnoreCase("Carlgo11")) {
                        sender.sendMessage(ChatColor.GREEN
                                + "SimpleGameMode Version: "
                                + getDescription().getVersion() + ".");
                    }
                }
            }
        }
        //}
        return true;

    }
}