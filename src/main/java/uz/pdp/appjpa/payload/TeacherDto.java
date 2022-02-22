package uz.pdp.appjpa.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeacherDto {
    private Integer subjectId;
    private String name;
    private String region;
    private String district;
    private String street;
    private String home;
}
