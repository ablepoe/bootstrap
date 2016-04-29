package com.dao;

import java.util.List;

import com.entity.Student;

public interface StudentDao {

	public int getTotalCount();
	
	public List<Student> getAllStudents();
	
}
