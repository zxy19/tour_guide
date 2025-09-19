package studio.fantasyit.tour_guide.api.event;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import studio.fantasyit.tour_guide.mark.ContextMarkSerializer;

public class CommonMarkSerializerRegisterEvent extends Event implements IModBusEvent {
    public void register(ResourceLocation id, ContextMarkSerializer.NetworkSerializer<?> mark) {
        ContextMarkSerializer.register(id, mark);
    }
}
