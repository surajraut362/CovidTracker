package com.capgemini.service;

import java.util.List;

import com.capgemini.exceptions.NoSuchStudentException;
import com.capgemini.model.Student;

public interface StudentService {
	public Student addStudent(Student student);
	public Student findStudentById(int studentId) throws NoSuchStudentException;
	public List<Student> findAllStudents();
	public Student modifyStudent(Student student);
	public boolean removeStudent(int studentId) throws NoSuchStudentException;
	public List<Student> findStudentByScore(double min, double max) ;
	List<Student> findStudentByName(String studentName);

}
