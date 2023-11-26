package yc.mhkif.marjaneapi.Services.Observables;


import yc.mhkif.marjaneapi.DTOs.Responses.PromotionResponse;
import yc.mhkif.marjaneapi.Enums.PromotionNotifierStatus;
import yc.mhkif.marjaneapi.Services.Interfaces.PromotionObserver;

public class ManagerNotifier implements PromotionObserver {
    // Now the ManagerNotifier became an Observer that implementing PromotionObserver Contract

    @Override
    public void promotionMade(PromotionNotifierStatus EventType, PromotionResponse promotion) {
        if (EventType.equals(PromotionNotifierStatus.NEW_PROMOTION)) {
            System.out.println("\n[ ::: " + EventType.name() + " ::: ]  ==>  ManagerNotifier has been Notified about a new promotion has been added. ");
        }
    }
}
