package dev.eths.bossbar;

import org.bukkit.plugin.java.JavaPlugin;

public class LegacyBossBarPlugin extends JavaPlugin {

    public void onEnable() {
        new LegacyBossBar(this);
    }

    public void onDisable() {

    }

}
