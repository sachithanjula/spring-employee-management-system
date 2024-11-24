package sachith.dev.studentmanagmentservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sachith.dev.studentmanagmentservice.entity.Department;
import sachith.dev.studentmanagmentservice.mapper.DepartmentMapper;
import sachith.dev.studentmanagmentservice.repository.DepartmentRepository;
import sachith.dev.studentmanagmentservice.response.CommonJsonResponse;
import sachith.dev.studentmanagmentservice.service.DepartmentService;

import java.util.Date;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public ResponseEntity<CommonJsonResponse> createDepartment(DepartmentMapper departmentMapper) {
        System.out.println("createDepartment");
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
            System.out.println(e.getMessage());
            commonJsonResponse.setSuccess(Boolean.FALSE);
            commonJsonResponse.setData(e.getMessage());
            responseEntity = new ResponseEntity<>(commonJsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return responseEntity;
    }
}
