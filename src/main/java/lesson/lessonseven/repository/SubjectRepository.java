package lesson.lessonseven.repository;

import lesson.lessonseven.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject,Integer> {
}
