package ahiru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ahiru.model.Teacher;
import ahiru.service.TeacherService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/teacherList")
@Slf4j
public class TeacherListController {

	@Autowired
	private TeacherService teacherService;

	@ModelAttribute(name = "teacherList")
	public Iterable<Teacher> prepareData() {
		Iterable<Teacher> teachers = teacherService.findAll();
		log.info("Teachers = {}", teachers);
		return teachers;
	}

	@GetMapping
	public String teacherListPage() {
		return "teacherList";
	}
}
