package ahiru.controller;

import java.time.DayOfWeek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ahiru.model.Lesson;
import ahiru.model.RegistrationForm;
import ahiru.model.StudentGroup;
import ahiru.model.Teacher;
import ahiru.repository.UserRepository;
import ahiru.service.GroupService;
import ahiru.service.LessonService;
import ahiru.service.TeacherService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/register")
@Slf4j
public class Registration {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private GroupService groupService;

	@Autowired
	private LessonService lessonService;

	@Autowired
	private TeacherService teacher;

	@GetMapping
	public String registerForm() {
		addInfo();
		return "register";
	}

	@PostMapping
	public String processTrgistration(RegistrationForm registrationForm) {
		log.info("register form = {}", registrationForm);
		userRepository.save(
				registrationForm.toUser(passwordEncoder, groupService));
		return "redirect:/login";
	}

	private void addInfo() {
		Teacher teacher = new Teacher();
		teacher.setName("Ivan");
		teacher.setPatronymic("Ivanovich");
		teacher.setPhone("Nokia");
		teacher.setSurname("Ivanovich");

		this.teacher.save(teacher);

		StudentGroup studentGroup = new StudentGroup();
		studentGroup.setName("111");

		this.groupService.save(studentGroup);

		Lesson lesson = new Lesson();
		lesson.setDayOfWeek(DayOfWeek.MONDAY);
		lesson.setName("Пн");
		lesson.setNumberOfLesson(1);
		lesson.setGroup(studentGroup);
		lesson.setTeacher(teacher);

		this.lessonService.save(lesson);

		studentGroup = new StudentGroup();
		studentGroup.setName("211");
		this.groupService.save(studentGroup);
		lesson = new Lesson();
		lesson.setDayOfWeek(DayOfWeek.TUESDAY);
		lesson.setName("Вт");
		lesson.setNumberOfLesson(2);
		lesson.setGroup(studentGroup);
		lesson.setTeacher(teacher);
		this.lessonService.save(lesson);

		lesson = new Lesson();
		lesson.setDayOfWeek(DayOfWeek.WEDNESDAY);
		lesson.setName("Ср");
		lesson.setNumberOfLesson(2);
		lesson.setGroup(studentGroup);
		lesson.setTeacher(teacher);
		this.lessonService.save(lesson);

		lesson = new Lesson();
		lesson.setDayOfWeek(DayOfWeek.THURSDAY);
		lesson.setName("Чт");
		lesson.setNumberOfLesson(2);
		lesson.setGroup(studentGroup);
		lesson.setTeacher(teacher);
		this.lessonService.save(lesson);

		lesson = new Lesson();
		lesson.setDayOfWeek(DayOfWeek.FRIDAY);
		lesson.setName("Пт");
		lesson.setNumberOfLesson(2);
		lesson.setGroup(studentGroup);
		lesson.setTeacher(teacher);
		this.lessonService.save(lesson);

		lesson = new Lesson();
		lesson.setDayOfWeek(DayOfWeek.SATURDAY);
		lesson.setName("Сб");
		lesson.setNumberOfLesson(2);
		lesson.setGroup(studentGroup);
		lesson.setTeacher(teacher);
		this.lessonService.save(lesson);
	}
}
