package yc.mhkif.marjaneapi.DTOs;

import yc.mhkif.marjaneapi.Entities.Center;
import yc.mhkif.marjaneapi.Entities.Product;
import yc.mhkif.marjaneapi.Entities.ProxyAdmin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PromotionDTO {
    private Long id;
    private Product product;
    private ProxyAdmin admin;
    private BigDecimal percentage;
    private LocalDateTime createdAt;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private List<Center> centers;
}
