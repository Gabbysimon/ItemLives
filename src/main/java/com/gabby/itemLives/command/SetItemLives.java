package com.gabby.itemLives.command;

import com.gabby.itemLives.ItemLives;
import com.gabby.itemLives.ItemLivesUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;


public class SetItemLives implements CommandExecutor {

    private Plugin plugin;

    public SetItemLives(Plugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (!sender.hasPermission("itemLives.admin")) {

            sender.sendMessage(Component.text("You do not have permission to execute this command!").color(NamedTextColor.RED));
            return true;
        }

        if (args.length != 2) {
            sender.sendMessage(ChatColor.RED + "Invalid arguments! /setlives <lives> <player>");
            return true;
        }

        final Player target = Bukkit.getPlayer(args[1]);
        final ItemStack item = target.getInventory().getItemInMainHand();

        int lives = -1;

        try {
            lives = Integer.parseInt(args [0]);}
        catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.RED + "Please enter a valid number!");
            return true;
        }
        if (lives <=0) {

            sender.sendMessage(ChatColor.RED + "You must enter a number greater than 0.");
            return true;
        }
        if (lives > 100) {

            sender.sendMessage(ChatColor.RED + "You must enter a number less than 100.");
            return true;
        }

        int result = ItemLivesUtils.setLives(item, lives, plugin);
        int repairs = ItemLivesUtils.getRepaired(item, plugin);
        int setRepairs = ItemLivesUtils.setRepaired(item, repairs + 1, plugin);
        if (result == -1) {

            sender.sendMessage(ChatColor.RED + "This item is invalid!");
        }
        else {
            sender.sendMessage(ChatColor.GREEN + "Successfully set +" + lives + " lives onto your item!");

        }
        return true;

    }
}
