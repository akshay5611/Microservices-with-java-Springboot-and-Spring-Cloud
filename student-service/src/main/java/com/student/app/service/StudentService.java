package com.student.app.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.student.app.entity.Student;
import com.student.app.exception.ResourceNotFoundException;
import com.student.app.repository.StudentRespository;
import com.student.app.request.CreateStudentRequest;
import com.student.app.response.StudentResponse;

@Service
public class StudentService {
	
	Logger logger = LoggerFactory.getLogger(StudentService.class);
	
	@Autowired
	private StudentRespository studentRespository;
	
	@Autowired
	ConfigCircuitBreakerCall call;
	
	public StudentResponse createStudent(CreateStudentRequest request) {

		Student student = new Student();
		student.setFirstName(request.getFirstName());
		student.setLastName(request.getLastName());
		student.setEmail(request.getEmail());
		student.setAddressId(request.getAddressId());
		student = studentRespository.save(student);
		
		StudentResponse studentResponse = new StudentResponse(student);
		studentResponse.setAddressResponse(call.getAddressById(student.getAddressId()));

		return studentResponse; 

	}
	
	public StudentResponse getStudentById(long id) {
		logger.info("inside getStudentById " + id);
		Student student = studentRespository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + id));
		StudentResponse studentResponse = new StudentResponse(student);
		studentResponse.setAddressResponse(call.getAddressById(student.getAddressId()));
		return studentResponse;
	}
	
	public List<Student> getStudentList() {
		return studentRespository.findAll();
	}


}
