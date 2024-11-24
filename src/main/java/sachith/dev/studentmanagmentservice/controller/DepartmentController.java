package sachith.dev.studentmanagmentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sachith.dev.studentmanagmentservice.entity.Department;
import sachith.dev.studentmanagmentservice.mapper.DepartmentMapper;
import sachith.dev.studentmanagmentservice.response.CommonJsonResponse;
import sachith.dev.studentmanagmentservice.service.DepartmentService;

@RestController
@RequestMapping("api/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/create")
    public ResponseEntity<CommonJsonResponse> createDepartment(@RequestBody DepartmentMapper departmentMapper) {
        System.out.println("createDepartment");
        return departmentService.createDepartment(departmentMapper);
    }

}
