package sachith.dev.studentmanagmentservice.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sachith.dev.studentmanagmentservice.entity.Department;
import sachith.dev.studentmanagmentservice.entity.Employee;
import sachith.dev.studentmanagmentservice.mapper.EmployeeMapper;
import sachith.dev.studentmanagmentservice.repository.DepartmentRepository;
import sachith.dev.studentmanagmentservice.repository.EmployeeRepository;
import sachith.dev.studentmanagmentservice.response.CommonJsonResponse;
import sachith.dev.studentmanagmentservice.service.EmployeeService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger logger = LogManager.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public ResponseEntity<CommonJsonResponse> createEmployee(EmployeeMapper employeeMapper) {
        logger.info("createEmployee");

        CommonJsonResponse commonJsonResponse = new CommonJsonResponse();
        ResponseEntity<CommonJsonResponse> responseEntity = null;

        Employee employee = new Employee();

        try {

            Department byDepartmentId = departmentRepository.findByDepartmentId(employeeMapper.getDepartmentCode());

            if (byDepartmentId != null) {
                employee.setEmployeeId(employeeMapper.getEmployeeId());
                employee.setName(employeeMapper.getName());
                employee.setEmail(employeeMapper.getEmail());
                employee.setAddress(employeeMapper.getAddress());
                employee.setTelephone(employeeMapper.getTelephone());
                employee.setEmail(employeeMapper.getEmail());
                employee.setDepartment(byDepartmentId);
                employee.setCreatedDate(new Date());
                employee.setEmpStatus("A");
                employeeRepository.save(employee);

                commonJsonResponse.setSuccess(Boolean.TRUE);
                commonJsonResponse.setData("Employee created successfully");
                responseEntity = new ResponseEntity<>(commonJsonResponse, HttpStatus.CREATED);

            } else {
                commonJsonResponse.setSuccess(Boolean.FALSE);
                commonJsonResponse.setData("Department not found");
                responseEntity = new ResponseEntity<>(commonJsonResponse, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            logger.error("createEmployee Error: "+e);
            commonJsonResponse.setSuccess(Boolean.FALSE);
            commonJsonResponse.setData(e.getMessage());
            responseEntity = new ResponseEntity<>(commonJsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return responseEntity;
    }

    @Override
    public ResponseEntity<CommonJsonResponse> getAllEmplyees() {
        logger.info("getAllEmployees");
        CommonJsonResponse commonJsonResponse = new CommonJsonResponse();
        ResponseEntity<CommonJsonResponse> responseEntity = null;
        List<EmployeeMapper> employeeMappers = new ArrayList<>();

        try {
            employeeRepository.findAll().forEach(employee -> {
                EmployeeMapper employeeMapper = new EmployeeMapper();
                employeeMapper.setEmployeeId(employee.getEmployeeId());
                employeeMapper.setName(employee.getName());
                employeeMapper.setEmail(employee.getEmail());
                employeeMapper.setAddress(employee.getAddress());
                employeeMapper.setTelephone(employee.getTelephone());
                employeeMapper.setId(employee.getId());
                employeeMapper.setDepartmentCode(employee.getDepartment().getDepartmentId());
                employeeMapper.setEmpStatus(employee.getEmpStatus());
                employeeMapper.setCreatedDate(employee.getCreatedDate());
                employeeMappers.add(employeeMapper);
            });

            if (employeeMappers.isEmpty()) {
                commonJsonResponse.setSuccess(Boolean.FALSE);
                commonJsonResponse.setData("Employee list is empty");
                responseEntity = new ResponseEntity<>(commonJsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                commonJsonResponse.setSuccess(Boolean.TRUE);
                commonJsonResponse.setDataList(employeeMappers);
                responseEntity = new ResponseEntity<>(commonJsonResponse, HttpStatus.OK);
            }

        } catch (Exception e) {
            logger.error("getAllEmployees Error: "+e);
            commonJsonResponse.setSuccess(Boolean.FALSE);
            commonJsonResponse.setData(e.getMessage());
            responseEntity = new ResponseEntity<>(commonJsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<CommonJsonResponse> getEmployeeById(String employeeId) {
        logger.info("getEmployeeById: "+employeeId);
        CommonJsonResponse commonJsonResponse = new CommonJsonResponse();
        ResponseEntity<CommonJsonResponse> responseEntity = null;
        EmployeeMapper employeeMapper = new EmployeeMapper();
        try {
            if (employeeId != null && !employeeId.isEmpty()) {
                Employee byEmployeeId = employeeRepository.findByEmployeeId(employeeId);

                if (byEmployeeId != null) {
                    employeeMapper.setEmployeeId(byEmployeeId.getEmployeeId());
                    employeeMapper.setName(byEmployeeId.getName());
                    employeeMapper.setEmail(byEmployeeId.getEmail());
                    employeeMapper.setAddress(byEmployeeId.getAddress());
                    employeeMapper.setTelephone(byEmployeeId.getTelephone());
                    employeeMapper.setId(byEmployeeId.getId());
                    employeeMapper.setDepartmentCode(byEmployeeId.getDepartment().getDepartmentId());
                    employeeMapper.setEmpStatus(byEmployeeId.getEmpStatus());
                    employeeMapper.setCreatedDate(byEmployeeId.getCreatedDate());

                    commonJsonResponse.setSuccess(Boolean.TRUE);
                    commonJsonResponse.setData(employeeMapper);
                    responseEntity = new ResponseEntity<>(commonJsonResponse, HttpStatus.OK);

                } else {
                    commonJsonResponse.setSuccess(Boolean.FALSE);
                    commonJsonResponse.setData("Employee is not available");
                    responseEntity = new ResponseEntity<>(commonJsonResponse, HttpStatus.BAD_REQUEST);
                }

            } else {
                commonJsonResponse.setSuccess(Boolean.FALSE);
                commonJsonResponse.setData("Employee code is not available");
                responseEntity = new ResponseEntity<>(commonJsonResponse, HttpStatus.BAD_REQUEST);
            }


        } catch (Exception e) {
            logger.error("getEmployeeById Error: "+e);
            commonJsonResponse.setSuccess(Boolean.FALSE);
            commonJsonResponse.setData(e.getMessage());
            responseEntity = new ResponseEntity<>(commonJsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @Override
    public ResponseEntity<CommonJsonResponse> updateEmployee(EmployeeMapper employeeMapper) {
        logger.info("updateEmployee: "+employeeMapper.getEmployeeId());
        CommonJsonResponse commonJsonResponse = new CommonJsonResponse();
        ResponseEntity<CommonJsonResponse> responseEntity = null;

        try {

            if (employeeMapper.getEmployeeId() != null && !employeeMapper.getEmployeeId().isEmpty()) {
                Employee employee = employeeRepository.findByEmployeeId(employeeMapper.getEmployeeId());

                if (employee != null) {
                    employee.setName(employeeMapper.getName());
                    employee.setEmail(employeeMapper.getEmail());
                    employee.setAddress(employeeMapper.getAddress());
                    employee.setTelephone(employeeMapper.getTelephone());
                    employeeRepository.save(employee);

                    commonJsonResponse.setSuccess(Boolean.TRUE);
                    commonJsonResponse.setData("Employee updated successfully");
                    responseEntity = new ResponseEntity<>(commonJsonResponse, HttpStatus.OK);

                } else {
                    commonJsonResponse.setSuccess(Boolean.FALSE);
                    commonJsonResponse.setData("Employee not available");
                    responseEntity = new ResponseEntity<>(commonJsonResponse, HttpStatus.BAD_REQUEST);
                }

            } else {
                commonJsonResponse.setSuccess(Boolean.FALSE);
                commonJsonResponse.setData("Employee code is not available");
                responseEntity = new ResponseEntity<>(commonJsonResponse, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            logger.error("updateEmployee Error: "+e);
            commonJsonResponse.setSuccess(Boolean.FALSE);
            commonJsonResponse.setData(e.getMessage());
            responseEntity = new ResponseEntity<>(commonJsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<CommonJsonResponse> deactivateEmployee(String employeeId) {
        logger.info("deactivateEmployee: "+employeeId);
        CommonJsonResponse commonJsonResponse = new CommonJsonResponse();
        ResponseEntity<CommonJsonResponse> responseEntity = null;

        try {
            if (employeeId != null && !employeeId.isEmpty()) {
                Employee employee = employeeRepository.findByEmployeeId(employeeId);

                if (employee != null) {
                    employee.setEmpStatus("I");
                    employeeRepository.save(employee);

                    commonJsonResponse.setSuccess(Boolean.TRUE);
                    commonJsonResponse.setData("Employee deactivate successfully");
                    responseEntity = new ResponseEntity<>(commonJsonResponse, HttpStatus.OK);
                } else {
                    commonJsonResponse.setSuccess(Boolean.FALSE);
                    commonJsonResponse.setData("Employee not available");
                    responseEntity = new ResponseEntity<>(commonJsonResponse, HttpStatus.BAD_REQUEST);
                }

            } else {
                commonJsonResponse.setSuccess(Boolean.FALSE);
                commonJsonResponse.setData("Employee code is not available");
                responseEntity = new ResponseEntity<>(commonJsonResponse, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            logger.error("deactivateEmployee Error: "+e);
            commonJsonResponse.setSuccess(Boolean.FALSE);
            commonJsonResponse.setData(e.getMessage());
            responseEntity = new ResponseEntity<>(commonJsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
