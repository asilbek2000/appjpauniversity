package uz.pdp.appjpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appjpa.entity.Address;
import uz.pdp.appjpa.entity.Group;
import uz.pdp.appjpa.entity.Student;
import uz.pdp.appjpa.payload.StudentDto;
import uz.pdp.appjpa.repository.AddressRepository;
import uz.pdp.appjpa.repository.GroupRepository;
import uz.pdp.appjpa.repository.StudentRepository;
import uz.pdp.appjpa.response.ApiResponse;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    GroupRepository groupRepository;
    public ApiResponse getAll(){
        List<Student> all = studentRepository.findAll();
        return new ApiResponse("List",true,all);
    }
    public ApiResponse addStudent(StudentDto dto){
        if (groupRepository.existsById(dto.getGroupId())) {
            Optional<Group> byId = groupRepository.findById(dto.getGroupId());
            Group group = byId.get();
            Address address=new Address();
            address.setStreet(dto.getStreet());
            address.setRegion(dto.getRegion());
            address.setHome(dto.getHome());
            address.setDistrict(dto.getDistrict());
            Address save = addressRepository.save(address);
            Student student=new Student();
            student.setGroup(group);
            student.setAddress(address);
            student.setName(dto.getName());
            Student save1 = studentRepository.save(student);
            return new ApiResponse("successfully added",true,save1);
        }
        return new ApiResponse("Group not found",false);
    }
    public ApiResponse editStudent(Integer id,StudentDto studentDto){
        if (studentRepository.existsById(id)) {
            Optional<Student> byId = studentRepository.findById(id);
            Student student = byId.get();
            Address address=new Address();
            address.setDistrict(studentDto.getDistrict());
            address.setRegion(studentDto.getRegion());
            address.setStreet(studentDto.getStreet());
            address.setHome(studentDto.getHome());
            Address save = addressRepository.save(address);
            Optional<Group> byId1 = groupRepository.findById(studentDto.getGroupId());
            Group group = byId1.get();
            student.setName(studentDto.getName());
            student.setAddress(save);
            student.setGroup(group);
            Student save1 = studentRepository.save(student);
            return new ApiResponse("Found and updated",true,save1);
        }
        return new ApiResponse("Student does not exist",false);

    }
    public ApiResponse delete(Integer id){
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return new ApiResponse("Found and deleted",true);
        }
        return new ApiResponse("Student does not exis",false);
    }
}
