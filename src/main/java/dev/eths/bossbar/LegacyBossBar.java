package dev.eths.bossbar;

import com.github.retrooper.packetevents.PacketEvents;
import dev.eths.bossbar.adapter.BossBarAdapter;
import dev.eths.bossbar.listener.PlayerJoinListener;
import dev.eths.bossbar.listener.PlayerQuitListener;
import dev.eths.bossbar.player.WrappedBossBarPlayer;
import dev.eths.bossbar.thread.BossBarThread;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

@Getter
public class LegacyBossBar {

    private final HashMap<UUID, WrappedBossBarPlayer> playerMap = new HashMap<>();

    public LegacyBossBar(JavaPlugin plugin) {
        LegacyBossBar service = this;

        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(plugin));
        PacketEvents.getAPI().getSettings().checkForUpdates(false).bStats(false);
        PacketEvents.getAPI().load();

        new PlayerJoinListener(plugin, this);
        new PlayerQuitListener(plugin, this);
        new BossBarThread(service).start();
    }

    public void setAdapter(Player player, BossBarAdapter bossBarAdapter) {
        WrappedBossBarPlayer bossBarPlayer = playerMap.get(player.getUniqueId());
        if (bossBarPlayer != null)
            bossBarPlayer.setBossBarAdapter(bossBarAdapter);
    }

    public void removeAdapter(Player player) {
        WrappedBossBarPlayer bossBarPlayer = playerMap.get(player.getUniqueId());
        if (bossBarPlayer != null)
            bossBarPlayer.setBossBarAdapter(BossBarAdapter.DEFAULT);
    }

}
