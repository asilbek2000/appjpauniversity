package uz.pdp.appjpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjpa.payload.FacultyDto;
import uz.pdp.appjpa.payload.GroupDto;
import uz.pdp.appjpa.response.ApiResponse;
import uz.pdp.appjpa.service.GroupService;

@RequestMapping("/group")
@RestController
public class GroupController {
    @Autowired
    GroupService groupService;
    @GetMapping
    public ApiResponse all(){
        ApiResponse all = groupService.getAll();
        return all;

    }
    @PostMapping
    public ApiResponse add(@RequestBody GroupDto dto){
        ApiResponse apiResponse = groupService.addGroup(dto);
        return apiResponse;
    }
    @PutMapping("{id}")
    public ApiResponse edit(@PathVariable Integer id,@RequestBody GroupDto dto){
        ApiResponse apiResponse = groupService.editGroup(id, dto);
        return apiResponse;
    }
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        ApiResponse apiResponse = groupService.deleteGroup(id);
        return apiResponse;
    }
}
