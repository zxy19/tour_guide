package studio.fantasyit.tour_guide.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import studio.fantasyit.tour_guide.TourGuide;
import studio.fantasyit.tour_guide.data.ItemTourGuide;

import java.util.function.Supplier;

public record C2SRequestTriggerableItems() implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<C2SRequestTriggerableItems> TYPE = new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(
                    TourGuide.MODID, "c2s_request_triggerable"
            )
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
    public static C2SRequestTriggerableItems fromNetwork(FriendlyByteBuf buf) {
        return new C2SRequestTriggerableItems();
    }

    public void toNetwork(FriendlyByteBuf buf) {
    }

    public static void handle(C2SRequestTriggerableItems message, IPayloadContext contextGetter) {
        contextGetter.enqueueWork(() -> {
            ItemTourGuide.syncTo((ServerPlayer) contextGetter.player());
        });
    }
}
