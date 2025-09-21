package studio.fantasyit.tour_guide.integration.kubejs.event;

import dev.latvian.mods.kubejs.event.KubeEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import studio.fantasyit.tour_guide.data.ItemTourGuide;

public class ItemTourGuideRegisterEventJS  implements KubeEvent {
    public void register(ResourceLocation id, Item... items) {
        for (Item item : items) {
            ItemTourGuide.register(item, id);
        }
    }
}
