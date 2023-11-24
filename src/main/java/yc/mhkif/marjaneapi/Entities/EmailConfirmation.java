package yc.mhkif.marjaneapi.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
//@Entity
//@Table(name = "email_confirmations")
public class EmailConfirmation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(targetEntity = Customer.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_cin", nullable = false)
    private Customer customer;

    private String code;
    @Column(name = "is_confirmed", nullable = false, columnDefinition = "boolean default false")
    private boolean isConfirmed;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at" )
    private LocalDateTime updatedAt;

    public EmailConfirmation(Customer customer){
        this.customer = customer;
        this.createdAt = LocalDateTime.now();
        this.code = "marjane007";

    }
}
