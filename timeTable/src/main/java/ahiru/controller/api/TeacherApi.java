package ahiru.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ahiru.dao.TeacherRepository;
import ahiru.model.Teacher;

@RestController
@RequestMapping("/api/teachers")
public class TeacherApi {

	@Autowired
	private TeacherRepository teacherRepository;

	@GetMapping
	public Iterable<Teacher> getTeachers() {
		return teacherRepository.findAll();
	}

	@GetMapping("{id}")
	public Teacher getTeacherById(@PathVariable Integer id) {
		return teacherRepository.findById(id).get();
	}

	@PostMapping
	public Teacher postTeacher(@RequestBody Teacher teacher) {
		return teacherRepository.save(teacher);
	}

	@DeleteMapping("{id}")
	public Teacher deleteTeacher(@PathVariable Integer id) {
		Teacher teacher = teacherRepository.findById(id).get();
		teacherRepository.deleteById(id);
		return teacher;
	}
}
