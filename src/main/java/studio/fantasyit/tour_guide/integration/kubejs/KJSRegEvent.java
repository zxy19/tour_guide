package studio.fantasyit.tour_guide.integration.kubejs;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;
import studio.fantasyit.tour_guide.integration.kubejs.event.*;

public class KJSRegEvent {
    static EventGroup client = EventGroup.of("TourGuideClient");
//    static EventHandler MarkRenderer = client.startup("markRenderer", () -> ClientMarkRendererRegisterEventJS.class);
    static EventHandler ScreenPredicator = client.startup("screenPredicator", () -> ClientScreenPredicatorRegisterEventJS.class);
    static EventGroup common = EventGroup.of("TourGuideCommon");
//    static EventHandler MarkSerializer = common.startup("markSerializer", () -> CommonMarkSerializerRegisterEventJS.class);
    static EventHandler TourData = common.server("data", () -> TourDataRegisterEventJS.class);
    static EventHandler Item = common.server("item", () -> ItemTourGuideRegisterEventJS.class);
}