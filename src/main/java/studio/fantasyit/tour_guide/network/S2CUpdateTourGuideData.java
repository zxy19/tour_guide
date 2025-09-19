package studio.fantasyit.tour_guide.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import studio.fantasyit.tour_guide.TourGuide;
import studio.fantasyit.tour_guide.client.TourGuidingClientData;
import studio.fantasyit.tour_guide.mark.ContextMarkSerializer;
import studio.fantasyit.tour_guide.mark.IMark;

import java.util.List;
import java.util.function.Supplier;

public record S2CUpdateTourGuideData(List<IMark> marks) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<S2CUpdateTourGuideData> TYPE = new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(
                    TourGuide.MODID, "s2c_update_tour_guide_data"
            )
    );
    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
    public void toNetwork(FriendlyByteBuf buf) {
        buf.writeCollection(marks, ContextMarkSerializer::serialize);
    }

    public static S2CUpdateTourGuideData fromNetwork(FriendlyByteBuf buf) {
        return new S2CUpdateTourGuideData(buf.readList(ContextMarkSerializer::deserialize));
    }

    @OnlyIn(Dist.CLIENT)
    public static void handle(S2CUpdateTourGuideData packet, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            TourGuidingClientData.setMarks(packet.marks);
        });
    }

}
