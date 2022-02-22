package uz.pdp.appjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appjpa.entity.Faculty;
import uz.pdp.appjpa.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
}
