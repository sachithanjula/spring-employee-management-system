package sachith.dev.studentmanagmentservice.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sachith.dev.studentmanagmentservice.mapper.EmployeeMapper;
import sachith.dev.studentmanagmentservice.response.CommonJsonResponse;
import sachith.dev.studentmanagmentservice.service.EmployeeService;

@RestController
@RequestMapping("api/employee")
public class EmployeeWebController {
    private static final Logger logger = LogManager.getLogger(EmployeeWebController.class);

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeWebController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/create")
    public ResponseEntity<CommonJsonResponse> createEmployee(@RequestBody EmployeeMapper employeeMapper) {
        logger.info("createEmployee");
        return employeeService.createEmployee(employeeMapper);
    }

    @GetMapping("/getAll")
    public ResponseEntity<CommonJsonResponse> getAllEmployees() {
        logger.info("getAllEmployees");
        return employeeService.getAllEmplyees();
    }

    @PostMapping("/getEmployeeById")
    public ResponseEntity<CommonJsonResponse> getEmployeeById(@RequestBody EmployeeMapper employeeMapper) {
        logger.info("getEmployeeById");
        return employeeService.getEmployeeById(employeeMapper.getEmployeeId());
    }

    @PutMapping("/update")
    public ResponseEntity<CommonJsonResponse> updateEmployee(@RequestBody EmployeeMapper employeeMapper) {
        logger.info("createEmployee");
        return employeeService.updateEmployee(employeeMapper);
    }

    @PostMapping("/deactivate")
    public ResponseEntity<CommonJsonResponse> deactivate(@RequestBody EmployeeMapper employeeMapper) {
        logger.info("createEmployee");
        return employeeService.deactivateEmployee(employeeMapper.getEmployeeId());
    }

}
