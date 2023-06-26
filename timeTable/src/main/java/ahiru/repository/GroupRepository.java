package ahiru.repository;

import org.springframework.data.repository.CrudRepository;

import ahiru.model.StudentGroup;

public interface GroupRepository
		extends CrudRepository<StudentGroup, Integer> {

}
