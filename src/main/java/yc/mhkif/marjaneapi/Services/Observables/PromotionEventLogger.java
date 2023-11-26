package yc.mhkif.marjaneapi.Services.Observables;


import yc.mhkif.marjaneapi.DTOs.Responses.PromotionResponse;
import yc.mhkif.marjaneapi.Enums.PromotionNotifierStatus;
import yc.mhkif.marjaneapi.Services.Interfaces.PromotionObserver;

public class PromotionEventLogger implements PromotionObserver {
    // Now the PromotionEventLogger became an Observer that implementing
    // PromotionObserver Contract and it will save the logs

    @Override
    public void promotionMade(PromotionNotifierStatus EventType, PromotionResponse promotion) {
        System.out.println("\n|::[ Logger ]::|    PromotionEventLogger has been Notified about a "+EventType.name()+" . ");
    }
}
