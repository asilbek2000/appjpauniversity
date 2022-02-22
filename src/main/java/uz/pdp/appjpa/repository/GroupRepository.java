package uz.pdp.appjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appjpa.entity.Faculty;
import uz.pdp.appjpa.entity.Group;

public interface GroupRepository extends JpaRepository<Group,Integer> {
}
