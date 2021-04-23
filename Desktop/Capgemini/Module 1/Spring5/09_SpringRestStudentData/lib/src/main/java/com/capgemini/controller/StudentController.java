
package com.capgemini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.exceptions.NoSuchStudentException;
import com.capgemini.model.Student;
import com.capgemini.service.StudentService;

@RestController
@RequestMapping(path = "students1")
public class StudentController {

	@Autowired
	private StudentService service; // http://localhost:9090/students/

	@GetMapping(path = "/byId/{studentId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Student getStudentById(@PathVariable("studentId") int studentId) throws NoSuchStudentException {
		return service.findStudentById(studentId);
	}

	@GetMapping(path = "/byName/{studentName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Student> getStudentByName(@PathVariable("studentName") String studentName)
			throws NoSuchStudentException {
		return service.findStudentByName(studentName);
	}

	@GetMapping(path = "/byScore", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Student> getStudentByScore(@RequestParam("min") int min, @RequestParam("max") int max) {
		return service.findStudentByScore(min, max);
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Student> setStudent(@RequestBody Student student) {
		ResponseEntity<Student> response = null;
		System.out.println(student);
		Student result = service.addStudent(student);
		if (result != null)
			response = new ResponseEntity<Student>(HttpStatus.BAD_REQUEST);
		else
			response = new ResponseEntity<Student>(HttpStatus.BAD_REQUEST);
		return response;
	}

	@DeleteMapping("{studentId}")
	public String removeStudent(@PathVariable("studentId") int studentId) throws NoSuchStudentException {
		service.removeStudent(studentId);
		return "Removed Successfully";
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Student updateStudent(@RequestBody Student student) {
		return service.modifyStudent(student);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Student> getAllStudents() {
		return service.findAllStudents();
	}
}
