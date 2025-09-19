package studio.fantasyit.tour_guide.integration.kubejs;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import dev.latvian.mods.kubejs.script.ScriptType;
import dev.latvian.mods.rhino.util.wrap.TypeWrappers;
import studio.fantasyit.tour_guide.integration.kubejs.helper.TourGuideObj;

public class KJSPlugin extends KubeJSPlugin {

    @Override
    public void registerTypeWrappers(ScriptType type, TypeWrappers typeWrappers) {
    }

    @Override
    public void registerEvents() {
        KJSRegEvent.client.register();
        KJSRegEvent.common.register();
    }

    @Override
    public void registerBindings(BindingsEvent event) {
        event.add("TourGuide", new TourGuideObj());
    }

}
