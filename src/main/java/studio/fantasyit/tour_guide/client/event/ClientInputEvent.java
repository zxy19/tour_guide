package studio.fantasyit.tour_guide.client.event;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.util.Lazy;
import net.neoforged.neoforge.network.PacketDistributor;
import org.lwjgl.glfw.GLFW;
import studio.fantasyit.tour_guide.TourGuide;
import studio.fantasyit.tour_guide.client.counter.ClientItemTourGuideCounter;
import studio.fantasyit.tour_guide.client.counter.ClientQuitCounter;
import studio.fantasyit.tour_guide.network.C2SInteractTourGuideData;

@EventBusSubscriber(modid = TourGuide.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)

public class ClientInputEvent {
    public static final Lazy<KeyMapping> KEY_CHECK_STEP = Lazy.of(() -> new KeyMapping(
            "key.tour_guide.check_step",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_ENTER,
            "key.tour_guide.category"
    ));
    public static final Lazy<KeyMapping> KEY_SKIP = Lazy.of(() -> new KeyMapping(
            "key.tour_guide.skip",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_KP_SUBTRACT,
            "key.tour_guide.category"
    ));
    public static final Lazy<KeyMapping> KEY_QUIT = Lazy.of(() -> new KeyMapping(
            "key.tour_guide.quit",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_END,
            "key.tour_guide.category"
    ));
    public static final Lazy<KeyMapping> KEY_START_TOUR_GUIDE = Lazy.of(() -> new KeyMapping(
            "key.tour_guide.start",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_T,
            "key.tour_guide.category"
    ));
    public static final Lazy<KeyMapping> KEY_SWITCH_ITEM_GUIDE = Lazy.of(() -> new KeyMapping(
            "key.tour_guide.switch",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_TAB,
            "key.tour_guide.category"
    ));
    public static boolean pressingShiftKey = false;
    public static boolean pressingEscapeKey = false;

    @EventBusSubscriber(modid = TourGuide.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ModBus {
        @SubscribeEvent
        public static void registerKeyMappings(final RegisterKeyMappingsEvent event) {
            event.register(KEY_CHECK_STEP.get());
            event.register(KEY_SKIP.get());
            event.register(KEY_QUIT.get());
            event.register(KEY_START_TOUR_GUIDE.get());
            event.register(KEY_SWITCH_ITEM_GUIDE.get());
        }
    }

    @SubscribeEvent
    public static void onKey(InputEvent.Key event) {
        InputConstants.Key key = InputConstants.getKey(event.getKey(), event.getScanCode());
        if (event.getAction() == GLFW.GLFW_PRESS) {
            if (KEY_QUIT.get().getKey().equals(key)) {
                ClientQuitCounter.keyPressed();
            }
            if (KEY_SKIP.get().getKey().equals(key)) {
                PacketDistributor.sendToServer(new C2SInteractTourGuideData(C2SInteractTourGuideData.Type.SKIP));
            }
            if (KEY_CHECK_STEP.get().getKey().equals(key)) {
                if ((event.getModifiers() & GLFW.GLFW_MOD_SHIFT) != 0)
                    PacketDistributor.sendToServer(new C2SInteractTourGuideData(C2SInteractTourGuideData.Type.BACK));
                else
                    PacketDistributor.sendToServer(new C2SInteractTourGuideData(C2SInteractTourGuideData.Type.DONE));
            }
            if (KEY_START_TOUR_GUIDE.get().getKey().equals(key)) {
                ClientItemTourGuideCounter.keyPressed();
            }
            if (KEY_SWITCH_ITEM_GUIDE.get().getKey().equals(key)) {
                ClientItemTourGuideCounter.offset();
            }
            if (key.getValue() == GLFW.GLFW_KEY_LEFT_SHIFT) {
                pressingShiftKey = true;
            }
        }
        if (event.getAction() == GLFW.GLFW_RELEASE) {
            if (KEY_START_TOUR_GUIDE.get().getKey().equals(key)) {
                ClientItemTourGuideCounter.keyReleased();
            }
            if (key.getValue() == GLFW.GLFW_KEY_LEFT_SHIFT) {
                pressingShiftKey = false;
            }
            if (KEY_QUIT.get().getKey().equals(key)) {
                ClientQuitCounter.keyReleased();
            }
        }
    }
}