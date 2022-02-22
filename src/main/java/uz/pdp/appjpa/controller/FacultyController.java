package uz.pdp.appjpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjpa.payload.FacultyDto;
import uz.pdp.appjpa.response.ApiResponse;
import uz.pdp.appjpa.service.FacultyService;

@RequestMapping("/faculty")
@RestController
public class FacultyController {
    @Autowired
    FacultyService facultyService;
    @GetMapping
    public ApiResponse all(){
        ApiResponse all = facultyService.getAll();
        return all;
    }
    @PostMapping
    public ApiResponse add(@RequestBody FacultyDto dto){
        ApiResponse addfaculty = facultyService.addfaculty(dto);
        return addfaculty;
    }
    @DeleteMapping("{id}")
    public ApiResponse delete(@PathVariable Integer id){
        ApiResponse apiResponse = facultyService.deleteFaculty(id);
        return apiResponse;
    }
    @PutMapping("{id}")
    public ApiResponse edit(@PathVariable Integer id,@RequestBody FacultyDto dto){
        ApiResponse apiResponse = facultyService.editFaculty(id, dto);
        return apiResponse;
    }

}
