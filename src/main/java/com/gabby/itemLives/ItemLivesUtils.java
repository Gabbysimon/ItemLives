package com.gabby.itemLives;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;

import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;


public class ItemLivesUtils {

    public static int getRepaired(ItemStack item, Plugin plugin) {
        if (item.getType() == Material.AIR) {
            return -1;
        }
        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            return -1;
        }

        NamespacedKey key = new NamespacedKey(plugin, "repaired");

        if (meta.getPersistentDataContainer().has(key, PersistentDataType.INTEGER)) {
            int repaired = meta.getPersistentDataContainer().get(key, PersistentDataType.INTEGER);
            return repaired;
        }

        return 0;}

    public static int setRepaired(ItemStack item, int repaired, Plugin plugin) {

        if (item.getType() == Material.AIR) {

            return -1;
        }

        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            return -1;
        }

        NamespacedKey key = new NamespacedKey(plugin, "repaired");
        meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, repaired);
        item.setItemMeta(meta);
        return repaired;


    }

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


        return 0;
    }

    public static int setLives(ItemStack item, int lives, Plugin plugin) {

        if (item.getType() == Material.AIR) {

            return -1;
        }

        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            return -1;
        }
        @Nullable List<Component> lore = meta.lore();
        if (lore == null) {
            lore = new ArrayList<>();

        }
        boolean loreupdated = false;
        for (int i = 0; i < lore.size(); i++) {

            String line = net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer.plainText().serialize(lore.get(i)).toLowerCase();
            if (line.contains("lives")) {
                lore.set(i, Component.text(lives + " Lives Remaining").color(NamedTextColor.GOLD).decoration(TextDecoration.ITALIC, false));
                loreupdated = true;
                break;
            }
        }
        if (!loreupdated) {

            lore.add(Component.text(lives + " Lives Remaining").color(NamedTextColor.GOLD).decoration(TextDecoration.ITALIC, false));
        }

        meta.lore(lore);
        item.setItemMeta(meta);



        NamespacedKey key = new NamespacedKey(plugin, "life");
        meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, lives);
        item.setItemMeta(meta);
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
