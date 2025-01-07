package uz.result.primemedicalcentre.domain.entity.doctor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import uz.result.primemedicalcentre.domain.dto.doctor.SpecializationCreateDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "doctor_specialization")
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String nameUz;

    String nameRu;

    @ManyToOne
    @JsonIgnore
    Doctor doctor;

    public Specialization(SpecializationCreateDTO createDTO) {
        if (createDTO == null) {
            return;
        }
        if (createDTO.getName() != null) {
            this.nameUz = createDTO.getName().getUz();
            this.nameRu = createDTO.getName().getRu();
        }
    }

}
