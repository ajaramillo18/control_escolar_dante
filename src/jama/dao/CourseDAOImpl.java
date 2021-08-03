/**
 * 
 */
package jama.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import jama.model.Course;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



/**
 * @author ajara
 *
 */



@Repository
public class CourseDAOImpl implements CourseDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override	
	public List<Course> getCourses() {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.getNamedQuery("course.getCourses");
		List<Course> lista = query.list();			
		
		return lista;
	}

	@Override
	public void save(Course course) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(course);
		
	}

	@Override
	public Course getCourse(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Course course = currentSession.get(Course.class, id);

		return course;
	}

	@Override
	public void deleteCourse(int id) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		Course course = currentSession.get(Course.class, id);
		currentSession.delete(course);	
		
	}

	@Override
	public List<Course> getCoursesByName(String name) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query query = currentSession.getNamedQuery("course.getCoursesByName");
		query.setString(0, name);
		List<Course> lista = query.list();		
		return lista;
		
	}

	
}
