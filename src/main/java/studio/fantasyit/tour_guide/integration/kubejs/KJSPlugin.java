package studio.fantasyit.tour_guide.integration.kubejs;

import dev.latvian.mods.kubejs.event.EventGroupRegistry;
import dev.latvian.mods.kubejs.plugin.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingRegistry;
import dev.latvian.mods.kubejs.script.ScriptType;
import dev.latvian.mods.rhino.util.wrap.TypeWrappers;
import studio.fantasyit.tour_guide.integration.kubejs.helper.DataHelpers;
import studio.fantasyit.tour_guide.integration.kubejs.helper.TourGuideObj;

public class KJSPlugin implements KubeJSPlugin {

    @Override
    public void registerEvents(EventGroupRegistry registry) {
        registry.register(KJSRegEvent.client);
        registry.register(KJSRegEvent.common);
    }


    @Override
    public void registerBindings(BindingRegistry event) {
        event.add("TourGuide", TourGuideObj.INSTANCE);
        event.add("TourGuideData", DataHelpers.INSTANCE);
    }

}
