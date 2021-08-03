/**
 * 
 */
package jama.dao;

import java.util.List;


import jama.model.Student;;

/**
 * @author ajara
 *
 */

public interface StudentDAO {
	

	public List<Student> getStudents();

	public void saveOrUpdate(Student student);
	
	public int save(Student student);

	public Student getStudent(int theId);

	public void deleteStudent(int theId);

	public List<Student> getStudentsByName(String name);

	public void paymentStudent(int theId, String concept, double amount, String details);

	List<Student> getStudentsByStatus(String status);

}
