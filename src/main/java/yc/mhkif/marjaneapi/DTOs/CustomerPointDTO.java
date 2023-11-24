package yc.mhkif.marjaneapi.DTOs;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import yc.mhkif.marjaneapi.Entities.Customer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerPointDTO {
    private String code;
    private Customer customer;
    private BigDecimal points;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
