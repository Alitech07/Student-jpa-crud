package lesson.lessonseven.controller;

import lesson.lessonseven.model.Address;
import lesson.lessonseven.model.University;
import lesson.lessonseven.payload.UniversityDto;
import lesson.lessonseven.repository.AddressRepository;
import lesson.lessonseven.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UniversityController {

    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    AddressRepository addressRepository;

    //BARCHA UNIVERSITYLARNI KO'RISH
    @RequestMapping(value = "/university", method = RequestMethod.GET)
    public List<University> getUniversities() {
        return universityRepository.findAll();
    }

    //UNIVERSITYLARNI ID SI BOYICHA
    @RequestMapping(value = "/university/{id}", method = RequestMethod.GET)
    public University getUniversity(@PathVariable Integer id) {
        Optional<University> optionalUniversity = universityRepository.findById(id);
        University university = optionalUniversity.get();

        return university;
    }

    //YANGI UNIVERSITY QO'SHISH
    @RequestMapping(value = "/university", method = RequestMethod.POST)
    public String addUniversity(@RequestBody UniversityDto universityDto) {
        Address address = new Address();
        address.setCity(universityDto.getCity());
        address.setDistrict(universityDto.getDistrict());
        address.setStreet(universityDto.getStreet());
        Address saveAddress = addressRepository.save(address);

        University university = new University();
        university.setName(universityDto.getName());
        university.setAddress(saveAddress);

        universityRepository.save(university);
        return "Added University";
    }

    //UNIVERSITY LARNI O'CHIRISH
    @RequestMapping(value = "/university/{id}", method = RequestMethod.DELETE)
    public String deleteUniversity(@PathVariable Integer id) {
        universityRepository.deleteById(id);
        return "deleted university";
    }

    //UNIVERSITETLARNI YANGILASH

    public String editUniversity(@PathVariable Integer id, @RequestBody UniversityDto universityDto) {
        Optional<University> optionalUniversity = universityRepository.findById(id);

        if (optionalUniversity.isPresent()) {
            University university = optionalUniversity.get();

            Address address = new Address();
            address.setCity(universityDto.getCity());
            address.setDistrict(universityDto.getDistrict());
            address.setStreet(universityDto.getStreet());
            addressRepository.save(address);

            university.setAddress(address);
            university.setName(universityDto.getName());
            return "University edited";
        } else {
            return "University not found";
        }
    }

}
