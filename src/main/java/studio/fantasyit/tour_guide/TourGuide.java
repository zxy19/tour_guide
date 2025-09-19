package studio.fantasyit.tour_guide;


import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TourGuide.MODID)
public class TourGuide {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "tour_guide";

    public TourGuide() {
        IEventBus modEventBus = ModLoadingContext.get().getActiveContainer().getEventBus();
//        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
