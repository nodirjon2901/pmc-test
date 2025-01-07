package uz.result.primemedicalcentre.domain.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import uz.result.primemedicalcentre.domain.dto.LanguageDTO;
import uz.result.primemedicalcentre.domain.entity.Photo;
import uz.result.primemedicalcentre.domain.entity.Service;
import uz.result.primemedicalcentre.exception.LanguageNotSupportException;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceMapper {

    Long id;

    String slug;

    String name;

    String description;

    boolean active;

    Photo photo;

    public ServiceMapper(Service service, String lang) {
        this.id = service.getId();
        this.slug = service.getSlug();
        this.active = service.isActive();
        this.photo = service.getPhoto();

        switch (lang.toLowerCase()) {

            case "uz": {
                this.name = service.getNameUz();
                this.description = service.getDescriptionUz();
                break;
            }

            case "ru": {
                this.name = service.getNameRu();
                this.description = service.getDescriptionRu();
                break;
            }

            default:
                throw new LanguageNotSupportException("Language not supported: " + lang);
        }
    }

    public static Service updateDtoToEntity(ServiceUpdateDTO updateDTO) {
        if (updateDTO == null) {
            return null;
        }
        Service service = new Service();
        service.setId(updateDTO.getId());
        service.setActive(updateDTO.isActive());
        if (updateDTO.getName() != null) {
            service.setNameUz(updateDTO.getName().getUz());
            service.setNameRu(updateDTO.getName().getRu());
        }
        if (updateDTO.getDescription() != null) {
            service.setDescriptionUz(updateDTO.getDescription().getUz());
            service.setDescriptionRu(updateDTO.getDescription().getRu());
        }
        return service;
    }

}
