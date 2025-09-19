package studio.fantasyit.tour_guide.integration.kubejs.event;

import dev.latvian.mods.kubejs.event.EventJS;
import net.minecraft.resources.ResourceLocation;
import studio.fantasyit.tour_guide.api.TourDataManager;
import studio.fantasyit.tour_guide.data.ITourDataFactory;

public class TourDataRegisterEventJS extends EventJS {
    public void register(ResourceLocation id, ITourDataFactory tourData) {
        TourDataManager.register(id, tourData);
    }
}
