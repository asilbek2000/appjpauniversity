package uz.pdp.appjpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjpa.payload.SubjectDto;
import uz.pdp.appjpa.response.ApiResponse;
import uz.pdp.appjpa.service.SubjectService;

@RequestMapping("/subject")
@RestController
public class SubjectController {
    @Autowired
    SubjectService subjectService;
    @GetMapping
    public ApiResponse all(){
        ApiResponse all = subjectService.getAll();
        return all;
    }
    @PostMapping
    public ApiResponse add(@RequestBody SubjectDto dto){
        ApiResponse apiResponse = subjectService.addSubject(dto);
        return apiResponse;
    }
    @PutMapping("/{id}")
    public ApiResponse edit(@PathVariable Integer id,@RequestBody SubjectDto dto){
        ApiResponse apiResponse = subjectService.editSubject(id, dto);
        return apiResponse;
    }
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        ApiResponse apiResponse = subjectService.deleteSubject(id);
        return apiResponse;
    }
}
