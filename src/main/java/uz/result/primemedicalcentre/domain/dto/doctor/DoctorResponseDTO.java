package uz.result.primemedicalcentre.domain.dto.doctor;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import uz.result.primemedicalcentre.domain.dto.LanguageDTO;
import uz.result.primemedicalcentre.domain.dto.serviceOfDoctor.ServiceOfDoctorResponseDTO;
import uz.result.primemedicalcentre.domain.entity.Photo;
import uz.result.primemedicalcentre.domain.entity.doctor.Doctor;
import uz.result.primemedicalcentre.domain.entity.doctor.ServiceOfDoctor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DoctorResponseDTO {

    Long id;

    String slug;

    LanguageDTO fullName;

    LanguageDTO description;

    LanguageDTO experience;

    LanguageDTO receptionTime;

    boolean active;

    Photo photo;

    List<SpecializationResponseDTO> specializationList;

    List<EducationResponseDTO> educationList;

    List<ServiceOfDoctorResponseDTO> serviceOfDoctorList;

    public DoctorResponseDTO(Doctor doctor) {
        this.id = doctor.getId();
        this.slug = doctor.getSlug();
        this.active = doctor.isActive();
        this.photo = doctor.getPhoto();
        this.fullName = new LanguageDTO(doctor.getFullNameUz(), doctor.getFullNameRu());
        this.description = new LanguageDTO(doctor.getDescriptionUz(), doctor.getDescriptionRu());
        this.experience = new LanguageDTO(doctor.getExperienceUz(), doctor.getExperienceRu());
        this.receptionTime = new LanguageDTO(doctor.getReceptionTimeUz(), doctor.getReceptionTimeRu());
        if (doctor.getSpecializationList() != null) {
            this.specializationList = doctor.getSpecializationList().stream().map(
                    SpecializationResponseDTO::new
            ).collect(Collectors.toList());
        }
        if (doctor.getEducationList() != null) {
            this.educationList = doctor.getEducationList().stream().map(
                    EducationResponseDTO::new
            ).collect(Collectors.toList());
        }
        if (doctor.getService() != null) {
            this.serviceOfDoctorList = doctor.getService().stream().map(
                    ServiceOfDoctorResponseDTO::new
            ).collect(Collectors.toList());
        }

    }

}
