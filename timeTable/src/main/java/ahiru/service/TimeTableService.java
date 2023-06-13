package ahiru.service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ahiru.dao.LessonRepository;
import ahiru.model.Lesson;

@Service
public class TimeTableService {
	@Autowired
	private LessonRepository lessonRepository;

	public Map<Integer, Map<DayOfWeek, Lesson>> timeTableTeacher(
			Integer id) {
		List<Lesson> lessons = lessonRepository
				.findAllLessonByTeacherId(id);

		Map<Integer, Map<DayOfWeek, Lesson>> timeTable = new TreeMap<>();
		int numberOfMaxLesson = 6;

		for (Lesson lesson : lessons) {
			if (!timeTable.containsKey(lesson.getNumberOfLesson())) {
				timeTable.put(lesson.getNumberOfLesson(), new TreeMap<>());
			}
			timeTable.get(lesson.getNumberOfLesson())
					.put(lesson.getDayOfWeek(), lesson);
			numberOfMaxLesson = Math.max(numberOfMaxLesson,
					lesson.getNumberOfLesson());
		}

		for (int i = 1; i <= numberOfMaxLesson; i++) {
			if (!timeTable.containsKey(i)) {
				timeTable.put(i, new TreeMap<>());
			}

			for (DayOfWeek dayOfWeek : DayOfWeek.values()) {

				if (dayOfWeek.equals(DayOfWeek.SUNDAY)) {
					continue;
				}

				if (!timeTable.get(i).containsKey(dayOfWeek)) {
					timeTable.get(i).put(dayOfWeek, null);
				}
			}
		}
		return timeTable;
	}

	public Map<Integer, Map<DayOfWeek, Lesson>> timeTableGroup(
			Integer id) {
		List<Lesson> lessons = lessonRepository.findAllLessonByGroupId(id);

		Map<Integer, Map<DayOfWeek, Lesson>> timeTable = new TreeMap<>();
		int numberOfMaxLesson = 6;

		for (Lesson lesson : lessons) {
			if (!timeTable.containsKey(lesson.getNumberOfLesson())) {
				timeTable.put(lesson.getNumberOfLesson(), new TreeMap<>());
			}
			timeTable.get(lesson.getNumberOfLesson())
					.put(lesson.getDayOfWeek(), lesson);
			numberOfMaxLesson = Math.max(numberOfMaxLesson,
					lesson.getNumberOfLesson());
		}

		for (int i = 1; i <= numberOfMaxLesson; i++) {
			if (!timeTable.containsKey(i)) {
				timeTable.put(i, new TreeMap<>());
			}

			for (DayOfWeek dayOfWeek : DayOfWeek.values()) {

				if (dayOfWeek.equals(DayOfWeek.SUNDAY)) {
					continue;
				}

				if (!timeTable.get(i).containsKey(dayOfWeek)) {
					timeTable.get(i).put(dayOfWeek, null);
				}
			}
		}
		return timeTable;
	}
}
