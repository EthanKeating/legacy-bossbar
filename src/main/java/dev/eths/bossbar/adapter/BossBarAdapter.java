package dev.eths.bossbar.adapter;

import lombok.experimental.Accessors;
import org.bukkit.entity.Player;

@Accessors(chain = true)
public abstract class BossBarAdapter {

    public abstract void tick();

    public abstract String getTitle(Player player);

    public abstract float getHealth(Player player);

    public abstract boolean isHidden(Player player);

    public static BossBarAdapter DEFAULT = new BossBarAdapter() {
        public void tick() { }
        public String getTitle(Player player) { return "&7"; }
        public float getHealth(Player player) { return 100.0f; }
        public boolean isHidden(Player player) { return true; }
    };
}
