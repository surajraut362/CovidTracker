package com.capgemini.service;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.exceptions.NoSuchStudentException;
import com.capgemini.model.Student;
import com.capgemini.repository.StudentRepository;
@SpringBootTest
@RunWith(SpringRunner.class)
public class StudentServiceImplTest {
	
	@Autowired
	private StudentService service;
	@Autowired
	private Student student;
	@MockBean
	private StudentRepository repository;
	
	@Test
	 public void testFindStudentByIdShouldReturnStudentObject() throws NoSuchStudentException {
		student.setStudentId(1);
		student.setStudentName("suraj");
		student.setStudentScore(100);
		when(repository.findByStudentId(student.getStudentId())).thenReturn(student);
		when(repository.save(student)).thenReturn(student);
		Student expeStudent=service.addStudent(student);
		Student actStudent=service.findStudentById(expeStudent.getStudentId());
		assertEquals(expeStudent.getStudentId(),actStudent.getStudentId());
		assertEquals(expeStudent.getStudentName(),actStudent.getStudentName());

	}
	@Test
	void testFindStudentByIdShouldThrowNoSuchStudentException() {
		when(repository.findByStudentId(-1)).thenReturn(null);
		assertThrows(NoSuchStudentException.class, ()->{
			service.findStudentById(-1);
		});
	}
}
