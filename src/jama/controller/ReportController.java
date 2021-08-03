/**
 * 
 */
package jama.controller;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
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
import jama.service.StudentService;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;

import jama.model.Course;
import jama.model.Student;






/**
 * @author ajara
 *
 */
@Controller
@RequestMapping ("/report")
public class ReportController {
	
	
	// need to inject our customer service
	@Autowired	
	private CourseService courseService;
	
	@Autowired	
	private StudentService studentService;

	@RequestMapping ("/list") //@GetMapping("/get/{id}")
	public String listCourses(Model model) {
		
		// get customers from the service
		List<Course> courseList = courseService.getCourses();
							
		// add the customers to the model
		model.addAttribute("courses", courseList);
		return "report-form";
		
	}
	
	
	@RequestMapping (value = "/report_no_pay", method = RequestMethod.GET) //@GetMapping("/get/{id}")
	public void getReportNoPaystudents(HttpServletResponse response) throws Exception {
		
		InputStream inputStream = this.getClass().getResourceAsStream("/main/webapp/resources/reports/student_not_pay_report.jrxml");

		
		// get customers from the service
		List<Student> studentList = studentService.getStudentsNoPay();//TODO getStudentsNoPay();
					
		
		response.setContentType("text/html");
		
		//if no one has paid yet
		if(studentList.isEmpty()) {
			response.getWriter().print("Nadie ha pagado aun");
			response.getWriter().flush();
			response.getWriter().close();
			return;
		}
		
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(studentList);
		//InputStream inputStream = this.getClass().getResourceAsStream("/main/webapp/resources/reports/student_not_pay_report.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
		HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
		exporter.exportReport();
		
	}
	
	

	

		
		
	
}
