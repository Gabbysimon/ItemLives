package com.gabby.itemLives;

import com.gabby.itemLives.command.AddItemLives;
import com.gabby.itemLives.command.GetItemLives;
import com.gabby.itemLives.command.SetItemLives;
import com.gabby.itemLives.listeners.DeathListener;
import com.gabby.itemLives.placeholders.MainHandLives;
import com.gabby.itemLives.placeholders.MainHandRepaired;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ItemLives extends JavaPlugin {

    @Override

    public void onLoad() {

        getLogger().info("ItemLives Plugin Loaded");
    }
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new DeathListener(this), this);
        getCommand("setlives").setExecutor(new SetItemLives(this));
        getCommand("getlives").setExecutor(new GetItemLives(this));
        getCommand("addlives").setExecutor(new AddItemLives(this));
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) { //
            new MainHandLives(this).register();
            new MainHandRepaired(this).register();//
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
