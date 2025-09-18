package studio.fantasyit.tour_guide.event;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import studio.fantasyit.tour_guide.TourGuide;
import studio.fantasyit.tour_guide.api.event.CommonMarkSerializerRegisterEvent;

@Mod.EventBusSubscriber(modid = TourGuide.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SetupEvent {
    @SubscribeEvent
    public static void onSetup(FMLLoadCompleteEvent event) {
        event.enqueueWork(() -> {
            ModLoader.get().postEvent(new CommonMarkSerializerRegisterEvent());
        });
    }
}
