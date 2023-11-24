package yc.mhkif.marjaneapi.Enums;

import lombok.Getter;

@Getter
public enum PromotionNotifierStatus {
    PROMOTION_ACCEPTED("Promotion_Accepted"),
    PROMOTION_REFUSED("Promotion_Refused"),
    NEW_PROMOTION("New_Promotion");

    private final String status;

    PromotionNotifierStatus(String status) {
        this.status = status;
    }

}
