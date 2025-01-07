package uz.result.primemedicalcentre.domain.dto.aboutUs.banner;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import uz.result.primemedicalcentre.domain.entity.AboutUsBanner;
import uz.result.primemedicalcentre.domain.entity.Photo;
import uz.result.primemedicalcentre.exception.LanguageNotSupportException;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AboutUsBannerMapper {

    Long id;

    String title;

    String body;

    Photo photo;

    public AboutUsBannerMapper(AboutUsBanner banner, String lang) {
        this.id = banner.getId();
        this.photo = banner.getPhoto();
        switch (lang.toLowerCase()) {

            case "uz": {
                this.title = banner.getTitleUz();
                this.body = banner.getBodyUz();
                break;
            }

            case "ru": {
                this.title = banner.getTitleRu();
                this.body = banner.getBodyRu();
                break;
            }

            default:
                throw new LanguageNotSupportException("Language is not supported: " + lang);
        }
    }

    public static AboutUsBanner updateDtoToEntity(AboutUsBannerUpdateDTO updateDTO) {
        if (updateDTO == null) {
            return null;
        }
        AboutUsBanner banner = new AboutUsBanner();
        banner.setId(updateDTO.getId());
        if (updateDTO.getTitle() != null) {
            banner.setTitleUz(updateDTO.getTitle().getUz());
            banner.setTitleRu(updateDTO.getTitle().getRu());
        }
        if (updateDTO.getBody() != null) {
            banner.setBodyUz(updateDTO.getBody().getUz());
            banner.setBodyRu(updateDTO.getBody().getRu());
        }

        return banner;
    }

}
