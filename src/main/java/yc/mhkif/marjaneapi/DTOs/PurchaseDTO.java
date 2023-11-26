package yc.mhkif.marjaneapi.DTOs;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import yc.mhkif.marjaneapi.Entities.Cashier;
import yc.mhkif.marjaneapi.Entities.Customer;
import yc.mhkif.marjaneapi.Entities.Product;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PurchaseDTO {
    private Long id;
    private Customer customer;
    private Product product;
    protected LocalDateTime createdAt;
}
