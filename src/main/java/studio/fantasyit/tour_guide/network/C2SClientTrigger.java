package studio.fantasyit.tour_guide.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import studio.fantasyit.tour_guide.TourGuide;
import studio.fantasyit.tour_guide.api.TourGuideTrigger;

public record C2SClientTrigger(String name) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<C2SClientTrigger> TYPE = new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(
                    TourGuide.MODID, "c2s_client_trigger"
            )
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static C2SClientTrigger fromNetwork(FriendlyByteBuf buf) {
        return new C2SClientTrigger(buf.readUtf());
    }

    public void toNetwork(FriendlyByteBuf buf) {
        buf.writeUtf(name);
    }

    public static void handle(C2SClientTrigger packet, IPayloadContext ctxGetter) {
        ctxGetter.enqueueWork(() -> {
            TourGuideTrigger.trigger((ServerPlayer) ctxGetter.player(), packet.name);
        });
    }
}
