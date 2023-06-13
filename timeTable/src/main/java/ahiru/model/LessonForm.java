package ahiru.model;

import java.time.DayOfWeek;

import ahiru.dao.GroupRepository;
import ahiru.dao.TeacherRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class LessonForm {
	private String name;
	private Integer numberOfLesson;
	private DayOfWeek dayOfWeek;
	private Integer teacher_id;
	private Integer student_group;

	public Lesson toLesson(GroupRepository groupRepository,
			TeacherRepository teacherRepository) {
		log.info("TO LESSON!");
		Teacher teacher = teacherRepository.findById(teacher_id).get();
		StudentGroup studentGroup = groupRepository.findById(student_group)
				.get();
		log.info("Teacher = {}", teacher);
		log.info("group = {}", studentGroup);
		Lesson lesson = new Lesson();
		lesson.setName(name);
		lesson.setNumberOfLesson(numberOfLesson);
		lesson.setDayOfWeek(dayOfWeek);
		lesson.setGroup(studentGroup);
		lesson.setTeacher(teacher);
		return lesson;
	}
}
