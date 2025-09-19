package studio.fantasyit.tour_guide.network;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import oshi.util.tuples.Pair;
import studio.fantasyit.tour_guide.TourGuide;
import studio.fantasyit.tour_guide.data.ItemTourGuide;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public record S2CSyncTriggerableItems(
        List<Pair<ResourceLocation, ResourceLocation>> items) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<S2CSyncTriggerableItems> TYPE = new CustomPacketPayload.Type<>(
            ResourceLocation.fromNamespaceAndPath(
                    TourGuide.MODID, "s2c_sync_trigger_items"
            )
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static S2CSyncTriggerableItems fromNetwork(FriendlyByteBuf buf) {
        return new S2CSyncTriggerableItems(buf.readCollection(ArrayList::new, t -> new Pair<>(t.readResourceLocation(), t.readResourceLocation())));
    }

    public void toNetwork(FriendlyByteBuf buf) {
        buf.writeCollection(items, (t, u) -> {
            t.writeResourceLocation(u.getA());
            t.writeResourceLocation(u.getB());
        });
    }

    public static void handle(S2CSyncTriggerableItems packet, IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            ItemTourGuide.clear();
            packet.items.forEach(t -> {
                ItemTourGuide.register(BuiltInRegistries.ITEM.get(t.getA()), t.getB());
            });
        });
    }
}
