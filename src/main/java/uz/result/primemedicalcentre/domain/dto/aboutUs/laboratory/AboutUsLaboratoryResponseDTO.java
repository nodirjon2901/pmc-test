package uz.result.primemedicalcentre.domain.dto.aboutUs.laboratory;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import uz.result.primemedicalcentre.domain.dto.LanguageDTO;
import uz.result.primemedicalcentre.domain.entity.AboutUsLaboratory;
import uz.result.primemedicalcentre.domain.entity.Photo;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AboutUsLaboratoryResponseDTO {

    Long id;

    LanguageDTO title;

    LanguageDTO body;

    Photo photo;

    public AboutUsLaboratoryResponseDTO(AboutUsLaboratory laboratory) {
        this.id = laboratory.getId();
        this.photo = laboratory.getPhoto();
        this.title = new LanguageDTO(laboratory.getTitleUz(), laboratory.getTitleRu());
        this.body = new LanguageDTO(laboratory.getBodyUz(), laboratory.getBodyRu());
    }

}
