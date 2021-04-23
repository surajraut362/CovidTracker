package com.capgemini.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.capgemini.exceptions.NoSuchStudentException;
import com.capgemini.model.Student;
import com.capgemini.service.StudentService;
@RestController
@ControllerAdvice
@RequestMapping(path = "students")
public class StudentController2 extends ResponseEntityExceptionHandler {
	@Autowired
	private StudentService service;
	
	//http://localhost:9090/students/
	@GetMapping(path = "/byId/{studentId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Student> getStudentById(@PathVariable("studentId")int studentId) throws NoSuchStudentException 
	{
		ResponseEntity<Student> response=null;
		
		 Student result= service.findStudentById(studentId); 
		 if(result!=null)
			 response=new ResponseEntity<Student>(result,HttpStatus.OK); 
		 else 
			 response=new ResponseEntity<Student>(HttpStatus.BAD_REQUEST); 
		 return response;
		 
		
	}
	@GetMapping(path = "/byName/{studentName}",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Student>> getStudentByName(@PathVariable("studentName")String studentName) throws NoSuchStudentException
	{
		ResponseEntity<List<Student>> response=null;
		
		List<Student>  result= service.findStudentByName(studentName); 
		 if(result!=null)
			 response=new ResponseEntity<List<Student>>(result,HttpStatus.OK); 
		 else 
			 response=new ResponseEntity<List<Student>>(HttpStatus.BAD_REQUEST); 
		 return response;
	}
	@GetMapping(path = "/byScore",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Student>> getStudentByScore(@RequestParam("min") int min,@RequestParam("max") int max)
	{
		ResponseEntity<List<Student>> response=null;
		List<Student>  result= service.findStudentByScore(min, max); 
		 if(result!=null)
			 response=new ResponseEntity<List<Student>>(result,HttpStatus.OK); 
		 else 
			 response=new ResponseEntity<List<Student>>(HttpStatus.BAD_REQUEST); 
		 return response;
	}
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Student> setStudent(@RequestBody Student student) {
		 ResponseEntity<Student> response=null;
		 System.out.println(student);
		 Student result= service.addStudent(student); 
		 if(result!=null)
			 response=new ResponseEntity<Student>(result,HttpStatus.CREATED); 
		 else 
			 response=new ResponseEntity<Student>(HttpStatus.BAD_REQUEST); 
		 return response;
		 }
	@DeleteMapping("{studentId}")
	public ResponseEntity<String> removeStudent(@PathVariable("studentId") int studentId) throws NoSuchStudentException
	{
		ResponseEntity<String> response=null;
		boolean  result= service.removeStudent(studentId);
		 if(result)
			 response=new ResponseEntity<String>("Removed Successfully",HttpStatus.ACCEPTED); 
		 else 
			 response=new ResponseEntity<String>("Not Removed",HttpStatus.BAD_REQUEST); 
		 return response;
	}
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Student> updateStudent(@RequestBody Student student)
	{
		ResponseEntity<Student> response=null;
		Student  result= service.modifyStudent(student);
		 if(result!=null)
			 response=new ResponseEntity<Student>(result,HttpStatus.ACCEPTED); 
		 else 
			 response=new ResponseEntity<Student>(HttpStatus.BAD_REQUEST); 
		 return response;
	}
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Student>> getAllStudents()
	{
ResponseEntity<List<Student>> response=null;
		
		List<Student>  result= service.findAllStudents(); 
		 if(result!=null)
			 response=new ResponseEntity<List<Student>>(result,HttpStatus.ACCEPTED); 
		 else 
			 response=new ResponseEntity<List<Student>>(HttpStatus.BAD_REQUEST); 
		 return response;
	}
	 @ExceptionHandler(value= { NoSuchStudentException.class})
	    public final String handleException(NoSuchStudentException ex) {
	       return  (ex.getMessage()+" Thrown by Global Handler");
}
}

