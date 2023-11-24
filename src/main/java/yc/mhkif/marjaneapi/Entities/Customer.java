package yc.mhkif.marjaneapi.Entities;

import jakarta.persistence.*;
import yc.mhkif.marjaneapi.Entities.Abstracts.Person;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "customers")
public class Customer extends Person {
    @Id
    private String cin;

    @ManyToOne
    @JoinColumn(name = "cashier_cin", referencedColumnName = "CIN", nullable = false)
    private Cashier cashier;

}
