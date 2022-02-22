package uz.pdp.appjpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appjpa.entity.Faculty;
import uz.pdp.appjpa.entity.Group;
import uz.pdp.appjpa.payload.GroupDto;
import uz.pdp.appjpa.repository.FacultyRepository;
import uz.pdp.appjpa.repository.GroupRepository;
import uz.pdp.appjpa.response.ApiResponse;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    FacultyRepository facultyRepository;
    public ApiResponse getAll(){
        List<Group> all = groupRepository.findAll();
        return new ApiResponse("List",true,all);
    }
    public ApiResponse addGroup(GroupDto dto){
        if (facultyRepository.existsById(dto.getFacultyId())) {
            Optional<Faculty> byId = facultyRepository.findById(dto.getFacultyId());
            Faculty faculty = byId.get();
            Group group=new Group();
            group.setName(dto.getName());
            group.setFaculty(faculty);
            Group save = groupRepository.save(group);
            return new ApiResponse("added successfully",true,save);
        }
        return new ApiResponse("Faculty not found",false);
    }
    public ApiResponse editGroup(Integer id,GroupDto dto){
        if (groupRepository.existsById(id)) {
            Optional<Faculty> byId = facultyRepository.findById(dto.getFacultyId());
            Faculty faculty = byId.get();
            Optional<Group> byId1 = groupRepository.findById(id);
            Group group = byId1.get();
            group.setFaculty(faculty);
            group.setName(dto.getName());
            Group save = groupRepository.save(group);
            return new ApiResponse("Found and updated",true,save);
        }
        return new ApiResponse("group does not exist",false);
    }
    public ApiResponse deleteGroup(Integer id){
        if (groupRepository.existsById(id)) {
            groupRepository.deleteById(id);
            return new ApiResponse("found and deleted",true);
        }
        return new ApiResponse("group does not exist",false);
    }
}
