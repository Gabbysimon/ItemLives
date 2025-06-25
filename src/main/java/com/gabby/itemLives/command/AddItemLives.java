package com.gabby.itemLives.command;

import com.gabby.itemLives.ItemLivesUtils;
import me.clip.placeholderapi.libs.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class AddItemLives implements CommandExecutor {

    private Plugin plugin;

    public AddItemLives(Plugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (!sender.hasPermission("itemLives.admin")) {

            sender.sendMessage(Component.text("You do not have permission to execute this command!").color(NamedTextColor.RED));
            return true;
        }

        if (args.length != 2) {
            sender.sendMessage(ChatColor.RED + "Invalid arguments! /addlives <lives> <player>");
            return true;
        }

        final Player target = Bukkit.getPlayer(args[1]); {

            if (target == null) {
                sender.sendMessage(Component.text("Please specify a valid player!").color(NamedTextColor.RED));
            }
        }

        final ItemStack item = target.getInventory().getItemInMainHand();

        int lives = -1;

        try {
            lives = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            sender.sendMessage(ChatColor.RED + "Please enter a valid number!");
            return true;
        }
        if (lives <= 0) {

            sender.sendMessage(ChatColor.RED + "You must enter a number greater than 0.");
            return true;
        }
        if (lives > 100) {

            sender.sendMessage(ChatColor.RED + "You must enter a number less than 100.");
            return true;
        }


        int currentLives = ItemLivesUtils.getLives(item, plugin);
        if (currentLives == -1) {
            sender.sendMessage(ChatColor.RED + "This item has no lives data!");
            return true;
        }

        int newlives = currentLives + lives;
        sender.sendMessage(Component.text("You have successfully added " + lives + " lives to " + target.getName() + "'s item!").color(NamedTextColor.GREEN));
        ItemLivesUtils.setLives(item, newlives, plugin);
        int repairs = ItemLivesUtils.getRepaired(item, plugin);
        int setRepairs = ItemLivesUtils.setRepaired(item, repairs + 1, plugin);
        return true;

    }



    }
