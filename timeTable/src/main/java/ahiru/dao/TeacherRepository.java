package ahiru.dao;

import org.springframework.data.repository.CrudRepository;

import ahiru.model.Teacher;

public interface TeacherRepository
		extends CrudRepository<Teacher, Integer> {

}
