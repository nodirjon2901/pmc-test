package uz.result.primemedicalcentre.domain.entity.doctor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import uz.result.primemedicalcentre.domain.dto.doctor.DoctorCreateDTO;
import uz.result.primemedicalcentre.domain.entity.Photo;
import uz.result.primemedicalcentre.domain.entity.Service;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    String slug;

    String fullNameUz;

    String fullNameRu;

    @Column(length = 2000)
    String descriptionUz;

    @Column(length = 2000)
    String descriptionRu;

    String experienceUz;

    String experienceRu;

    String receptionTimeUz;

    String receptionTimeRu;

    boolean active;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    Photo photo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctor")
    List<Specialization> specializationList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctor")
    List<Education> educationList;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctor")
    List<ServiceOfDoctor> service;

    @PostPersist
    @PostUpdate
    private void setOption() {
        if (this.educationList != null) {
            this.educationList.forEach(i -> i.setDoctor(this));
        }
        if (this.specializationList != null) {
            this.specializationList.forEach(i -> i.setDoctor(this));
        }
        if (this.service != null) {
            this.service.forEach(i -> i.setDoctor(this));
        }
    }

    public Doctor(DoctorCreateDTO createDTO) {
        if (createDTO == null) {
            return;
        }
        if (createDTO.getFullName() != null) {
            this.fullNameUz = createDTO.getFullName().getUz();
            this.fullNameRu = createDTO.getFullName().getRu();
        }
        if (createDTO.getDescription() != null) {
            this.descriptionUz = createDTO.getDescription().getUz();
            this.descriptionRu = createDTO.getDescription().getRu();
        }
        if (createDTO.getExperience() != null) {
            this.experienceUz = createDTO.getExperience().getUz();
            this.experienceRu = createDTO.getExperience().getRu();
        }
        if (createDTO.getReceptionTime() != null) {
            this.receptionTimeUz = createDTO.getReceptionTime().getUz();
            this.receptionTimeRu = createDTO.getReceptionTime().getRu();
        }
        if (createDTO.getSpecializationList() != null && !createDTO.getSpecializationList().isEmpty()) {
            this.specializationList = createDTO.getSpecializationList().stream().map(
                    Specialization::new
            ).collect(Collectors.toList());
        }
//        if (createDTO.getEducationList() != null && !createDTO.getEducationList().isEmpty()) {
//            this.educationList = createDTO.getEducationList().stream().map(
//                    Education::new
//            ).collect(Collectors.toList());
//        }
//        if (createDTO.getServiceOfDoctorList() != null && !createDTO.getServiceOfDoctorList().isEmpty()) {
//            this.service = createDTO.getServiceOfDoctorList().stream().map(
//                    ServiceOfDoctor::new
//            ).collect(Collectors.toList());
//        }
    }

}
