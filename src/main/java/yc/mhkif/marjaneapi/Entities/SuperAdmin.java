package yc.mhkif.marjaneapi.Entities;

import lombok.*;
import yc.mhkif.marjaneapi.Entities.Abstracts.Person;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "superAdmin")
public class SuperAdmin extends Person {

    @Id
    @Column(name = "CIN", length = 255)
    private String cin;

}