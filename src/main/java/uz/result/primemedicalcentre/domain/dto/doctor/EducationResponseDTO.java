package uz.result.primemedicalcentre.domain.dto.doctor;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import uz.result.primemedicalcentre.domain.dto.LanguageDTO;
import uz.result.primemedicalcentre.domain.entity.doctor.Education;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EducationResponseDTO {

    Long id;

    String startYear;

    String finishYear;

    LanguageDTO institution;

    LanguageDTO qualification;

    public EducationResponseDTO(Education education) {
        this.id = education.getId();
        this.startYear=education.getStartYear();
        this.finishYear=education.getFinishYear();
        this.institution = new LanguageDTO(education.getInstitutionUz(), education.getInstitutionRu());
        this.qualification = new LanguageDTO(education.getQualificationUz(), education.getQualificationRu());
    }

}
