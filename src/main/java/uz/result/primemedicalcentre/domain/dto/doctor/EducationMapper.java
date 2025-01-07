package uz.result.primemedicalcentre.domain.dto.doctor;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import uz.result.primemedicalcentre.domain.dto.LanguageDTO;
import uz.result.primemedicalcentre.domain.entity.Service;
import uz.result.primemedicalcentre.domain.entity.doctor.Education;
import uz.result.primemedicalcentre.exception.LanguageNotSupportException;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EducationMapper {

    Long id;

    String startYear;

    String finishYear;

    String institution;

    String qualification;

    public EducationMapper(Education education, String lang) {
        this.id = education.getId();
        this.startYear = education.getStartYear();
        this.finishYear = education.getFinishYear();
        switch (lang.toLowerCase()) {

            case "uz": {
                this.institution = education.getInstitutionUz();
                this.qualification = education.getQualificationUz();
                break;
            }

            case "ru": {
                this.institution = education.getInstitutionRu();
                this.qualification = education.getQualificationRu();
                break;
            }

            default:
                throw new LanguageNotSupportException("Language is not supported: " + lang);
        }
    }

    public static Education updateDtoToEntity(EducationResponseDTO updateDTO) {
        if (updateDTO == null) {
            return null;
        }
        Education education = new Education();
        education.setId(updateDTO.getId());
        education.setStartYear(updateDTO.getStartYear());
        education.setFinishYear(updateDTO.getFinishYear());
        if (updateDTO.getInstitution() != null) {
            education.setInstitutionUz(updateDTO.getInstitution().getUz());
            education.setInstitutionRu(updateDTO.getInstitution().getRu());
        }
        if (updateDTO.getQualification() != null) {
            education.setQualificationUz(updateDTO.getQualification().getUz());
            education.setQualificationRu(updateDTO.getQualification().getRu());
        }
        return education;
    }

}
