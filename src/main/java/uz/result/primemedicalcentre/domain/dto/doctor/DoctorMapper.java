package uz.result.primemedicalcentre.domain.dto.doctor;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import uz.result.primemedicalcentre.domain.dto.newness.NewOptionMapper;
import uz.result.primemedicalcentre.domain.dto.serviceOfDoctor.ServiceOfDoctorMapper;
import uz.result.primemedicalcentre.domain.entity.Photo;
import uz.result.primemedicalcentre.domain.entity.doctor.Doctor;
import uz.result.primemedicalcentre.exception.LanguageNotSupportException;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DoctorMapper {

    Long id;

    String slug;

    String fullName;

    String description;

    String experience;

    String receptionTime;

    boolean active;

    Photo photo;

    List<SpecializationMapper> specializationList;

    List<EducationMapper> educationList;

    List<ServiceOfDoctorMapper> serviceOfDoctorList;

    public DoctorMapper(Doctor doctor, String lang) {
        this.id = doctor.getId();
        this.slug = doctor.getSlug();
        this.active = doctor.isActive();
        this.photo = doctor.getPhoto();
        switch (lang.toLowerCase()) {

            case "uz": {
                this.fullName = doctor.getFullNameUz();
                this.description = doctor.getDescriptionUz();
                this.experience = doctor.getExperienceUz();
                this.receptionTime = doctor.getReceptionTimeUz();
                break;
            }

            case "ru": {
                this.fullName = doctor.getFullNameRu();
                this.description = doctor.getDescriptionRu();
                this.experience = doctor.getExperienceRu();
                this.receptionTime = doctor.getReceptionTimeRu();
                break;
            }

            default:
                throw new LanguageNotSupportException("Language is not supported: " + lang);
        }
        this.specializationList = doctor.getSpecializationList().stream().map(
                specialization -> new SpecializationMapper(specialization, lang)
        ).collect(Collectors.toList());
        this.educationList = doctor.getEducationList().stream().map(
                education -> new EducationMapper(education, lang)
        ).collect(Collectors.toList());
        this.serviceOfDoctorList = doctor.getService().stream().map(
                serviceOfDoctor -> new ServiceOfDoctorMapper(serviceOfDoctor, lang)
        ).toList();
    }

    public static Doctor updateDtoToEntity(DoctorUpdateDTO updateDTO) {
        if (updateDTO == null) {
            return null;
        }
        Doctor doctor = new Doctor();
        doctor.setId(updateDTO.getId());
        doctor.setActive(updateDTO.isActive());
        if (updateDTO.getFullName() != null) {
            doctor.setFullNameUz(updateDTO.getFullName().getUz());
            doctor.setFullNameRu(updateDTO.getFullName().getRu());
        }
        if (updateDTO.getDescription() != null) {
            doctor.setDescriptionUz(updateDTO.getDescription().getUz());
            doctor.setDescriptionRu(updateDTO.getDescription().getRu());
        }
        if (updateDTO.getExperience() != null) {
            doctor.setExperienceUz(updateDTO.getExperience().getUz());
            doctor.setExperienceRu(updateDTO.getExperience().getRu());
        }
        if (updateDTO.getReceptionTime() != null) {
            doctor.setReceptionTimeUz(updateDTO.getReceptionTime().getUz());
            doctor.setReceptionTimeRu(updateDTO.getReceptionTime().getRu());
        }
        if (updateDTO.getSpecializationList() != null && !updateDTO.getSpecializationList().isEmpty()) {
            doctor.setSpecializationList(
                    updateDTO.getSpecializationList().stream().map(
                            SpecializationMapper::updateDtoToEntity
                    ).collect(Collectors.toList())
            );
        }
        return doctor;
    }

}
