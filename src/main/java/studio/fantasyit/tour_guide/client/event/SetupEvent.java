package studio.fantasyit.tour_guide.client.event;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoader;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import studio.fantasyit.tour_guide.TourGuide;
import studio.fantasyit.tour_guide.api.event.ClientMarkRendererRegisterEvent;
import studio.fantasyit.tour_guide.api.event.ScreenPredicatorRegisterEvent;
import studio.fantasyit.tour_guide.integration.Integrations;
import studio.fantasyit.tour_guide.integration.kubejs.KubeJSPort;

@EventBusSubscriber(modid = TourGuide.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class SetupEvent {
    @SubscribeEvent
    public static void onSetup(FMLLoadCompleteEvent event) {
        event.enqueueWork(() -> {
            ModLoader.postEvent(new ClientMarkRendererRegisterEvent());
            ModLoader.postEvent(new ScreenPredicatorRegisterEvent());
            if (Integrations.kjs())
                KubeJSPort.setupClient();
        });
    }
}
