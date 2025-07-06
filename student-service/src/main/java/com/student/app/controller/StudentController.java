package com.student.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.app.entity.Student;
import com.student.app.request.CreateStudentRequest;
import com.student.app.response.StudentResponse;
import com.student.app.service.StudentService;

import jakarta.ws.rs.InternalServerErrorException;

@RestController
@RequestMapping("api/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/create")
	public ResponseEntity<StudentResponse> createStudent(@RequestBody CreateStudentRequest request) {
		try {
			StudentResponse response = studentService.createStudent(request);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (Exception ex) {
			throw new InternalServerErrorException("Internal Server Error "+ ex.getMessage());
		}
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<StudentResponse> getStudentById(@PathVariable long id) {
        try {
            StudentResponse response = studentService.getStudentById(id);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            throw new InternalServerErrorException("Failed to fetch student by "+  ex.getMessage());
        }
    }
	
	@GetMapping("/getStudents")
	public ResponseEntity<List<Student>> getStudentList() {
		List<Student> student = studentService.getStudentList();
		if (student.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(student);

	}

}
