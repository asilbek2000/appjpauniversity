package uz.pdp.appjpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appjpa.entity.Subject;
import uz.pdp.appjpa.payload.SubjectDto;
import uz.pdp.appjpa.repository.SubjectRepository;
import uz.pdp.appjpa.response.ApiResponse;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    @Autowired
    SubjectRepository subjectRepository;
    public ApiResponse getAll(){
        List<Subject> all = subjectRepository.findAll();
        return new ApiResponse("List",true,all);
    }
    public ApiResponse addSubject(SubjectDto dto){
        Subject subject=new Subject();
        subject.setName(dto.getName());
        Subject save = subjectRepository.save(subject);
        return new ApiResponse("successfully added",true,save);
    }
    public ApiResponse editSubject(Integer id,SubjectDto dto){
        if (subjectRepository.existsById(id)) {
            Optional<Subject> byId = subjectRepository.findById(id);
            Subject subject = byId.get();
            subject.setName(dto.getName());
            Subject save = subjectRepository.save(subject);
            return new ApiResponse("Found and updated",true,save);
        }
        return new ApiResponse("Subject not found",false);
    }
    public ApiResponse deleteSubject(Integer id){
        if (subjectRepository.existsById(id)) {
            subjectRepository.deleteById(id);
            return new ApiResponse("found and deleted",true);
        }
        return new ApiResponse("Subject does not exist",false);
    }
}
