package ahiru.controller;

import java.time.DayOfWeek;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ahiru.model.Lesson;
import ahiru.model.StudentGroup;
import ahiru.model.Teacher;
import ahiru.service.GroupService;
import ahiru.service.LessonService;
import ahiru.service.TeacherService;
import ahiru.service.TimeTableService;

@Controller
@RequestMapping("/timeTable")
public class LessonController {

	@Autowired
	private TimeTableService timeTableService;

	@Autowired
	private LessonService lessonService;

	@Autowired
	private TeacherService teacher;

	@Autowired
	private GroupService groupService;

	@GetMapping("/teachers/{id}")
	public String lessonsOfTeacher(@PathVariable Integer id, Model model) {

		addInfo();

		Map<Integer, Map<DayOfWeek, Lesson>> timeTable = timeTableService
				.timeTableTeacher(id);
		model.addAttribute("timeTable", timeTable);
		model.addAttribute("type", "teacher");
		model.addAttribute("title", teacher.findById(id).get().getName());

		return "lessonsList";
	}

	@GetMapping("/groups/{id}")
	public String lessonsOfGroup(@PathVariable Integer id, Model model) {

		addInfo();

		Map<Integer, Map<DayOfWeek, Lesson>> timeTable = timeTableService
				.timeTableGroup(id);
		model.addAttribute("timeTable", timeTable);
		model.addAttribute("type", "group");
		model.addAttribute("title",
				groupService.findById(id).get().getName());

		return "lessonsList";
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
