package studio.fantasyit.tour_guide.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;
import studio.fantasyit.tour_guide.client.ClientMessages;

import java.util.function.Supplier;

public record S2CTipMessage(boolean allowSkip, boolean noCondition) {
    public void toNetwork(FriendlyByteBuf buf) {
        buf.writeBoolean(allowSkip);
        buf.writeBoolean(noCondition);
    }

    public static S2CTipMessage fromNetwork(FriendlyByteBuf buf) {
        return new S2CTipMessage(buf.readBoolean(), buf.readBoolean());
    }

    @OnlyIn(Dist.CLIENT)
    public static void handle(S2CTipMessage packet, Supplier<NetworkEvent.Context> ctxGetter) {
        ctxGetter.get().enqueueWork(() -> {
            ClientMessages.sendOp(packet.allowSkip,packet.noCondition);
        });
        ctxGetter.get().setPacketHandled(true);
    }
}
