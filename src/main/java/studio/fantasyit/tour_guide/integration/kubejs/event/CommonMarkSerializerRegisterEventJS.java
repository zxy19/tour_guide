package studio.fantasyit.tour_guide.integration.kubejs.event;

import dev.latvian.mods.kubejs.event.EventJS;
import net.minecraft.resources.ResourceLocation;
import studio.fantasyit.tour_guide.mark.ContextMarkSerializer;

public class CommonMarkSerializerRegisterEventJS extends EventJS {
    public void register(ResourceLocation id, ContextMarkSerializer.NetworkSerializer<?> mark) {
        ContextMarkSerializer.register(id, mark);
    }
}
