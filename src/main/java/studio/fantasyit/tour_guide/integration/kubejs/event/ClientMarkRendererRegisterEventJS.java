package studio.fantasyit.tour_guide.integration.kubejs.event;

import dev.latvian.mods.kubejs.event.EventJS;
import net.minecraft.resources.ResourceLocation;
import studio.fantasyit.tour_guide.client.IGuiMarkRenderer;
import studio.fantasyit.tour_guide.client.IWorldMarkRenderer;
import studio.fantasyit.tour_guide.client.MarkRendererManager;

public class ClientMarkRendererRegisterEventJS extends EventJS {
    public void registerGuiMark(ResourceLocation id, IGuiMarkRenderer<?> renderer) {
        MarkRendererManager.register(id, renderer);
    }

    public void registerWorldMark(ResourceLocation id, IWorldMarkRenderer<?> renderer) {
        MarkRendererManager.register(id, renderer);
    }
}
