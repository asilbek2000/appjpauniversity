package uz.pdp.appjpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjpa.payload.StudentDto;
import uz.pdp.appjpa.response.ApiResponse;
import uz.pdp.appjpa.service.StudentService;

@RequestMapping("/student")
@RestController
public class StudentController {
    @Autowired
    StudentService studentService;
    @GetMapping
    public ApiResponse all(){
        ApiResponse all = studentService.getAll();
        return all;

    }
    @PostMapping
    public ApiResponse add(@RequestBody StudentDto dto){
        ApiResponse apiResponse = studentService.addStudent(dto);
        return apiResponse;
    }
    @PutMapping("/{id}")
    public ApiResponse edit(@PathVariable Integer id,@RequestBody StudentDto dto){
        ApiResponse apiResponse = studentService.editStudent(id, dto);
        return apiResponse;
    }
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        ApiResponse apiResponse = studentService.delete(id);
        return apiResponse;

    }
}
