package uz.pdp.appjpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appjpa.entity.Faculty;
import uz.pdp.appjpa.entity.University;
import uz.pdp.appjpa.payload.FacultyDto;
import uz.pdp.appjpa.payload.UniversityDto;
import uz.pdp.appjpa.repository.FacultyRepository;
import uz.pdp.appjpa.repository.UniversityRepository;
import uz.pdp.appjpa.response.ApiResponse;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyService {
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    UniversityRepository universityRepository;
    public ApiResponse getAll(){
        List<Faculty> all = facultyRepository.findAll();
        return new ApiResponse("List",true,all);
    }
    public ApiResponse addfaculty(FacultyDto dto){
        if (universityRepository.existsById(dto.getUniversityId())) {
            Optional<University> byId = universityRepository.findById(dto.getUniversityId());
            University university = byId.get();

            Faculty faculty=new Faculty();
            faculty.setName(dto.getName());
            faculty.setUniversity(university);
            Faculty save = facultyRepository.save(faculty);
            return new ApiResponse("successfully added",true,save);
        }
        return new ApiResponse("university not found",false);
    }
    public ApiResponse deleteFaculty(Integer id){
        if (facultyRepository.existsById(id)) {
            facultyRepository.deleteById(id);
            return new ApiResponse("found and deleted",true);

        }
        return new ApiResponse("faculty does not exist",false);
    }
    public ApiResponse editFaculty(Integer id, FacultyDto dto){
        if (facultyRepository.existsById(id)) {
            Optional<Faculty> byId = facultyRepository.findById(id);
            Faculty faculty = byId.get();
            Optional<University> byId1 = universityRepository.findById(dto.getUniversityId());
            University university = byId1.get();
            faculty.setUniversity(university);
            faculty.setName(dto.getName());
            Faculty save = facultyRepository.save(faculty);
            return new ApiResponse("Found and updated",true,save);
        }
        return new ApiResponse("Faculty does not exist",false);
    }
}
