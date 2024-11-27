package sachith.dev.studentmanagmentservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "EMPID", unique = true, nullable = false)
    private String employeeId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "TELEPHONE")
    private String telephone;

    @Column(name = "CREATEDDATE")
    private Date createdDate;

    @Column(name = "EMP_STATUS")
    private String empStatus;

    @ManyToOne
    @JoinColumn(name = "DEPID")
    private Department department;

}
