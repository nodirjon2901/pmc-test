package uz.result.primemedicalcentre.domain.dto.doctor;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import uz.result.primemedicalcentre.domain.dto.LanguageDTO;
import uz.result.primemedicalcentre.domain.dto.serviceOfDoctor.ServiceOfDoctorUpdate;
import uz.result.primemedicalcentre.domain.entity.Photo;
import uz.result.primemedicalcentre.domain.entity.doctor.ServiceOfDoctor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DoctorUpdateDTO {

    Long id;

    LanguageDTO fullName;

    LanguageDTO description;

    LanguageDTO experience;

    LanguageDTO receptionTime;

    boolean active;

    List<SpecializationResponseDTO> specializationList;

//    List<EducationResponseDTO> educationList;
//
//    List<ServiceOfDoctorUpdate> serviceOfDoctorList;


}
