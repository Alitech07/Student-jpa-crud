package lesson.lessonseven.controller;

import lesson.lessonseven.model.Subject;
import lesson.lessonseven.repository.SubjectRepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    SubjectRepository subjectRepository;

    @GetMapping
    public List<Subject> getSubject(){
        List<Subject> subjectList = subjectRepository.findAll();
        return subjectList;
    }

    @PostMapping
    public String addSubject(@RequestBody Subject subject){
        Subject saveSubject = new Subject();
        saveSubject.setName(saveSubject.getName());
        return "Added Subject";
    }

    @DeleteMapping("/{id}")
    public String deleteSubject(@PathVariable Integer id){
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (!optionalSubject.isPresent())
            return "Subject not found";
        subjectRepository.deleteById(id);
        return "Subject deleted";
    }
}
