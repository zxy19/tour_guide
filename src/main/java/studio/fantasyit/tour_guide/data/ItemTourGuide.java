package studio.fantasyit.tour_guide.data;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.network.PacketDistributor;
import oshi.util.tuples.Pair;
import studio.fantasyit.tour_guide.api.event.ItemTourGuideRegisterEvent;
import studio.fantasyit.tour_guide.network.S2CSyncTriggerableItems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemTourGuide {
    public static Map<Item, List<ResourceLocation>> itemTourGuide = new HashMap<>();

    public static void clear() {
        itemTourGuide.clear();
    }

    public static void register(Item item, ResourceLocation tourGuide) {
        if (itemTourGuide.containsKey(item))
            itemTourGuide.get(item).add(tourGuide);
        else
            itemTourGuide.put(item, new ArrayList<>(List.of(tourGuide)));
    }

    public static void syncTo(ServerPlayer player) {
        PacketDistributor.sendToPlayer(player, new S2CSyncTriggerableItems(
                itemTourGuide
                        .entrySet()
                        .stream()
                        .flatMap(t ->
                                t.getValue()
                                        .stream()
                                        .map(tt -> new Pair<>(BuiltInRegistries.ITEM.getKey(t.getKey()), tt))
                        )
                        .toList()
        ));
    }

    public static void clearAndBroadcastRegister() {
        clear();
        NeoForge.EVENT_BUS.post(new ItemTourGuideRegisterEvent());
    }

    public static int getCount(Item item) {
        return itemTourGuide.containsKey(item) ? itemTourGuide.get(item).size() : 0;
    }

    public static ResourceLocation get(Item item, int offset) {
        if (itemTourGuide.containsKey(item))
            return itemTourGuide.get(item).get(offset % itemTourGuide.get(item).size());
        return null;
    }
}
