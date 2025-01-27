package dev.eths.bossbar.listener;

import dev.eths.bossbar.LegacyBossBar;
import dev.eths.bossbar.LegacyBossBarPlugin;
import dev.eths.bossbar.example.ExampleAdapter;
import dev.eths.bossbar.player.WrappedBossBarPlayer;
import dev.eths.bossbar.util.IListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerJoinListener extends IListener {
    public PlayerJoinListener(JavaPlugin plugin, LegacyBossBar service) {
        super(plugin, service);
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        service.getPlayerMap().put(
                player.getUniqueId(),
                new WrappedBossBarPlayer(player));


        if (this.plugin instanceof LegacyBossBarPlugin)
            service.setAdapter(player, new ExampleAdapter());
    }
}
