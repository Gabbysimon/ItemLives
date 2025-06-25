package com.gabby.itemLives;

import org.bukkit.Material;

import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


public class ItemLivesUtils {

    public static int getLives(ItemStack item, Plugin plugin) {

        if (item.getType() == Material.AIR) {

            return -1;
        }

        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            return -1;
        }

        NamespacedKey key = new NamespacedKey(plugin, "life");

        if (meta.getPersistentDataContainer().has(key, PersistentDataType.INTEGER)) {
            int life = meta.getPersistentDataContainer().get(key, PersistentDataType.INTEGER);
            return life;
        }


        return -1;
    }

    public static int setLives(Player player, int lives, Plugin plugin) {

        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.getType() == Material.AIR) {

            return -1;
        }

        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            return -1;
        }

        NamespacedKey key = new NamespacedKey(plugin, "life");
        meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, lives);
        item.setItemMeta(meta);
        player.playSound(player.getLocation(), Sound.UI_LOOM_TAKE_RESULT, 1.0f, 1.0f);
        return lives;


    }

    public static int removeLives(Player player, int lives, Plugin plugin) {

        List<ItemStack> allItems = new ArrayList<>();
        Collections.addAll(allItems, player.getInventory().getContents());
        Collections.addAll(allItems, player.getInventory().getArmorContents());
        allItems.add(player.getInventory().getItemInOffHand());

        for (ItemStack item : allItems) {

            if (item.getType() == Material.AIR) continue; {

                if (getLives(item, plugin) != -1) {

                    ItemMeta meta = item.getItemMeta();

                    NamespacedKey key = new NamespacedKey(plugin, "life");
                    meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, lives);
                    item.setItemMeta(meta);
                    return lives;
                }
            }
        }

        return -1;
    }


}
