package ahiru.controller.api;

import java.time.DayOfWeek;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ahiru.model.Lesson;
import ahiru.model.LessonForm;
import ahiru.model.StudentGroup;
import ahiru.model.Teacher;
import ahiru.service.GroupService;
import ahiru.service.LessonService;
import ahiru.service.TeacherService;

@RestController
@RequestMapping("/api/lessons")
public class LessonApi {

	@Autowired
	private LessonService lessonService;

	@Autowired
	private GroupService groupService;

	@Autowired
	private TeacherService teacherService;

	@GetMapping("{id}")
	public Lesson getLessonById(@PathVariable Integer id) {
		return lessonService.findById(id).get();
	}

	@GetMapping("/getFreeTeacher/{day}")
	public List<Teacher> getFreeTeacher(@PathVariable String day) {
		DayOfWeek dayOfWeek = DayOfWeek.valueOf(day.split("-")[1]);
		Integer numberOfLesson = Integer.decode(day.split("-")[0]);
		List<Teacher> teachers = (List<Teacher>) teacherService.findAll();
		for (int i = 0; i < teachers.size(); i++) {
			List<Lesson> lessons = lessonService
					.findAllLessonByTeacherId(teachers.get(i).getId());
			for (Lesson lesson : lessons) {
				if (lesson.getDayOfWeek() == dayOfWeek
						&& lesson.getNumberOfLesson() == numberOfLesson) {
					teachers.remove(i);
					i--;
					break;
				}
			}
		}
		return teachers;
	}

	@GetMapping("/getFreeGroup/{day}")
	public List<StudentGroup> getFreeGroup(@PathVariable String day) {
		DayOfWeek dayOfWeek = DayOfWeek.valueOf(day.split("-")[1]);
		Integer numberOfLesson = Integer.decode(day.split("-")[0]);
		List<StudentGroup> studentsGroup = (List<StudentGroup>) groupService
				.findAll();
		for (int i = 0; i < studentsGroup.size(); i++) {
			List<Lesson> lessons = lessonService.findAllLessonByTeacherId(
					studentsGroup.get(i).getId());
			for (Lesson lesson : lessons) {
				if (lesson.getDayOfWeek() == dayOfWeek
						&& lesson.getNumberOfLesson() == numberOfLesson) {
					studentsGroup.remove(i);
					i--;
					break;
				}
			}
		}
		return studentsGroup;
	}

	@PostMapping
	public Lesson postLesson(@RequestBody LessonForm lessonForm) {
		return lessonService
				.save(lessonForm.toLesson(groupService, teacherService));
	}

	@DeleteMapping("{id}")
	public Lesson deleteLessonById(@PathVariable Integer id) {
		Lesson lesson = lessonService.findById(id).get();
		lessonService.deleteById(id);
		return lesson;
	}
}
