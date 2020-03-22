package com.amritesh.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.amritesh.hibernate.entity.demo.Course;
import com.amritesh.hibernate.entity.demo.Instructor;
import com.amritesh.hibernate.entity.demo.InstructorDetail;
import com.amritesh.hibernate.entity.demo.Review;
import com.amritesh.hibernate.entity.demo.Student;

public class CreateCoursesAndStudentsDemo {

	public static void main(String[] args) {
		
		SessionFactory sessionFactory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Instructor.class)
										.addAnnotatedClass(InstructorDetail.class)
										.addAnnotatedClass(Course.class)
										.addAnnotatedClass(Student.class)
										.addAnnotatedClass(Review.class)
										.buildSessionFactory();
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			
			Course course1 = new Course("Sitar");
			session.save(course1);
						
			Student student1 = new Student("John", "Doe", "john@abc.com");
			Student student2 = new Student("Amritesh", "Chandan", "achandan1@yodlee.com");
			Student student3 = new Student("Aman", "Raj", "aRaj@gmail.com");
			
			course1.addStudent(student1);
			course1.addStudent(student2);
			course1.addStudent(student3);
			
			session.save(student1);
			session.save(student2);
			session.save(student3);
			
			System.out.println(course1.getStudents());
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			sessionFactory.close();
		}
		
	}
	
}