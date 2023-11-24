package yc.mhkif.marjaneapi.Entities.Abstracts;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class Person {
    @Column(name = "first_name", length = 255, nullable = false)
    protected String firstName;

    @Column(name = "last_name", length = 255, nullable = false)
    protected String lastName;

    @Column(name = "email", length = 255, nullable = false)
    protected String email;

    @Column(name = "password", length = 255, nullable = false)
    protected String password;

    @Column(name = "phone", length = 255, nullable = false)
    protected String phone;
}
