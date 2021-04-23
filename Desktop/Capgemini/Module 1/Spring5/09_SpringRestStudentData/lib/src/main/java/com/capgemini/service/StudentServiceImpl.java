package com.capgemini.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.capgemini.exceptions.NoSuchStudentException;
import com.capgemini.model.Student;
import com.capgemini.repository.StudentRepository;
@Service("service")
@Scope("singleton")
public class StudentServiceImpl implements StudentService{
	@Autowired
	private StudentRepository repository;
	public StudentServiceImpl(StudentRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Student addStudent(Student student) {
		return repository.save(student);
	}

	@Override
	public Student findStudentById(int studentId) throws NoSuchStudentException {
	Student student= repository.findByStudentId(studentId);
	System.out.println(student);
	if(student==null)
	{
		throw new NoSuchStudentException("No Student exist with given Id : "+studentId);
	}
	return student;
	}

	@Override
	public List<Student> findAllStudents() {
		return repository.findAll();
	}

	@Override
	public Student modifyStudent(Student student) {
		return repository.save(student);
	}

	@Override
	public boolean removeStudent(int studentId) throws NoSuchStudentException {
		 repository.deleteById(studentId);
		 return true;
	}

	@Override
	public List<Student> findStudentByScore(double min, double max) {
		return repository.findStudentByScore(min,max);			      
	}

	@Override
	public List<Student> findStudentByName(String studentName) {
		return repository.findStudentByStudentName(studentName);
	}
	

}
