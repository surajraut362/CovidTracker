package com.capgemini.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capgemini.model.Student;
@Repository
public interface StudentRepository extends JpaRepositoryImplementation<Student  , Integer> {

	@Query(name = "byScore")
	List<Student> findStudentByScore(@Param("min") double min, @Param("max")double max);

	List<Student> findStudentByStudentName(String studentName);//QueryDSL

	Student findByStudentId(int studentId);
	
}
