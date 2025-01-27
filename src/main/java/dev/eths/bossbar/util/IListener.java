package dev.eths.bossbar.util;

import dev.eths.bossbar.LegacyBossBar;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class IListener implements Listener {

    protected final JavaPlugin plugin;
    protected final LegacyBossBar service;

    public IListener(JavaPlugin plugin, LegacyBossBar service) {
        this.plugin = plugin;
        this.service = service;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

}
