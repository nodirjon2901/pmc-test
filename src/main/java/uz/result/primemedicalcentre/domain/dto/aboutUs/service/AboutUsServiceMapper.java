package uz.result.primemedicalcentre.domain.dto.aboutUs.service;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import uz.result.primemedicalcentre.domain.entity.AboutUsService;
import uz.result.primemedicalcentre.domain.entity.Photo;
import uz.result.primemedicalcentre.exception.LanguageNotSupportException;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AboutUsServiceMapper {

    Long id;

    String title;

    String body;

    Photo photo;

    public AboutUsServiceMapper(AboutUsService service, String lang) {
        this.id = service.getId();
        this.photo = service.getPhoto();
        switch (lang.toLowerCase()) {

            case "uz": {
                this.title = service.getTitleUz();
                this.body = service.getBodyUz();
                break;
            }

            case "ru": {
                this.title = service.getTitleRu();
                this.body = service.getBodyRu();
                break;
            }

            default:
                throw new LanguageNotSupportException("Language is not supported: " + lang);

        }
    }

    public static AboutUsService updateDtoToEntity(AboutUsServiceUpdateDTO updateDTO) {
        if (updateDTO == null) {
            return null;
        }
        AboutUsService service = new AboutUsService();
        service.setId(updateDTO.getId());
        if (updateDTO.getTitle() != null) {
            service.setTitleUz(updateDTO.getTitle().getUz());
            service.setTitleRu(updateDTO.getTitle().getRu());
        }
        if (updateDTO.getBody() != null) {
            service.setBodyUz(updateDTO.getBody().getUz());
            service.setBodyRu(updateDTO.getBody().getRu());
        }
        return service;
    }

}
