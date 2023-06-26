package ahiru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ahiru.model.StudentGroup;
import ahiru.service.GroupService;

@Controller
@RequestMapping("/groupList")
public class GroupListController {

	@Autowired
	private GroupService groupService;

	@ModelAttribute(name = "groupList")
	public Iterable<StudentGroup> prepareData() {
		Iterable<StudentGroup> groups = groupService.findAll();
		return groups;
	}

	@GetMapping
	public String groupListPage() {
		return "groupList";
	}
}
