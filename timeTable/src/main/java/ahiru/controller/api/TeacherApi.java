package ahiru.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ahiru.model.Teacher;
import ahiru.service.TeacherService;

@RestController
@RequestMapping("/api/teachers")
public class TeacherApi {

	@Autowired
	private TeacherService teacherService;

	@GetMapping
	public Iterable<Teacher> getTeachers() {
		return teacherService.findAll();
	}

	@GetMapping("{id}")
	public Teacher getTeacherById(@PathVariable Integer id) {
		return teacherService.findById(id).get();
	}

	@PostMapping
	public Teacher postTeacher(@RequestBody Teacher teacher) {
		return teacherService.save(teacher);
	}

	@DeleteMapping("{id}")
	public Teacher deleteTeacher(@PathVariable Integer id) {
		Teacher teacher = teacherService.findById(id).get();
		teacherService.deleteById(id);
		return teacher;
	}
}
