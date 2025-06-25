package com.gabby.itemLives.listeners;

import com.gabby.itemLives.ItemLivesUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DeathListener implements Listener {

    private final Plugin plugin;

    public DeathListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        player.sendMessage(Component.text("You a dead mf!", NamedTextColor.AQUA));

        List<ItemStack> drops = event.getDrops();
        List<ItemStack> toSave = new ArrayList<>();

        Iterator<ItemStack> it = drops.iterator();
        while (it.hasNext()) {
            ItemStack item = it.next();
            if (item == null || item.getType() == Material.AIR) continue;

            int lives = ItemLivesUtils.getLives(item, plugin);
            if (lives >= 1) {
                ItemLivesUtils.setLives(item, lives - 1, plugin);
                toSave.add(item);
                it.remove();
            }
            if (lives == 0) {
                it.remove();

            }
        }

        if (!toSave.isEmpty()) {
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                for (ItemStack saved : toSave) {
                    player.getInventory().addItem(saved);
                }

                player.sendMessage(Component.text("A total of " + toSave.size() + " item" + (toSave.size() == 1 ? "" : "s") + " were saved by a life!", NamedTextColor.GREEN));
            }, 1L);
        }
    }
}