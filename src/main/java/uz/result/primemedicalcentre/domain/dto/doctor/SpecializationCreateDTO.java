package uz.result.primemedicalcentre.domain.dto.doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.result.primemedicalcentre.domain.dto.LanguageDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecializationCreateDTO {

    private LanguageDTO name;

}
