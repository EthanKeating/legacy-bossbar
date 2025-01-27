package dev.eths.bossbar.packet;

import com.github.retrooper.packetevents.protocol.entity.data.EntityData;
import com.github.retrooper.packetevents.protocol.entity.data.EntityDataTypes;
import com.github.retrooper.packetevents.protocol.entity.type.EntityTypes;
import com.github.retrooper.packetevents.util.Vector3d;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerDestroyEntities;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerSpawnLivingEntity;
import lombok.experimental.UtilityClass;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@UtilityClass
public class PacketBuilder {

    public WrapperPlayServerSpawnLivingEntity getSpawnPacket(final int entityId, final Location location, final String title, final float health) {
        return new WrapperPlayServerSpawnLivingEntity(
                entityId,
                UUID.randomUUID(),
                EntityTypes.WITHER,
                new Vector3d(location.getX(), location.getY(), location.getZ()),
                0,
                0,
                0,
                new Vector3d(0.0, 0.0, 0.0),
                buildData(title, health));
    }

    public WrapperPlayServerDestroyEntities getDestroyPacket(final int[] entityIds) {
        return new WrapperPlayServerDestroyEntities(entityIds);
    }

    public List<EntityData> buildData(final String title, final float health) {
        return Arrays.asList(
                new EntityData(0, EntityDataTypes.BYTE, (byte) 0x20),
                new EntityData(1, EntityDataTypes.SHORT, (short) 300),
                new EntityData(2, EntityDataTypes.STRING, ChatColor.translateAlternateColorCodes('&', title)),
                new EntityData(3, EntityDataTypes.BYTE, (byte) 1),
                new EntityData(6, EntityDataTypes.FLOAT, Math.max(0.0f, Math.min(health * 3.0f, 300.0f))),
                new EntityData(7, EntityDataTypes.INT, Color.BLACK.asRGB()),
                new EntityData(8, EntityDataTypes.BYTE, (byte) 0),
                new EntityData(15, EntityDataTypes.BYTE, (byte) 1),
                new EntityData(20, EntityDataTypes.INT, Integer.MAX_VALUE)
        );
    }


}
