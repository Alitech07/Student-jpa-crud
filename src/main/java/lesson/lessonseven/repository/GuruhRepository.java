package lesson.lessonseven.repository;

import lesson.lessonseven.model.Guruh;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuruhRepository extends JpaRepository<Guruh,Integer> {
}
