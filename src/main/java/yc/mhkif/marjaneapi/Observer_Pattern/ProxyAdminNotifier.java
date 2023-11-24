package yc.mhkif.marjaneapi.Observer_Pattern;


import yc.mhkif.marjaneapi.DTOs.Responses.PromotionResponse;
import yc.mhkif.marjaneapi.Enums.PromotionNotifierStatus;

public class ProxyAdminNotifier implements PromotionObserver{
    @Override
    public void promotionMade(PromotionNotifierStatus EventType, PromotionResponse promotion) {
        if(!EventType.equals(PromotionNotifierStatus.NEW_PROMOTION)){
            System.out.println("\n| [ ::: "+EventType.name()+" ::: ]  ==>  ProxyAdminNotifier has been Notified about a new action  has been performed on a promotion . |\n");
        }
    }
}
