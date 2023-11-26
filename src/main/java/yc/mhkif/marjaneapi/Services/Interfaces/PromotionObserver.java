package yc.mhkif.marjaneapi.Services.Interfaces;

import yc.mhkif.marjaneapi.DTOs.Responses.PromotionResponse;
import yc.mhkif.marjaneapi.Enums.PromotionNotifierStatus;

@FunctionalInterface
public interface PromotionObserver {
    // This an Observer Object that observing the Promotion Event

    // We need to define a contract that will be implemented
    // by every Observer observing the promotions of the Promotion manager

    public void promotionMade(PromotionNotifierStatus EventType, PromotionResponse promotion);
}
