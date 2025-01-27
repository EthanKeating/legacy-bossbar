package dev.eths.bossbar.impl;

import dev.eths.bossbar.adapter.BossBarAdapter;
import dev.eths.bossbar.packet.PacketBuilder;
import dev.eths.bossbar.player.WrappedBossBarPlayer;
import io.github.retrooper.packetevents.util.SpigotReflectionUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@Getter
@Setter
@Accessors(chain = true)
public class BossBarImpl {

    private static final int[] ENTITY_IDS = new int[] {
            SpigotReflectionUtil.generateEntityId(),
            SpigotReflectionUtil.generateEntityId()
    };

    private static final int FORWARD_DISTANCE = 28;

    private final WrappedBossBarPlayer bossBarPlayer;

    private String title = "&7";
    private float health = 300;
    private boolean hidden = false;

    public BossBarImpl(final WrappedBossBarPlayer bossBarPlayer) {
        this.bossBarPlayer = bossBarPlayer;
    }

    public void tick(final BossBarAdapter adapter) {
        final Player player = bossBarPlayer.toBukkit();

        updateTitle(adapter, player);
        updateHealth(adapter, player);
        handleHiddenState(adapter, player);

        if (!hidden)
            updatePosition(player);
    }

    private void updateTitle(final BossBarAdapter adapter, final Player player) {
        final String currentTitle = adapter.getTitle(player);
        if (!currentTitle.equals(title))
            title = currentTitle;
    }

    private void updateHealth(final BossBarAdapter adapter, final Player player) {
        float currentHealth = adapter.getHealth(player);
        if (currentHealth != health)
            health = currentHealth;
    }

    private void handleHiddenState(final BossBarAdapter adapter, final Player player) {
        final boolean currentHiddenState = adapter.isHidden(player);

        if (currentHiddenState != hidden) {
            hidden = currentHiddenState;

            if (hidden) bossBarPlayer.getUser().sendPacketSilently(PacketBuilder.getDestroyPacket(ENTITY_IDS));
        }
    }

    private void updatePosition(final Player player) {
        final Location[] locations = new Location[]{
                player.getEyeLocation().add(player.getLocation().getDirection().multiply(FORWARD_DISTANCE)).add(0, 3, 0),
                player.getEyeLocation().add(player.getLocation().getDirection().multiply(-FORWARD_DISTANCE))
        };

        for (int i = 0; i <= 1; i++)
            bossBarPlayer.getUser().sendPacketSilently(PacketBuilder.getSpawnPacket(
                    ENTITY_IDS[i],
                    locations[i],
                    title, health));
    }

}
