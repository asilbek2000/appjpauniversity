package uz.pdp.appjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appjpa.entity.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty,Integer> {
}
