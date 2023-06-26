package ahiru.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ahiru.model.Teacher;
import ahiru.repository.TeacherRepository;

@Service
public class TeacherService {

	@Autowired
	private TeacherRepository teacherRepository;

	public Teacher save(Teacher teacher) {
		return teacherRepository.save(teacher);
	}

	public Optional<Teacher> findById(Integer id) {
		return teacherRepository.findById(id);
	}

	public Iterable<Teacher> findAll() {
		return teacherRepository.findAll();
	}

	public Teacher deleteById(Integer id) {
		Teacher teacher = teacherRepository.findById(id).get();
		teacherRepository.deleteById(id);
		return teacher;
	}
}
