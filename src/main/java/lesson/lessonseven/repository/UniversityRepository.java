package lesson.lessonseven.repository;

import lesson.lessonseven.model.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UniversityRepository extends JpaRepository<University, Integer> {
}
