package uz.pdp.appjpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appjpa.entity.Address;
import uz.pdp.appjpa.entity.University;
import uz.pdp.appjpa.payload.UniversityDto;
import uz.pdp.appjpa.repository.AddressRepository;
import uz.pdp.appjpa.repository.UniversityRepository;
import uz.pdp.appjpa.response.ApiResponse;

import java.util.List;
import java.util.Optional;

@Service
public class UniversityService {
    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    AddressRepository addressRepository;
    public ApiResponse getAll(){
        List<University> all = universityRepository.findAll();
        return new ApiResponse("List",true,all);
    }
    public ApiResponse addUniversity(UniversityDto dto){
        Address address=new Address();
        address.setRegion(dto.getRegion());
        address.setStreet(dto.getStreet());
        address.setDistrict(dto.getDistrict());
        address.setHome(dto.getHome());
        Address save = addressRepository.save(address);
        University university=new University();
        university.setAddress(save);
        university.setName(dto.getName());
        University save1 = universityRepository.save(university);
        return new ApiResponse("added successfully",true,save1);
    }
    public ApiResponse editUniversity(Integer id,UniversityDto dto){
        if (universityRepository.existsById(id)) {
            Address address=new Address();
            address.setHome(dto.getHome());
            address.setRegion(dto.getRegion());
            address.setDistrict(dto.getDistrict());
            address.setStreet(dto.getStreet());
            Address save = addressRepository.save(address);
            Optional<University> byId = universityRepository.findById(id);
            University university = byId.get();
            university.setAddress(save);
            university.setName(dto.getName());
            University save1 = universityRepository.save(university);
            return new ApiResponse("found and updated",true,save1);

        }
        return new ApiResponse("University not found",false);
    }
    public ApiResponse deleteUniversity(Integer id){
        if (universityRepository.existsById(id)) {
            universityRepository.deleteById(id);
            return new ApiResponse("found and deleted",true);

        }
        return new ApiResponse("university does not exist",false);
    }
}
