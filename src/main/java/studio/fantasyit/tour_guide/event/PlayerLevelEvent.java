package studio.fantasyit.tour_guide.event;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import studio.fantasyit.tour_guide.TourGuide;
import studio.fantasyit.tour_guide.api.TourManager;

@Mod.EventBusSubscriber(modid = TourGuide.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerLevelEvent {
    @SubscribeEvent
    public static void onPlayerLevel(PlayerEvent.PlayerLoggedOutEvent event) {
        if (event.getEntity() instanceof ServerPlayer sp)
            TourManager.remove(sp);
    }
}
