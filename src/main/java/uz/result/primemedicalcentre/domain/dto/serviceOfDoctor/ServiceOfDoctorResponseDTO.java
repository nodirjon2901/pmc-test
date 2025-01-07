package uz.result.primemedicalcentre.domain.dto.serviceOfDoctor;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import uz.result.primemedicalcentre.domain.dto.LanguageDTO;
import uz.result.primemedicalcentre.domain.entity.doctor.ServiceOfDoctor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceOfDoctorResponseDTO {

    Long id;

    LanguageDTO name;

    LanguageDTO price;

    boolean active;

    public ServiceOfDoctorResponseDTO(ServiceOfDoctor service) {
        this.id = service.getId();
        this.active = service.isActive();
        this.name = new LanguageDTO(service.getNameUz(), service.getNameRu());
        this.price = new LanguageDTO(service.getPriceUz(), service.getPriceRu());
    }

}
