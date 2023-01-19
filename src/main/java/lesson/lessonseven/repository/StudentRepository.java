package lesson.lessonseven.repository;

import lesson.lessonseven.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Page<Student> findAllByGuruh_Fuclty_UniversityId(Integer guruh_fuclty_university_id, Pageable pageable);
    Page<Student> findAllByGuruh_FucltyId(Integer guruh_fuclty_id, Pageable pageable);
    Page<Student> findAllByGuruhId(Integer guruh_id, Pageable pageable);
}
