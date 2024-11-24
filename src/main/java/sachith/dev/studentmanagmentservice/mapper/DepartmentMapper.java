package sachith.dev.studentmanagmentservice.mapper;

import lombok.Getter;
import lombok.Setter;
import sachith.dev.studentmanagmentservice.entity.Employee;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class DepartmentMapper {


    private int id;
    private String departmentId;
    private String departmentName;
    private Date createDate;
    private List<Employee> employees;

}
