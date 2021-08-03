/**
 * 
 */
package jama.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import jama.model.Course;






/**
 * @author ajara
 *
 */
@Controller
@RequestMapping ("/course")
public class CourseController {
	
	
	// need to inject our customer service
	@Autowired	
	private CourseService courseService;

	@RequestMapping ("/list") //@GetMapping("/get/{id}")
	public String listCourses(Model model) {
		
		// get customers from the service
		List<Course> courseList = courseService.getCourses();
							
		// add the customers to the model
		model.addAttribute("courses", courseList);
		return "list-courses";
		
	}
	@GetMapping("/showFormForAdd")
	public String addCourse(Model model) {
		
		Course newCourse = new Course();
		model.addAttribute(newCourse);
		return "course-form";
	}
	
	@PostMapping("/saveCourse")
	public String saveCourse(@ModelAttribute("course") @Valid Course course, BindingResult bindingResult) {
		
		//return to form if there are validation errors
		if(bindingResult.hasErrors())
			return  "course-form";
		
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXX");
		courseService.save(course);
		
		return "redirect:/course/list";
		
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("courseId") int theId,
									Model theModel) {
		
		// get the customer from our service
		Course course = courseService.getCourse(theId);	
		
		// set customer as a model attribute to pre-populate the form
		theModel.addAttribute("course", course);
		
		// send over to our form		
		return "course-form";
	}
	
	@GetMapping("/delete")
	public String deleteCourse(@RequestParam("courseId") int theId) {
		
		// delete the customer
		courseService.deleteCourse(theId);
		
		return "redirect:/course/list";
	}
	
	
	
	@GetMapping("/search")
	public String searchCourse(@RequestParam("name") String name, Model model) {
		
		// get customers from the service
		List<Course> courseList = courseService.getCoursesByName(name);
									
		// add the customers to the model
		model.addAttribute("courses", courseList);
		return "list-courses";
		
	}
		
		
	
}
