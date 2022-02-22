package uz.pdp.appjpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjpa.payload.TeacherDto;
import uz.pdp.appjpa.response.ApiResponse;
import uz.pdp.appjpa.service.TeacherService;

@RequestMapping("/teacher")
@RestController
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    @GetMapping
    public ApiResponse all(){
        ApiResponse all = teacherService.getAll();
        return all;
    }
    @PostMapping
    public ApiResponse add(@RequestBody TeacherDto dto){
        ApiResponse apiResponse = teacherService.addTeacher(dto);
        return apiResponse;
    }
    @PutMapping("/{id}")
    public ApiResponse edit(@PathVariable Integer id,@RequestBody TeacherDto dto){
        ApiResponse apiResponse = teacherService.editTeacher(id, dto);
        return apiResponse;
    }
    @DeleteMapping("{id}")
    public ApiResponse delete(@PathVariable Integer id){
        ApiResponse apiResponse = teacherService.deleteTeacher(id);
        return apiResponse;
    }
}
