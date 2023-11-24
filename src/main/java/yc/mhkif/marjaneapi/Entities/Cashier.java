package yc.mhkif.marjaneapi.Entities;

import jakarta.persistence.*;
import lombok.*;
import yc.mhkif.marjaneapi.Entities.Abstracts.Person;



@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "cashiers")
public class Cashier extends Person  {

    @Id
    @Column(name = "CIN", length = 255)
    private String cin;

    @ManyToOne
    @JoinColumn(name = "admin_cin", referencedColumnName = "CIN", nullable = false)
    private ProxyAdmin admin;

}
