package ahiru.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ahiru.dao.GroupRepository;
import ahiru.model.StudentGroup;

@RestController
@RequestMapping("/api/groups")
public class GroupApi {

	@Autowired
	private GroupRepository groupRepository;

	@GetMapping
	public Iterable<StudentGroup> getStudentGroup() {
		return groupRepository.findAll();
	}

	@GetMapping("{id}")
	public StudentGroup getStudentGroupById(@PathVariable Integer id) {
		return groupRepository.findById(id).get();
	}

	@PostMapping
	public StudentGroup postStudentGroup(
			@RequestBody StudentGroup studentGroup) {
		return groupRepository.save(studentGroup);
	}

	@DeleteMapping("{id}")
	public StudentGroup deleteStudentGroup(@PathVariable Integer id) {
		StudentGroup studentGroup = groupRepository.findById(id).get();
		groupRepository.deleteById(id);
		return studentGroup;
	}

}
