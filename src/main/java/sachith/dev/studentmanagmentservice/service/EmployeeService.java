package sachith.dev.studentmanagmentservice.service;

import org.springframework.http.ResponseEntity;
import sachith.dev.studentmanagmentservice.mapper.EmployeeMapper;
import sachith.dev.studentmanagmentservice.response.CommonJsonResponse;

public interface EmployeeService {
    ResponseEntity<CommonJsonResponse> createEmployee(EmployeeMapper employeeMapper);
    ResponseEntity<CommonJsonResponse> getAllEmplyees();
    ResponseEntity<CommonJsonResponse> getEmployeeById(String employeeId);
    ResponseEntity<CommonJsonResponse> updateEmployee(EmployeeMapper employeeMapper);
    ResponseEntity<CommonJsonResponse> deactivateEmployee(String employeeId);
}
