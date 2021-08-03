/**
 * 
 */
package jama.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@NamedQueries({ @NamedQuery(name = "student.getStudents", query = "from Student order by firstName"),
		@NamedQuery(name = "student.getStudentsByName", query = "from Student where lastName = ? order by firstName"),
		@NamedQuery(name = "student.getStudentByStatus", query = "from Student where status =? order by firstName")

})

/**
 * @author ajara
 *
 */
@Data
@Entity
@Table(name = "student")
@EqualsAndHashCode(exclude = "courses") // due to lombock conflict with hibernate´s manytomany stackoverflowerror
@ToString(exclude = "courses")
public class Student {

	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private int id;

	@Column(name = "first_name")
	@NotEmpty(message = "*Dato Requerido")
	@Pattern(regexp = "[ÁÉÍÓÚáéíóúÑñÜüa-zA-Z\\s]*", message = "*Solo letras")
	private String firstName;

	@Column(name = "last_name")
	@NotEmpty(message = "*Dato Requerido")
	@Pattern(regexp = "[ÁÉÍÓÚáéíóúÑñÜüa-zA-Z\\s]*", message = "*Solo letras")
	private String lastName;

	@Column(name = "email")
	@NotEmpty(message = "*Dato Requerido")
	@Email(message = "*formato invalido")
	private String email;

	@Column(name = "status")
	private String status;
	/*
	 * los status posibles son:
	 * A = Activo sin pagar
	 * P = Activo ya pago
	 */

	@Column(name = "tutor_id")
	private int tutorId;

	@Column(name = "tuition")
	@NotNull(message = "*Dato Requerido")
	@Range(min = 0, max = 100000, message = "*fuera del rango requerido")
	// @Pattern(regexp="\\d+(.\\d{1,2})?", message="*cantidad invalida")
	private double tuition;

	@Column(name = "phone")
	@NotEmpty(message = "*Dato Requerido")
	@Size(min = 10, max = 10, message = "*Deben ser 10 digitos")
	@Pattern(regexp = "[0-9]*", message = "*Solo numeros")
	private String phone;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	@JoinTable(name = "course_student", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	private Set<Course> courses;

	@Transient
	String course;
	
	//TODO temporal for payment details, this should not be here but in the future payment model class
	@Transient
	String detail;
}
