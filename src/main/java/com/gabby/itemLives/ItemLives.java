package com.gabby.itemLives;

import com.gabby.itemLives.command.AddItemLives;
import com.gabby.itemLives.command.GetItemLives;
import com.gabby.itemLives.command.SetItemLives;
import org.bukkit.plugin.java.JavaPlugin;

public final class ItemLives extends JavaPlugin {

    @Override

    public void onLoad() {

        getLogger().info("ItemLives Plugin Loaded");
    }
    public void onEnable() {
        // Plugin startup logic
        getCommand("setitemlives").setExecutor(new SetItemLives(this));
        getCommand("getlives").setExecutor(new GetItemLives(this));
        getCommand("addlives").setExecutor(new AddItemLives(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
