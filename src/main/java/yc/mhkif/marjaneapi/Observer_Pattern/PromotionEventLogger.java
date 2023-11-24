package yc.mhkif.marjaneapi.Observer_Pattern;


import yc.mhkif.marjaneapi.DTOs.Responses.PromotionResponse;
import yc.mhkif.marjaneapi.Enums.PromotionNotifierStatus;

public class PromotionEventLogger implements PromotionObserver{
    // Now the PromotionEventLogger became an Observer that implementing
    // PromotionObserver Contract and it will save the logs

    @Override
    public void promotionMade(PromotionNotifierStatus EventType, PromotionResponse promotion) {
        System.out.println("\n|::[ Logger ]::|    PromotionEventLogger has been Notified about a "+EventType.name()+" . ");
    }
}
