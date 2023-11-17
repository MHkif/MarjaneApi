package yc.mhkif.marjaneapi.DTOs;

import yc.mhkif.marjaneapi.Entities.Center;
import yc.mhkif.marjaneapi.Entities.Implementations.PromotionCenterId;
import yc.mhkif.marjaneapi.Entities.Manager;
import yc.mhkif.marjaneapi.Entities.Promotion;
import yc.mhkif.marjaneapi.Enums.PromotionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromotionCenterDTO {
    private PromotionCenterId id;
    private Promotion productPromotion;
    private Center center;
    private Manager manager;
    private PromotionStatus status;
    private LocalDateTime performedAt;
}
