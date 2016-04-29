package com.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dao.StudentDao;
import com.db.MyHibernateUtil;
import com.entity.Student;

public class StudentDaoImpl implements StudentDao {

	@Override
	public int getTotalCount() {
		Session session = MyHibernateUtil.getMyHibernateUtilInstance().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "from Student s";
		List<?> li = session.createQuery(hql).list();
		tx.commit();
		session.close();
		return li.size();
	}

	@Override
	public List<Student> getAllStudents() {
		Session session = MyHibernateUtil.getMyHibernateUtilInstance().openSession();
		Transaction tx = session.beginTransaction();
		String hql = "from Student s";
		List<?> li = session.createQuery(hql).list();
		tx.commit();
		session.close();
		return (List<Student>) li;
	}

}
