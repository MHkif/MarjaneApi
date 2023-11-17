package yc.mhkif.marjaneapi.Entities;

import lombok.*;
import yc.mhkif.marjaneapi.Entities.Abstracts.Person;
import jakarta.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "managers")
public class Manager extends Person {

    @Id
    @Column(name = "CIN", length = 255)
    private String cin;

    @ManyToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "CIN", nullable = false)
    private ProxyAdmin admin;

}