/**
 * 
 */
package jama.dao;

import java.util.List;


import jama.model.Course;;

/**
 * @author ajara
 *
 */

public interface CourseDAO {
	

	public List<Course> getCourses();

	public void save(Course course);

	public Course getCourse(int theId);

	public void deleteCourse(int theId);

	public List<Course> getCoursesByName(String name);

	

}
