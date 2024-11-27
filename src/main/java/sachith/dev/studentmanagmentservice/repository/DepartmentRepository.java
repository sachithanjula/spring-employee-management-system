package sachith.dev.studentmanagmentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sachith.dev.studentmanagmentservice.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {

    Department findByDepartmentId(String departmentId);
}
