package uz.result.primemedicalcentre.domain.dto.aboutUs.laboratory;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import uz.result.primemedicalcentre.domain.entity.AboutUsLaboratory;
import uz.result.primemedicalcentre.domain.entity.Photo;
import uz.result.primemedicalcentre.exception.LanguageNotSupportException;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AboutUsLaboratoryMapper {

    Long id;

    String title;

    String body;

    Photo photo;

    public AboutUsLaboratoryMapper(AboutUsLaboratory laboratory, String lang) {
        this.id = laboratory.getId();
        this.photo = laboratory.getPhoto();
        switch (lang.toLowerCase()) {

            case "uz": {
                this.title = laboratory.getTitleUz();
                this.body = laboratory.getBodyUz();
                break;
            }

            case "ru": {
                this.title = laboratory.getTitleRu();
                this.body = laboratory.getBodyRu();
                break;
            }

            default:
                throw new LanguageNotSupportException("Language is not supported: " + lang);

        }
    }

    public static AboutUsLaboratory updateDtoToEntity(AboutUsLaboratoryUpdateDTO updateDTO) {
        if (updateDTO == null) {
            return null;
        }
        AboutUsLaboratory laboratory = new AboutUsLaboratory();
        laboratory.setId(updateDTO.getId());
        if (updateDTO.getTitle() != null) {
            laboratory.setTitleUz(updateDTO.getTitle().getUz());
            laboratory.setTitleRu(updateDTO.getTitle().getRu());
        }
        if (updateDTO.getBody() != null) {
            laboratory.setBodyUz(updateDTO.getBody().getUz());
            laboratory.setBodyRu(updateDTO.getBody().getRu());
        }
        return laboratory;
    }

}
