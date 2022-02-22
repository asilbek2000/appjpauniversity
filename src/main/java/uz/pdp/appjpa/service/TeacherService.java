package uz.pdp.appjpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appjpa.entity.Address;
import uz.pdp.appjpa.entity.Subject;
import uz.pdp.appjpa.entity.Teacher;
import uz.pdp.appjpa.payload.TeacherDto;
import uz.pdp.appjpa.repository.AddressRepository;
import uz.pdp.appjpa.repository.SubjectRepository;
import uz.pdp.appjpa.repository.TeacherRepository;
import uz.pdp.appjpa.response.ApiResponse;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    SubjectRepository subjectRepository;
    public ApiResponse getAll(){
        List<Teacher> all = teacherRepository.findAll();
        return new ApiResponse("List",true,all);
    }
    public ApiResponse addTeacher(TeacherDto dto){
        Address address=new Address();
        address.setStreet(dto.getStreet());
        address.setDistrict(dto.getDistrict());
        address.setRegion(dto.getRegion());
        address.setHome(dto.getHome());
        Address myaddress = addressRepository.save(address);
        Optional<Subject> byId = subjectRepository.findById(dto.getSubjectId());
        Subject subject = byId.get();
        Teacher teacher=new Teacher();
        teacher.setName(dto.getName());
        teacher.setAddress(myaddress);
        teacher.setSubject(subject);
        Teacher save1 = teacherRepository.save(teacher);
        return new ApiResponse("added successfully",true,save1);
    }
    public ApiResponse editTeacher(Integer id,TeacherDto dto){
        if (teacherRepository.existsById(id)) {
            Address address=new Address();
            address.setHome(dto.getHome());
            address.setDistrict(dto.getDistrict());
            address.setRegion(dto.getRegion());
            address.setStreet(dto.getStreet());
            Address savedAddress = addressRepository.save(address);
            Optional<Subject> byId = subjectRepository.findById(dto.getSubjectId());
            Subject subject = byId.get();
            Optional<Teacher> byId1 = teacherRepository.findById(id);
            Teacher teacher = byId1.get();
            teacher.setSubject(subject);
            teacher.setName(dto.getName());
            teacher.setAddress(savedAddress);
            Teacher save = teacherRepository.save(teacher);
            return new ApiResponse("Found and updated",true,save);
        }
        return new ApiResponse("Teacher does not exist",false);
    }
    public ApiResponse deleteTeacher(Integer id){
        if (teacherRepository.existsById(id)) {
            teacherRepository.deleteById(id);
            return new ApiResponse("Found and deleted",true);
        }
        return new ApiResponse("Tacher does not exist",false);
    }

}
