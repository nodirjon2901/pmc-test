package uz.result.primemedicalcentre.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import uz.result.primemedicalcentre.domain.dto.service.ServiceCreateDTO;
import uz.result.primemedicalcentre.domain.entity.doctor.Doctor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "service")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    String slug;

    String nameUz;

    String nameRu;

    @Column(length = 3000)
    String descriptionUz;

    @Column(length = 3000)
    String descriptionRu;

    boolean active;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    Photo photo;

    public Service(ServiceCreateDTO createDTO) {
        if (createDTO == null) {
            return;
        }
        if (createDTO.getName() != null) {
            this.nameUz = createDTO.getName().getUz();
            this.nameRu = createDTO.getName().getRu();
        }
        if (createDTO.getDescription() != null) {
            this.descriptionUz = createDTO.getDescription().getUz();
            this.descriptionRu = createDTO.getDescription().getRu();
        }
    }

}
