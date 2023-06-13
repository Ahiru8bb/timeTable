package ahiru.model;

import java.time.DayOfWeek;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Lesson {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Integer numberOfLesson;
	private DayOfWeek dayOfWeek;

	@ManyToOne
	@JoinColumn(name = "teacher_id", referencedColumnName = "id")
	private Teacher teacher;

	@ManyToOne
	@JoinColumn(name = "student_group", referencedColumnName = "id")
	private StudentGroup group;
}
