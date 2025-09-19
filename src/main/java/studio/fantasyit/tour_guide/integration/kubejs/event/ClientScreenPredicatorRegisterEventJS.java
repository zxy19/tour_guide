package studio.fantasyit.tour_guide.integration.kubejs.event;

import dev.latvian.mods.kubejs.event.EventJS;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import studio.fantasyit.tour_guide.client.screen_predicator.ScreenPredicatorAndTransformer;

import java.util.function.BiConsumer;
import java.util.function.Predicate;

@OnlyIn(Dist.CLIENT)
public class ClientScreenPredicatorRegisterEventJS extends EventJS {
    public void registerPredicator(ResourceLocation id, Predicate<Screen> predicate) {
        ScreenPredicatorAndTransformer.register(id, predicate);
    }

    public void registerTransformer(ResourceLocation id, BiConsumer<Screen, GuiGraphics> transformer) {
        ScreenPredicatorAndTransformer.register(id, transformer);
    }
}
