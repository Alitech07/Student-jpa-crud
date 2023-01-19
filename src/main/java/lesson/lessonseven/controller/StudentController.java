package lesson.lessonseven.controller;

import lesson.lessonseven.model.Address;
import lesson.lessonseven.model.Guruh;
import lesson.lessonseven.model.Student;
import lesson.lessonseven.model.Subject;
import lesson.lessonseven.payload.StudentDto;
import lesson.lessonseven.repository.AddressRepository;
import lesson.lessonseven.repository.GuruhRepository;
import lesson.lessonseven.repository.StudentRepository;
import lesson.lessonseven.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    GuruhRepository guruhRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    SubjectRepository subjectRepository;

    //VAZIRLIK UCHUN
    @GetMapping("/info")
    public Page<Student> getStudentListForMinistry(@RequestParam int page){
        Pageable pageable = PageRequest.of(page,10);
        Page<Student> studentPage = studentRepository.findAll(pageable);
        return studentPage;
    }

    //UNIVERSITET UCHUN
    @GetMapping("/university/{id}")
    public Page<Student> getStudentListFoUniversity(@RequestParam int page, @PathVariable Integer id){
        Pageable pageable = PageRequest.of(page,10);
        Page<Student> studentPage = studentRepository.findAllByGuruh_Fuclty_UniversityId(id,pageable);
        return studentPage;
    }
    //FACULTY UCHUN
    @GetMapping("/fuclty/{id}")
    public Page<Student> getStudentListForFaculty(@RequestParam int page, @PathVariable Integer id){
        Pageable pageable = PageRequest.of(page,10);
        Page<Student> studentPage = studentRepository.findAllByGuruh_FucltyId(id,pageable);
        return studentPage;
    }

    //GROUP UCHUN
    @GetMapping("/group/{id}")
    public Page<Student> getStudentListForGroup(@RequestParam int page, @PathVariable Integer id){
        Pageable pageable = PageRequest.of(page,10);
        Page<Student> studentPage = studentRepository.findAllByGuruhId(id,pageable);
        return studentPage;
    }

    @PostMapping("/add")
    public String addStudent(@RequestBody StudentDto studentDto){
        Optional<Address> optionalAddress = addressRepository.findById(studentDto.getAddressId());
        if (!optionalAddress.isPresent())
            return "Address not found";
        Set<Subject> subjectSet = new HashSet<>();
        for (Integer i:studentDto.getSubjectIds()){
            Optional<Subject> optionalSubject = subjectRepository.findById(i);
            if (!optionalSubject.isPresent())
                return "Subject not found";
            subjectSet.add(optionalSubject.get());
        }

        Optional<Guruh> optionalGuruh = guruhRepository.findById(studentDto.getGuruhId());
        if (!optionalGuruh.isPresent())
            return "Gruop not found";
        Student student = new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setGuruh(optionalGuruh.get());
        student.setAddress(optionalAddress.get());
        student.setSubjects(subjectSet);
        studentRepository.save(student);
        return "Added Student";
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Integer id){
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (!optionalStudent.isPresent())
            return "Student not found";
        studentRepository.deleteById(id);
        return "Student deleted";
    }

    @PutMapping("/{id}")
    public String editStudent(@PathVariable Integer id, @RequestBody StudentDto studentDto){
        Optional<Address> optionalAddress = addressRepository.findById(studentDto.getAddressId());
        if (!optionalAddress.isPresent())
            return "Address not found";
        Set<Subject> subjectSet = new HashSet<>();
        for (Integer i:studentDto.getSubjectIds()){
            Optional<Subject> optionalSubject = subjectRepository.findById(i);
            if (!optionalSubject.isPresent())
                return "Subject not found";
            subjectSet.add(optionalSubject.get());
        }

        Optional<Guruh> optionalGuruh = guruhRepository.findById(studentDto.getGuruhId());
        if (!optionalGuruh.isPresent())
            return "Gruop not found";
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (!optionalStudent.isPresent())
            return "Student not found";
        Student student = optionalStudent.get();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setGuruh(optionalGuruh.get());
        student.setAddress(optionalAddress.get());
        student.setSubjects(subjectSet);
        studentRepository.save(student);
        return "Edit Student";
    }
}
