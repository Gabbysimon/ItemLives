package com.gabby.itemLives.command;

import com.gabby.itemLives.ItemLivesUtils;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;


public class SetItemLives implements CommandExecutor {

    private Plugin plugin;

    public SetItemLives(Plugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can execute this command.");
            return true;
        }

        if (!sender.hasPermission("itemLives.admin")) {

            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Invalid arguments!");
            return true;
        }

        final Player player = (Player) sender;

        int lives = -1;

        try {
            lives = Integer.parseInt(args [0]);}
        catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.RED + "Please enter a valid number!");
        }
        if (lives <=0) {

            player.sendMessage(ChatColor.RED + "You must enter a number greater than 0.");
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
            return true;
        }
        if (lives > 100) {

            player.sendMessage(ChatColor.RED + "You must enter a number less than 100.");
            return true;
        }

        int result = ItemLivesUtils.setLives(player, lives, plugin);
        if (result == -1) {

            player.sendMessage(ChatColor.RED + "This item is invalid!");
        }
        else {
            player.sendMessage(ChatColor.GREEN + "Successfully set +" + lives + " lives onto your item!");

        }
        return true;

    }
}
