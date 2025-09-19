package studio.fantasyit.tour_guide.api.event;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.Event;
import studio.fantasyit.tour_guide.data.ItemTourGuide;

public class ItemTourGuideRegisterEvent extends Event {
    public void register(ResourceLocation id, Item... items) {
        for (Item item : items) {
            ItemTourGuide.register(item, id);
        }
    }
}
