package uz.pdp.appjpa.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FacultyDto {
private String name;
private Integer universityId;
}
