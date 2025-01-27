package dev.eths.bossbar.player;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.protocol.player.ClientVersion;
import com.github.retrooper.packetevents.protocol.player.User;
import dev.eths.bossbar.adapter.BossBarAdapter;
import dev.eths.bossbar.impl.BossBarImpl;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

@Getter
public class WrappedBossBarPlayer {

    private final UUID uuid;
    private final User user;

    private final BossBarImpl bossBarImpl;
    private final boolean isLegacy;

    @Getter @Setter
    private BossBarAdapter bossBarAdapter;

    public WrappedBossBarPlayer(final Player player) {
        this.uuid = player.getUniqueId();
        this.user = PacketEvents.getAPI().getPlayerManager().getUser(player);
        this.bossBarImpl = new BossBarImpl(this);
        this.isLegacy = user.getClientVersion().isOlderThanOrEquals(ClientVersion.V_1_7_10);
        this.bossBarAdapter = BossBarAdapter.DEFAULT;
    }

    public Player toBukkit() {
        return Bukkit.getPlayer(uuid);
    }

}
