package sachith.dev.studentmanagmentservice.mapper;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class EmployeeMapper {

    private int id;
    private String employeeId;
    private String name;
    private String address;
    private String email;
    private String telephone;
    private Date createdDate;
    private DepartmentMapper departmentMapper;
    private String empStatus;
    private String departmentCode;
}
