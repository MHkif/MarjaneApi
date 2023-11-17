package yc.mhkif.marjaneapi.Entities;

import yc.mhkif.marjaneapi.Entities.Abstracts.Person;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private String id;
    @Column(name = "firstName", length = 255, nullable = false)
    private String firstName;

    @Column(name = "lastName", length = 255, nullable = false)
    private String lastName;

    @Column(name = "email", length = 255, nullable = false)
    private String email;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "phone", length = 255, nullable = false)
    private String phone;

}
