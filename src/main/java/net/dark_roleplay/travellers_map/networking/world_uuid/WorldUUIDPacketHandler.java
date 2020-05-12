package net.dark_roleplay.travellers_map.networking.world_uuid;

import net.dark_roleplay.travellers_map.util.MapManager;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.concurrent.ThreadTaskExecutor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.LogicalSidedProvider;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class WorldUUIDPacketHandler {
    public static void encode(WorldUUIDPacket packet, PacketBuffer packetBuffer) {
        packetBuffer.writeUniqueId(packet.getWorldUUID());
    }

    public static WorldUUIDPacket decode(PacketBuffer buffer) {
        WorldUUIDPacket packet = new WorldUUIDPacket();
        return packet.setWorldUUID(buffer.readUniqueId());
    }

    public static void onMessage(WorldUUIDPacket packet, Supplier<NetworkEvent.Context> supplier) {
        MapManager.setWorldUUID(packet.getWorldUUID());
    }
}
