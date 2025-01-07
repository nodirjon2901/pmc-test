package uz.result.primemedicalcentre.domain.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import uz.result.primemedicalcentre.domain.dto.LanguageDTO;
import uz.result.primemedicalcentre.domain.entity.Photo;
import uz.result.primemedicalcentre.domain.entity.Service;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceResponseDTO {

    Long id;

    String slug;

    LanguageDTO name;

    LanguageDTO description;

    boolean active;

    Photo photo;

    public ServiceResponseDTO(Service service) {
        this.id = service.getId();
        this.slug=service.getSlug();
        this.active = service.isActive();
        this.photo = service.getPhoto();
        this.name = new LanguageDTO(service.getNameUz(), service.getNameRu());
        this.description = new LanguageDTO(service.getDescriptionUz(), service.getDescriptionRu());
    }

}
