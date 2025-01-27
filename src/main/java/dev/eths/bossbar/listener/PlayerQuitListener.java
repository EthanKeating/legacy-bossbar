package dev.eths.bossbar.listener;

import dev.eths.bossbar.LegacyBossBar;
import dev.eths.bossbar.util.IListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerQuitListener extends IListener {
    public PlayerQuitListener(JavaPlugin plugin, LegacyBossBar service) {
        super(plugin, service);
    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent event) {
        final Player player = event.getPlayer();

        service.getPlayerMap().remove(player.getUniqueId());
    }
}
