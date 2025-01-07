package uz.result.primemedicalcentre.domain.dto.doctor;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import uz.result.primemedicalcentre.domain.entity.doctor.Education;
import uz.result.primemedicalcentre.domain.entity.doctor.Specialization;
import uz.result.primemedicalcentre.exception.LanguageNotSupportException;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SpecializationMapper {

    Long id;

    String name;

    public SpecializationMapper(Specialization specialization, String lang) {
        this.id = specialization.getId();
        switch (lang.toLowerCase()) {

            case "uz": {
                this.name = specialization.getNameUz();
                break;
            }

            case "ru": {
                this.name = specialization.getNameRu();
                break;
            }

            default:
                throw new LanguageNotSupportException("Language is not supported: " + lang);

        }
    }

    public static Specialization updateDtoToEntity(SpecializationResponseDTO updatedDTO) {
        if (updatedDTO == null) {
            return null;
        }
        Specialization specialization = new Specialization();
        specialization.setId(updatedDTO.getId());
        if (updatedDTO.getName() != null) {
            specialization.setNameUz(updatedDTO.getName().getUz());
            specialization.setNameRu(updatedDTO.getName().getRu());
        }
        return specialization;
    }

}
