package yc.mhkif.marjaneapi.Entities;

import yc.mhkif.marjaneapi.Entities.Implementations.PromotionCenterId;
import yc.mhkif.marjaneapi.Enums.PromotionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Data
@Entity
@Table(name = "promotion_center")
public class PromotionCenter {

    @EmbeddedId
    private PromotionCenterId id;

    @MapsId("promotionId")
    @ManyToOne
    @JoinColumn(name = "promotion_id", nullable = false)
    private Promotion promotion;

    @MapsId("centerId")
    @ManyToOne
    @JoinColumn(name = "center_id", nullable = false)
    private Center center;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;

    @Enumerated(EnumType.STRING)
    private PromotionStatus status;

    private LocalDateTime performedAt;

    public PromotionCenter() {
        this.status = PromotionStatus.PENDING;
    }

}