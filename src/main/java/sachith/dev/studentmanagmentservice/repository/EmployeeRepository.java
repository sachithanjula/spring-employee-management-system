package sachith.dev.studentmanagmentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sachith.dev.studentmanagmentservice.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    Employee findByEmployeeId(String employeeId);
}
