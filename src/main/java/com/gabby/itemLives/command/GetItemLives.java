package com.gabby.itemLives.command;

import com.gabby.itemLives.ItemLivesUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class GetItemLives implements CommandExecutor {

    private Plugin plugin;

    public GetItemLives(Plugin plugin) {
        this.plugin = plugin;
    }
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can execute this command!");
            return true;
        }

        final Player player = (Player) sender;
        final ItemStack item = player.getInventory().getItemInMainHand();
        int livesremaining = ItemLivesUtils.getLives(item, plugin);
        player.sendMessage(ChatColor.DARK_GREEN + "This item has " + livesremaining + " lives remaining");
        return true;
    }


}
