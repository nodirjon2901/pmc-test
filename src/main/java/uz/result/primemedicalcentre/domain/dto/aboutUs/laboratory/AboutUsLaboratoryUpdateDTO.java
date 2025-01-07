package uz.result.primemedicalcentre.domain.dto.aboutUs.laboratory;

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
public class AboutUsLaboratoryUpdateDTO {

    Long id;

    LanguageDTO title;

    LanguageDTO body;

}