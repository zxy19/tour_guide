package studio.fantasyit.tour_guide.integration.kubejs;

import studio.fantasyit.tour_guide.integration.kubejs.event.ClientScreenPredicatorRegisterEventJS;
import studio.fantasyit.tour_guide.integration.kubejs.event.ItemTourGuideRegisterEventJS;
import studio.fantasyit.tour_guide.integration.kubejs.event.TourDataRegisterEventJS;

public class KubeJSPort {
    public static void setupClient() {
        KJSRegEvent.ScreenPredicator.post(new ClientScreenPredicatorRegisterEventJS());
    }

    public static void reloaded() {
        KJSRegEvent.TourData.post(new TourDataRegisterEventJS());
        KJSRegEvent.Item.post(new ItemTourGuideRegisterEventJS());
    }
}
