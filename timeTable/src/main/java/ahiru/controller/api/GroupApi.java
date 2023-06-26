package ahiru.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ahiru.model.StudentGroup;
import ahiru.service.GroupService;

@RestController
@RequestMapping("/api/groups")
public class GroupApi {

	@Autowired
	private GroupService groupService;

	@GetMapping
	public Iterable<StudentGroup> getStudentGroup() {
		return groupService.findAll();
	}

	@GetMapping("{id}")
	public StudentGroup getStudentGroupById(@PathVariable Integer id) {
		return groupService.findById(id).get();
	}

	@PostMapping
	public StudentGroup postStudentGroup(
			@RequestBody StudentGroup studentGroup) {
		return groupService.save(studentGroup);
	}

	@DeleteMapping("{id}")
	public StudentGroup deleteStudentGroup(@PathVariable Integer id) {
		return groupService.deleteById(id);
	}

}
