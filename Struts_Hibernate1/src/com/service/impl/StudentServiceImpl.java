package com.service.impl;

import java.util.List;

import com.dao.impl.StudentDaoImpl;
import com.entity.Student;
import com.service.StudentService;

public class StudentServiceImpl implements StudentService {
	
	private int totalCount;
	private List<Student> allStudents;
	
	public List<Student> getAllStudents() {
		return allStudents;
	}

	public void setAllStudents(List<Student> allStudents) {
		this.allStudents = allStudents;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	public String index() {
		StudentDaoImpl sdao = new StudentDaoImpl();
		totalCount = sdao.getTotalCount();
		allStudents = sdao.getAllStudents();
		return "index";
	}

}
