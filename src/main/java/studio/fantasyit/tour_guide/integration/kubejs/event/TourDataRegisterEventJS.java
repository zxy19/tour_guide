package studio.fantasyit.tour_guide.integration.kubejs.event;

import dev.latvian.mods.kubejs.event.KubeEvent;
import net.minecraft.resources.ResourceLocation;
import studio.fantasyit.tour_guide.api.TourDataManager;
import studio.fantasyit.tour_guide.data.ITourDataFactory;

public class TourDataRegisterEventJS  implements KubeEvent {
    public void register(ResourceLocation id, ITourDataFactory tourData) {
        TourDataManager.register(id, tourData);
    }
}
