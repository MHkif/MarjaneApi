package yc.mhkif.marjaneapi.Entities;

import yc.mhkif.marjaneapi.Entities.Abstracts.Person;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "proxiesAdmin")
public class ProxyAdmin  extends Person{

    @Id
    @Column(name = "CIN", length = 255)
    private String cin;

    @ManyToOne
    @JoinColumn(name = "superAdmin_id", referencedColumnName = "CIN", nullable = false)
    private SuperAdmin superAdmin;

}