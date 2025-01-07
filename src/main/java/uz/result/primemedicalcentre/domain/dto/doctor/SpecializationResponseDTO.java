package uz.result.primemedicalcentre.domain.dto.doctor;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import uz.result.primemedicalcentre.domain.dto.LanguageDTO;
import uz.result.primemedicalcentre.domain.entity.doctor.Specialization;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SpecializationResponseDTO {

    Long id;

    LanguageDTO name;

    public SpecializationResponseDTO(Specialization specialization) {
        this.id = specialization.getId();
        this.name = new LanguageDTO(specialization.getNameUz(), specialization.getNameRu());
    }

}
