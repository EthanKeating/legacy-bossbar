package dev.eths.bossbar.thread;

import dev.eths.bossbar.LegacyBossBar;
import dev.eths.bossbar.adapter.BossBarAdapter;
import dev.eths.bossbar.impl.BossBarImpl;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class BossBarThread extends Thread {

    private final LegacyBossBar service;

    private final ArrayList<UUID> cachedPlayers = new ArrayList<>();

    private final HashMap<UUID, BossBarImpl> bossBarImplementations = new HashMap<>();

    public BossBarThread(final LegacyBossBar service) {
        super("bossbar-thread");
        this.service = service;
    }

    public void run() {
        while (true) {
            long dynamicSleep = System.currentTimeMillis();
            HashSet<BossBarAdapter> tickedBossBars = new HashSet<>();

            new CopyOnWriteArrayList<>(service.getPlayerMap().values()).forEach(bossBarPlayer -> {
                BossBarAdapter adapter = bossBarPlayer.getBossBarAdapter();

                if (!tickedBossBars.contains(adapter)) {
                    tickedBossBars.add(adapter);
                    adapter.tick();
                }

                bossBarPlayer.getBossBarImpl().tick(bossBarPlayer.getBossBarAdapter());
            });
            try {
                Thread.sleep(Math.max(0, 50 - (System.currentTimeMillis() - dynamicSleep)));
            } catch (final InterruptedException e) {
                Bukkit.getLogger().warning("BossBar thread interrupted from an outside source.");
            }
        }
    }


}
