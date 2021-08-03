/**
 * 
 */
package jama.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import jama.service.CourseService;
import jama.service.StudentService;
import lombok.Getter;
import jama.model.Course;
import jama.model.Student;






/**
 * @author ajara
 *
 */
@Controller
@RequestMapping ("/student")
public class StudentController {
	
	
	// need to inject our customer service
	@Autowired	
	private StudentService studentService;
	
	@Autowired	
	private CourseService courseService;
	
	@Value("#{'${pago.conceptos}'.split(',')}")
	private String[] conceptos;

	@RequestMapping ("/list") //@GetMapping("/get/{id}")
	public String listStudents(Model model) {
		
		// get customers from the service
		List<Student> studentList = studentService.getStudents();
							
		// add the customers to the model
		model.addAttribute("students", studentList);
		return "list-students";
		
	}
	@GetMapping("/showFormForAdd")
	public String addStudent(Model model) {
		
		Student newStudent = new Student();
		model.addAttribute(newStudent);
		
		
		// get customers from the service
		List<Course> courseList = courseService.getCourses();
		
		// add the customers to the model
		model.addAttribute("allCourses", courseList);
		model.addAttribute("update", false);
		
		return "student-form";
	}
	
	@PostMapping("/saveStudent")
	public String saveStudent(@ModelAttribute("student") @Valid Student student, BindingResult bindingResult, Model model) {
		
		//return to form if there are validation errors
		if(bindingResult.hasErrors()) {
			
			// get customers from the service
			List<Course> courseList = courseService.getCourses();
			
			// add the customers to the model
			model.addAttribute("allCourses", courseList);
			return  "student-form";
		}
			
		
		studentService.save(student);
		
		return "redirect:/student/list";
		
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int theId,
									Model theModel) {
		
		// get the student from our service
		Student student = studentService.getStudent(theId);	
		
		// set student as a model attribute to pre-populate the form
		theModel.addAttribute("student", student);
		theModel.addAttribute("update", true);
		List<Course> courseList = courseService.getCourses();
		theModel.addAttribute("allCourses", courseList);
		
		// send over to our form		
		return "student-form";
	}
	
	@GetMapping("/delete")
	public String deleteStudent(@RequestParam("studentId") int theId) {
		
		// delete the customer
		studentService.deleteStudent(theId);
		
		return "redirect:/student/list";
	}
	
	@PostMapping("/payment")
	public String paymentStudent(@ModelAttribute("student") Student student, @RequestParam("id") Integer theId) {
		
		// delete the customer
		String concept ="";
		studentService.paymentStudent(student.getId(), student.getCourse(), student.getTuition(), student.getDetail());
		
		return "redirect:/student/list";
	}
	
	@GetMapping("/search")
	public String searchStudent(@RequestParam("name") String name, Model model) {
		
		// get customers from the service
		List<Student> studentList = studentService.getStudentsByName(name);
									
		// add the customers to the model
		model.addAttribute("students", studentList);
		return "list-students";
		
	}
	
	@GetMapping("/showFormForPayment")
	public String showFormForPayment(@RequestParam("studentId") int id, Model model) {
		
		// get the student from our service
		Student student = studentService.getStudent(id);	
		
		
		// pre fill the current date in details
		
		Locale espanol = new Locale("es","ES");
		String pattern = "MMMM yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern,espanol);
		String date = simpleDateFormat.format(new Date());		
		student.setDetail(date.toUpperCase());
		
				
		// set student as a model attribute to pre-populate the form
		model.addAttribute("student", student);
		model.addAttribute("update", true);
		String[] allConcepts = conceptos;
		model.addAttribute("allConcepts", allConcepts);	
		
		
				
		
		return "student-payment";
	}
		
		
	
}
