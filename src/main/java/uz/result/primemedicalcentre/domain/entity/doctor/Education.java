package uz.result.primemedicalcentre.domain.entity.doctor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import uz.result.primemedicalcentre.domain.dto.LanguageDTO;
import uz.result.primemedicalcentre.domain.dto.doctor.EducationCreateDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "doctor_education")
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String startYear;

    String finishYear;

    String institutionUz;

    String institutionRu;

    String qualificationUz;

    String qualificationRu;

    @ManyToOne
    @JsonIgnore
    Doctor doctor;

    public Education(EducationCreateDTO createDTO){
        if (createDTO==null){
            return;
        }
        this.startYear=createDTO.getStartYear();
        this.finishYear=createDTO.getFinishYear();
        if (createDTO.getInstitution()!=null){
            this.institutionUz=createDTO.getInstitution().getUz();
            this.institutionRu=createDTO.getInstitution().getRu();
        }
        if (createDTO.getQualification()!=null){
            this.qualificationUz=createDTO.getQualification().getUz();
            this.qualificationRu=createDTO.getQualification().getRu();
        }
    }

}
