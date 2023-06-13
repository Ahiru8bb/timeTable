package ahiru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ahiru.dao.GroupRepository;
import ahiru.model.StudentGroup;

@Controller
@RequestMapping("/groupList")
public class GroupListController {

	@Autowired
	private GroupRepository groupRepository;

	@ModelAttribute(name = "groupList")
	public Iterable<StudentGroup> prepareData() {
		Iterable<StudentGroup> groups = groupRepository.findAll();
		return groups;
	}

	@GetMapping
	public String groupListPage() {
		return "groupList";
	}
}
