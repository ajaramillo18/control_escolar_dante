/**
 * 
 */
package jama.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jama.dao.CourseDAO;
import jama.model.Course;

/**
 * @author ajara
 *
 */
@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseDAO courseDAO;
	
	
	
	@Override
	@Transactional
	public List<Course> getCourses() {
		List<Course> lista =  courseDAO.getCourses();//new ArrayList<>();
		
		/*Course course1 = new Course();
		course1.setId(1);
		course1.setFirstName("Armando");
		course1.setLastName("Jaramillo");
		course1.setEmail("ajaramillo18@hotmail.com");
		lista.add(course1);
		Course course2 = new Course();
		course2.setId(2);
		course2.setFirstName("Angela");
		course2.setLastName("Magallon");
		course2.setEmail("gelamagallona@hotmail.com");
		lista.add(course2);
		Course course3 = new Course();
		course3.setId(3);
		course3.setFirstName("Dante");
		course3.setLastName("Dali");
		course3.setEmail("dddd@hotmail.com");
		lista.add(course3);*/
		return lista;
	}

	@Override
	@Transactional
	public void save(Course course) {
		courseDAO.save(course);
		
	}

	@Override
	@Transactional
	public Course getCourse(int theId) {
		
		Course course = courseDAO.getCourse(theId);
		
		return course;
		
	}

	@Override
	@Transactional
	public void deleteCourse(int theId) {
		 
		courseDAO.deleteCourse(theId);
		
	}

	@Override
	@Transactional
	public List<Course> getCoursesByName(String name) {
		
		List<Course> lista =  courseDAO.getCoursesByName(name);
		
		return lista;
	}

	
}
