package sachith.dev.studentmanagmentservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "DEPARTMENT")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "DEPID", unique = true, nullable = false)
    private String departmentId;

    @Column(name = "DEPNAME")
    private String departmentName;

    @Column(name = "CREATEDDATE")
    private Date createDate;

    @OneToMany
    private List<Employee> employees;

}
