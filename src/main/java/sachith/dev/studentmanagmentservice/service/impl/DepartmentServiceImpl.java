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
            department.setDepartmentStatus("A");
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
                departmentMapper.setDepartmentStatus(department.getDepartmentStatus());
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

    @Override
    public ResponseEntity<CommonJsonResponse> getDepartmentByDepartmentId(String departmentId) {
        logger.info("getDepartmentByDepartmentId: "+departmentId);
        CommonJsonResponse commonJsonResponse = new CommonJsonResponse();
        ResponseEntity<CommonJsonResponse> responseEntity = null;
        DepartmentMapper departmentMapper = new DepartmentMapper();

        try {
            if (departmentId != null && !departmentId.isEmpty()) {
                Department department = departmentRepository.findByDepartmentId(departmentId);
                if (department != null) {
                    departmentMapper.setId(department.getId());
                    departmentMapper.setDepartmentName(department.getDepartmentName());
                    departmentMapper.setCreateDate(department.getCreateDate());

                    commonJsonResponse.setSuccess(Boolean.TRUE);
                    commonJsonResponse.setData(departmentMapper);
                    responseEntity = new ResponseEntity<>(commonJsonResponse, HttpStatus.OK);
                } else {
                    commonJsonResponse.setSuccess(Boolean.FALSE);
                    commonJsonResponse.setData("Department code is not available");
                    responseEntity = new ResponseEntity<>(commonJsonResponse, HttpStatus.BAD_REQUEST);
                }
            } else {
                commonJsonResponse.setSuccess(Boolean.FALSE);
                commonJsonResponse.setData("Department code is not empty/NULL");
                responseEntity = new ResponseEntity<>(commonJsonResponse, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            logger.error("getDepartmentByDepartmentId Error: "+e.getMessage());
            commonJsonResponse.setSuccess(Boolean.FALSE);
            commonJsonResponse.setData(e.getMessage());
            responseEntity = new ResponseEntity<>(commonJsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @Override
    public ResponseEntity<CommonJsonResponse> updateDepartment(DepartmentMapper departmentMapper) {
        logger.info("updateDepartment");
        CommonJsonResponse commonJsonResponse = new CommonJsonResponse();
        ResponseEntity<CommonJsonResponse> responseEntity = null;

        try {
            if (departmentMapper.getDepartmentId() != null && !departmentMapper.getDepartmentId().isEmpty()) {
                Department byDepartmentId = departmentRepository.findByDepartmentId(departmentMapper.getDepartmentId());

                if (byDepartmentId != null) {
                    byDepartmentId.setDepartmentName(departmentMapper.getDepartmentName());
                    departmentRepository.save(byDepartmentId);

                    commonJsonResponse.setSuccess(Boolean.TRUE);
                    commonJsonResponse.setData(byDepartmentId);
                    responseEntity = new ResponseEntity<>(commonJsonResponse, HttpStatus.OK);
                } else {
                    commonJsonResponse.setSuccess(Boolean.FALSE);
                    commonJsonResponse.setData("Department code is not available");
                    responseEntity = new ResponseEntity<>(commonJsonResponse, HttpStatus.BAD_REQUEST);
                }

            } else {
                commonJsonResponse.setSuccess(Boolean.FALSE);
                commonJsonResponse.setData("Department code is not empty/NULL");
                responseEntity = new ResponseEntity<>(commonJsonResponse, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            logger.error("updateDepartment Error: "+e.getMessage());
            commonJsonResponse.setSuccess(Boolean.FALSE);
            commonJsonResponse.setData(e.getMessage());
            responseEntity = new ResponseEntity<>(commonJsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }

    @Override
    public ResponseEntity<CommonJsonResponse> deactivateDepartment(String departmentId) {
        logger.info("deactivateDepartment: "+departmentId);
        CommonJsonResponse commonJsonResponse = new CommonJsonResponse();
        ResponseEntity<CommonJsonResponse> responseEntity = null;

        try {
            if (departmentId != null && !departmentId.isEmpty()) {
                Department byDepartmentId = departmentRepository.findByDepartmentId(departmentId);

                if (byDepartmentId != null) {
                    byDepartmentId.setDepartmentStatus("I");
                    departmentRepository.save(byDepartmentId);

                    commonJsonResponse.setSuccess(Boolean.TRUE);
                    commonJsonResponse.setData(byDepartmentId);
                    responseEntity = new ResponseEntity<>(commonJsonResponse, HttpStatus.OK);
                } else {
                    commonJsonResponse.setSuccess(Boolean.FALSE);
                    commonJsonResponse.setData("Department code is not available");
                    responseEntity = new ResponseEntity<>(commonJsonResponse, HttpStatus.BAD_REQUEST);
                }

            } else {
                commonJsonResponse.setSuccess(Boolean.FALSE);
                commonJsonResponse.setData("Department code is not empty/NULL");
                responseEntity = new ResponseEntity<>(commonJsonResponse, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            logger.error("deactivateDepartment Error: "+e.getMessage());
            commonJsonResponse.setSuccess(Boolean.FALSE);
            commonJsonResponse.setData(e.getMessage());
            responseEntity = new ResponseEntity<>(commonJsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return responseEntity;
    }
}
