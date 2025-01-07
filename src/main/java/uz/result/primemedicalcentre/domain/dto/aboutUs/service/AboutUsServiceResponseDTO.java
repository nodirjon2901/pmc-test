package uz.result.primemedicalcentre.domain.dto.aboutUs.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import uz.result.primemedicalcentre.domain.dto.LanguageDTO;
import uz.result.primemedicalcentre.domain.entity.AboutUsService;
import uz.result.primemedicalcentre.domain.entity.Photo;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AboutUsServiceResponseDTO {

    Long id;

    LanguageDTO title;

    LanguageDTO body;

    Photo photo;

    public AboutUsServiceResponseDTO(AboutUsService service) {
        this.id = service.getId();
        this.photo = service.getPhoto();
        this.title = new LanguageDTO(service.getTitleUz(), service.getTitleRu());
        this.body = new LanguageDTO(service.getBodyUz(), service.getBodyRu());
    }

}
