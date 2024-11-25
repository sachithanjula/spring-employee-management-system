package sachith.dev.studentmanagmentservice.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sachith.dev.studentmanagmentservice.entity.Department;
import sachith.dev.studentmanagmentservice.mapper.DepartmentMapper;
import sachith.dev.studentmanagmentservice.repository.DepartmentRepository;
import sachith.dev.studentmanagmentservice.response.CommonJsonResponse;
import sachith.dev.studentmanagmentservice.service.DepartmentService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    private static final Logger logger = LogManager.getLogger(DepartmentServiceImpl.class);

    @Override
    public ResponseEntity<CommonJsonResponse> createDepartment(DepartmentMapper departmentMapper) {
        logger.info("createDepartment");
        Department department = new Department();
        CommonJsonResponse commonJsonResponse = new CommonJsonResponse();
        ResponseEntity<CommonJsonResponse> responseEntity = null;

        try {

            department.setDepartmentName(departmentMapper.getDepartmentName());
            department.setDepartmentId(departmentMapper.getDepartmentId());
            department.setCreateDate(new Date());
            Department saveDepartment = departmentRepository.save(department);

            commonJsonResponse.setSuccess(Boolean.TRUE);
            commonJsonResponse.setData(saveDepartment);
            responseEntity = new ResponseEntity<>(commonJsonResponse, HttpStatus.CREATED);

        } catch (Exception e) {
            logger.error("createDepartment Error: "+e.getMessage());
            commonJsonResponse.setSuccess(Boolean.FALSE);
            commonJsonResponse.setData(e.getMessage());
            responseEntity = new ResponseEntity<>(commonJsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return responseEntity;
    }

    @Override
    public ResponseEntity<CommonJsonResponse> getAllDepartments() {
        logger.info("getAllDepartments");
        CommonJsonResponse commonJsonResponse = new CommonJsonResponse();
        List<DepartmentMapper> departmentMappers = new ArrayList<>();
        ResponseEntity<CommonJsonResponse> responseEntity = null;

        try {
            departmentRepository.findAll().forEach(department -> {
                DepartmentMapper departmentMapper = new DepartmentMapper();
                departmentMapper.setDepartmentId(department.getDepartmentId());
                departmentMapper.setDepartmentName(department.getDepartmentName());
                departmentMapper.setCreateDate(department.getCreateDate());
                departmentMappers.add(departmentMapper);
            });

            if (departmentMappers.isEmpty()) {
                commonJsonResponse.setSuccess(Boolean.FALSE);
                commonJsonResponse.setData("Department list is empty");
                responseEntity = new ResponseEntity<>(commonJsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                commonJsonResponse.setSuccess(Boolean.TRUE);
                commonJsonResponse.setDataList(departmentMappers);
                responseEntity = new ResponseEntity<>(commonJsonResponse, HttpStatus.OK);
            }
        } catch (Exception e) {
            logger.error("getAllDepartments Error: "+e.getMessage());
            commonJsonResponse.setSuccess(Boolean.FALSE);
            commonJsonResponse.setData(e.getMessage());
            responseEntity = new ResponseEntity<>(commonJsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }
}
