package lesson.lessonseven.controller;

import lesson.lessonseven.model.Address;
import lesson.lessonseven.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    AddressRepository addressRepository;

    @GetMapping
    public List<Address> getAddress(){
        List<Address> addressList = addressRepository.findAll();
        return addressList;
    }

    @PostMapping
    public String addAddress(@RequestBody Address address){
        Address saveaddress = new Address();
        saveaddress.setCity(address.getCity());
        saveaddress.setDistrict(address.getDistrict());
        saveaddress.setStreet(address.getStreet());
        addressRepository.save(saveaddress);
        return "Address added";
    }

    @DeleteMapping("/{id}")
    public String deleteAddress(@PathVariable Integer id){
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (!optionalAddress.isPresent())
            return "Address not found";
        addressRepository.deleteById(id);
        return "Deleted Address";
    }
}
