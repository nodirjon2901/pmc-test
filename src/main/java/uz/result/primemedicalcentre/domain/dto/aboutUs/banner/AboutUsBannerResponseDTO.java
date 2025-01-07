package uz.result.primemedicalcentre.domain.dto.aboutUs.banner;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import uz.result.primemedicalcentre.domain.dto.LanguageDTO;
import uz.result.primemedicalcentre.domain.entity.AboutUsBanner;
import uz.result.primemedicalcentre.domain.entity.Photo;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AboutUsBannerResponseDTO {

    Long id;

    LanguageDTO title;

    LanguageDTO body;

    Photo photo;

    public AboutUsBannerResponseDTO(AboutUsBanner banner) {
        this.id = banner.getId();
        this.photo = banner.getPhoto();
        this.title = new LanguageDTO(banner.getTitleUz(), banner.getTitleRu());
        this.body = new LanguageDTO(banner.getBodyUz(), banner.getBodyRu());
    }

}
