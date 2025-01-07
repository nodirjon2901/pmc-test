package uz.result.primemedicalcentre.domain.dto.doctor;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import uz.result.primemedicalcentre.domain.dto.LanguageDTO;
import uz.result.primemedicalcentre.domain.dto.serviceOfDoctor.ServiceOfDoctorCreateDTO;
import uz.result.primemedicalcentre.domain.entity.doctor.ServiceOfDoctor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DoctorCreateDTO {

    LanguageDTO fullName;

    LanguageDTO description;

    LanguageDTO experience;

    LanguageDTO receptionTime;

    List<SpecializationCreateDTO> specializationList;

//    List<EducationCreateDTO> educationList;
//
//    List<ServiceOfDoctorCreateDTO> serviceOfDoctorList;

}
