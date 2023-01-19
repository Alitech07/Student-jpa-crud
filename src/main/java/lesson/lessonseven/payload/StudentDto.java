package lesson.lessonseven.payload;

import lesson.lessonseven.model.Subject;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class StudentDto {
    private String firstName;
    private String lastName;
    private Integer addressId;
    private Integer guruhId;
    private Set<Integer> subjectIds;
}
