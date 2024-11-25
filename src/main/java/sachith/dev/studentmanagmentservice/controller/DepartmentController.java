package sachith.dev.studentmanagmentservice.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sachith.dev.studentmanagmentservice.mapper.DepartmentMapper;
import sachith.dev.studentmanagmentservice.response.CommonJsonResponse;
import sachith.dev.studentmanagmentservice.service.DepartmentService;
import sachith.dev.studentmanagmentservice.service.impl.DepartmentServiceImpl;

@RestController
@RequestMapping("api/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    private static final Logger logger = LogManager.getLogger(DepartmentController.class);

    @PostMapping("/create")
    public ResponseEntity<CommonJsonResponse> createDepartment(@RequestBody DepartmentMapper departmentMapper) {
        logger.info("createDepartment");
        return departmentService.createDepartment(departmentMapper);
    }

    @GetMapping("/getAll")
    public ResponseEntity<CommonJsonResponse> getAllDepartments() {
        logger.info("getAllDepartments");
        return departmentService.getAllDepartments();
    }

}
