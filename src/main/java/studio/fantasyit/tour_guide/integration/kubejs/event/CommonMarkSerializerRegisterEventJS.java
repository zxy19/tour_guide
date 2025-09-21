package studio.fantasyit.tour_guide.integration.kubejs.event;

import dev.latvian.mods.kubejs.event.KubeEvent;
import net.minecraft.resources.ResourceLocation;
import studio.fantasyit.tour_guide.mark.ContextMarkSerializer;

public class CommonMarkSerializerRegisterEventJS implements KubeEvent {
    public void register(ResourceLocation id, ContextMarkSerializer.NetworkSerializer<?> mark) {
        ContextMarkSerializer.register(id, mark);
    }
}
