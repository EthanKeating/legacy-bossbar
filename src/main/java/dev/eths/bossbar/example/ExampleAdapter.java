package dev.eths.bossbar.example;

import dev.eths.bossbar.adapter.BossBarAdapter;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ExampleAdapter extends BossBarAdapter {

    private int health = 100;

    @Override
    public void tick() {
        if (--health <= 0)
            health = 100;
    }

    @Override
    public String getTitle(Player player) {
        return ChatColor.translateAlternateColorCodes('&',
                ("&" + Math.floor(Math.random() * 10)) + UUID.randomUUID().toString());
    }

    @Override
    public float getHealth(Player player) {
        return health;
    }

    @Override
    public boolean isHidden(Player player) {
        return false;
    }
}
