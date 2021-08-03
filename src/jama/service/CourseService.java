/**
 * 
 */
package jama.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jama.model.Course;

/**
 * @author ajara
 *
 */

public interface CourseService {

	public List<Course> getCourses();

	public void save(Course course);

	public Course getCourse(int theId);

	public void deleteCourse(int theId);

	public List<Course> getCoursesByName(String name);


}
