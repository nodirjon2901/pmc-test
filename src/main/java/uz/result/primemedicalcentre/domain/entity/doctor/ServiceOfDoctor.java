package uz.result.primemedicalcentre.domain.entity.doctor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import uz.result.primemedicalcentre.domain.dto.serviceOfDoctor.ServiceOfDoctorCreateDTO;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "doctor_service")
public class ServiceOfDoctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String nameUz;

    String nameRu;

    String priceUz;

    String priceRu;

    boolean active;

    @ManyToOne
    @JsonIgnore
    Doctor doctor;

    public ServiceOfDoctor(ServiceOfDoctorCreateDTO createDTO) {
        if (createDTO == null) {
            return;
        }
        if (createDTO.getName() != null) {
            this.nameUz = createDTO.getName().getUz();
            this.nameRu = createDTO.getName().getRu();
        }
        if (createDTO.getPrice() != null) {
            this.priceUz = createDTO.getPrice().getUz();
            this.priceRu = createDTO.getPrice().getRu();
        }
    }
}
