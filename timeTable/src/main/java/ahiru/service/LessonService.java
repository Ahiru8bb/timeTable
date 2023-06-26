package ahiru.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ahiru.model.Lesson;
import ahiru.repository.LessonRepository;

@Service
public class LessonService {

	@Autowired
	private LessonRepository lessonRepository;

	public List<Lesson> findAllLessonByTeacherId(Integer id) {
		return lessonRepository.findAllLessonByTeacherId(id);
	}

	public List<Lesson> findAllLessonByGroupId(Integer id) {
		return lessonRepository.findAllLessonByGroupId(id);
	}

	public Lesson save(Lesson lesson) {
		return lessonRepository.save(lesson);
	}

	public Optional<Lesson> findById(Integer id) {
		return lessonRepository.findById(id);
	}

	public Lesson deleteById(Integer id) {
		Lesson lesson = lessonRepository.findById(id).get();
		lessonRepository.deleteById(id);
		return lesson;
	}
}
