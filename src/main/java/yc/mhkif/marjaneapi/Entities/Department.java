package yc.mhkif.marjaneapi.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    //@OneToOne
    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "CIN", nullable = false)
    private Manager manager;

    @ManyToOne
    @JoinColumn(name = "center_id", nullable = false)
    private Center center;


}