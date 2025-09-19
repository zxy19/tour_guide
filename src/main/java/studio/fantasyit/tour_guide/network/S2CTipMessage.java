package studio.fantasyit.tour_guide.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import studio.fantasyit.tour_guide.TourGuide;
import studio.fantasyit.tour_guide.client.ClientMessages;

import java.util.function.Supplier;

public record S2CTipMessage(boolean allowSkip, boolean noCondition) implements CustomPacketPayload {
    public void toNetwork(FriendlyByteBuf buf) {
        buf.writeBoolean(allowSkip);
        buf.writeBoolean(noCondition);
    }

    public static S2CTipMessage fromNetwork(FriendlyByteBuf buf) {
        return new S2CTipMessage(buf.readBoolean(), buf.readBoolean());
    }

    @OnlyIn(Dist.CLIENT)
    public static void handle(S2CTipMessage packet, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            ClientMessages.sendOp(packet.allowSkip,packet.noCondition);
        });
    }

    public static final CustomPacketPayload.Type<S2CTipMessage> TYPE = new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(
                    TourGuide.MODID, "s2c_tip_message"
            )
    );
    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
