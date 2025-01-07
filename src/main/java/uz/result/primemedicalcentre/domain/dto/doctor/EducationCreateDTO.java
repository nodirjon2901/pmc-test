package uz.result.primemedicalcentre.domain.dto.doctor;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import uz.result.primemedicalcentre.domain.dto.LanguageDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EducationCreateDTO {

    String startYear;

    String finishYear;

    LanguageDTO institution;

    LanguageDTO qualification;

}
