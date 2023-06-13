package ahiru.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ahiru.model.Lesson;

public interface LessonRepository extends CrudRepository<Lesson, Integer> {

	public List<Lesson> findAllLessonByTeacherId(Integer id);

	public List<Lesson> findAllLessonByGroupId(Integer id);

}
