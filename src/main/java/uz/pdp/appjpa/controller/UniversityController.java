package uz.pdp.appjpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjpa.payload.UniversityDto;
import uz.pdp.appjpa.response.ApiResponse;
import uz.pdp.appjpa.service.UniversityService;

@RequestMapping("/university")
@RestController
public class UniversityController {
    @Autowired
    UniversityService universityService;
    @GetMapping
    public ApiResponse all(){
        ApiResponse all = universityService.getAll();
        return all;
    }
    @PostMapping
    public ApiResponse add(@RequestBody UniversityDto dto){
        ApiResponse apiResponse = universityService.addUniversity(dto);
        return apiResponse;
    }
    @PutMapping("/{id}")
    public ApiResponse edit(@PathVariable Integer id,@RequestBody UniversityDto dto){
        ApiResponse apiResponse = universityService.editUniversity(id, dto);
        return apiResponse;
    }
    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable Integer id){
        ApiResponse apiResponse = universityService.deleteUniversity(id);
        return apiResponse;
    }

}
