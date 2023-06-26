package ahiru.controller;

import java.time.DayOfWeek;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ahiru.model.Lesson;
import ahiru.service.GroupService;
import ahiru.service.TeacherService;
import ahiru.service.TimeTableService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/timeTable")
@Slf4j
public class LessonController {

	@Autowired
	private TimeTableService timeTableService;

	@Autowired
	private TeacherService teacher;

	@Autowired
	private GroupService groupService;

	@GetMapping("/teachers/{id}")
	public String lessonsOfTeacher(@PathVariable Integer id, Model model) {

		Map<Integer, Map<DayOfWeek, Lesson>> timeTable = timeTableService
				.timeTableTeacher(id);
		model.addAttribute("timeTable", timeTable);
		model.addAttribute("type", "teacher");
		model.addAttribute("title", teacher.findById(id).get().getName());
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null && auth.getAuthorities().stream()
				.anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
			return "lessonsList";
		} else {

			return "lessons";
		}

	}

	@GetMapping("/groups/{id}")
	public String lessonsOfGroup(@PathVariable Integer id, Model model) {
		Map<Integer, Map<DayOfWeek, Lesson>> timeTable = timeTableService
				.timeTableGroup(id);
		model.addAttribute("timeTable", timeTable);
		model.addAttribute("type", "group");
		model.addAttribute("title",
				groupService.findById(id).get().getName());
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null && auth.getAuthorities().stream()
				.anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
			return "lessonsList";
		} else {
			return "lessons";
		}
	}

}
