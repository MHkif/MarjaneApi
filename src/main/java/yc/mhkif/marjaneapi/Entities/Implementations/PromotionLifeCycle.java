package yc.mhkif.marjaneapi.Entities.Implementations;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import yc.mhkif.marjaneapi.Entities.Product;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PromotionLifeCycle implements Serializable {
    private Product product;
    protected LocalDateTime startAt;
    protected LocalDateTime endAt;
}