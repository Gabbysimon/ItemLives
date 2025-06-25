package com.gabby.itemLives.placeholders;

import com.gabby.itemLives.ItemLivesUtils;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class MainHandLives extends PlaceholderExpansion {

    private final Plugin plugin;

    public MainHandLives(Plugin plugin) {
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
        return "itemlives";
    }

    @Override
    @NotNull
    public String getVersion() {
        return plugin.getPluginMeta().getVersion();
    }

    public String onPlaceholderRequest(Player player, @NotNull String params) {

        if (player == null) return "Not a player";
        if (params.equalsIgnoreCase("helditemlives")) {

            int lives = ItemLivesUtils.getLives(player.getInventory().getItemInMainHand(), plugin);
            return (lives == -1) ? "none" : String.valueOf(lives);
        }
        if (params.equalsIgnoreCase("helditemrepairs")) {

            int repaired = ItemLivesUtils.getRepaired(player.getInventory().getItemInMainHand(), plugin);
            return (repaired == -1) ? "none" : String.valueOf(repaired);

        }

        return null;

    }

}
