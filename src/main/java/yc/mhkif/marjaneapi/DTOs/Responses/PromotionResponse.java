package yc.mhkif.marjaneapi.DTOs.Responses;

import lombok.Builder;
import yc.mhkif.marjaneapi.Entities.Center;
import yc.mhkif.marjaneapi.Entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import yc.mhkif.marjaneapi.Entities.ProxyAdmin;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PromotionResponse {
    private Long id;
    private Product product;
    private BigDecimal percentage;
    private LocalDateTime createdAt;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
}
