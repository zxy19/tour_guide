package studio.fantasyit.tour_guide.network;

import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import studio.fantasyit.tour_guide.TourGuide;
import studio.fantasyit.tour_guide.api.TourManager;
import studio.fantasyit.tour_guide.data.TourData;

import java.util.function.Supplier;

public record C2SInteractTourGuideData(Type _type) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<C2SInteractTourGuideData> TYPE = new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(
                    TourGuide.MODID, "c2s_interact"
            )
    );
    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
    public enum Type {
        DONE,
        QUIT,
        SKIP,
        BACK
    }

    public void toNetwork(FriendlyByteBuf buf) {
        buf.writeEnum(_type);
    }

    public static C2SInteractTourGuideData fromNetwork(FriendlyByteBuf buf) {
        return new C2SInteractTourGuideData(buf.readEnum(Type.class));
    }

    public static void handle(C2SInteractTourGuideData packet, IPayloadContext ctxGetter) {
        ctxGetter.enqueueWork(() -> {
            ServerPlayer sender = (ServerPlayer) ctxGetter.player();
            TourData tourData = TourManager.get(sender);
            if (tourData == null) {
                return;
            }
            try {
                switch (packet._type) {
                    case DONE -> tourData.doneAndTryNextStep();
                    case SKIP -> tourData.skipAndTryNextStep();
                    case QUIT -> tourData.terminate();
                    case BACK -> tourData.goPrevStep();
                }
            } catch (Exception e) {
                sender.sendSystemMessage(Component.literal(e.getMessage()).withStyle(ChatFormatting.RED));
            }
        });
    }
}
