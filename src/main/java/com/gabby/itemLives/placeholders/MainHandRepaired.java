package com.gabby.itemLives.placeholders;

import com.gabby.itemLives.ItemLivesUtils;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class MainHandRepaired extends PlaceholderExpansion {

    private final Plugin plugin;

    public MainHandRepaired(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    @NotNull
    public String getAuthor() {
        return "Gabbysimon";
    }
    @Override
    @NotNull
    public String getIdentifier() {
        return "repairtracker";
    }

    @Override
    @NotNull
    public String getVersion() {
        return plugin.getPluginMeta().getVersion();
    }

    public String onPlaceholderRequest(Player player, @NotNull String params) {

        if (player == null) return "Not a player";
        if (params.equalsIgnoreCase("helditemrepairs")) {

            int repaired = ItemLivesUtils.getRepaired(player.getInventory().getItemInMainHand(), plugin);
            return (repaired == -1) ? "none" : String.valueOf(repaired);
        }

        return null;
    }



}
