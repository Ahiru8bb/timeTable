package ahiru.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ahiru.model.StudentGroup;
import ahiru.repository.GroupRepository;

@Service
public class GroupService {

	@Autowired
	private GroupRepository groupRepository;

	public StudentGroup save(StudentGroup studentGroup) {
		return groupRepository.save(studentGroup);
	}

	public Optional<StudentGroup> findById(Integer id) {
		return groupRepository.findById(id);
	}

	public Iterable<StudentGroup> findAll() {
		return groupRepository.findAll();
	}

	public StudentGroup deleteById(Integer id) {
		StudentGroup studentGroup = groupRepository.findById(id).get();
		groupRepository.findById(id);
		return studentGroup;
	}
}
