package sachith.dev.studentmanagmentservice.service;

import org.springframework.http.ResponseEntity;
import sachith.dev.studentmanagmentservice.entity.Department;
import sachith.dev.studentmanagmentservice.mapper.DepartmentMapper;
import sachith.dev.studentmanagmentservice.response.CommonJsonResponse;

public interface DepartmentService {
    ResponseEntity<CommonJsonResponse> createDepartment(DepartmentMapper departmentMapper);
    ResponseEntity<CommonJsonResponse> getAllDepartments();

}
