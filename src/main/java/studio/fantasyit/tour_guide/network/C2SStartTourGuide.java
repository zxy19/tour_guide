package studio.fantasyit.tour_guide.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import studio.fantasyit.tour_guide.TourGuide;
import studio.fantasyit.tour_guide.api.TourManager;

import java.util.Objects;

public record C2SStartTourGuide(ResourceLocation id) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<C2SStartTourGuide> TYPE = new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(
                    TourGuide.MODID, "c2s_start_tour_guide"
            )
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public void toNetwork(FriendlyByteBuf buf) {
        buf.writeResourceLocation(id);
    }

    public static C2SStartTourGuide fromNetwork(FriendlyByteBuf buf) {
        return new C2SStartTourGuide(buf.readResourceLocation());
    }

    public static void handle(C2SStartTourGuide packet, IPayloadContext ctx) {
        ctx.enqueueWork(() -> TourManager.start(Objects.requireNonNull((ServerPlayer) ctx.player()), packet.id));
    }
}
