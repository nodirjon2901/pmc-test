package uz.result.primemedicalcentre.domain.dto.newness;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import uz.result.primemedicalcentre.domain.dto.LanguageDTO;
import uz.result.primemedicalcentre.domain.entity.NewOption;
import uz.result.primemedicalcentre.domain.entity.Photo;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewOptionResponseDTO {

    Long id;

    LanguageDTO title;

    LanguageDTO body;

    Photo photo;

    Integer orderNum;

    public NewOptionResponseDTO(NewOption option) {
        this.id = option.getId();
        this.photo = option.getPhoto();
        this.orderNum= option.getOrderNum();
        this.title = new LanguageDTO(option.getTitleUz(), option.getTitleRu());
        this.body = new LanguageDTO(option.getBodyUz(), option.getBodyRu());
    }

}
