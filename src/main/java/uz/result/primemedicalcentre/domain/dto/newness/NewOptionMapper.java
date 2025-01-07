package uz.result.primemedicalcentre.domain.dto.newness;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import uz.result.primemedicalcentre.domain.entity.NewOption;
import uz.result.primemedicalcentre.domain.entity.Photo;
import uz.result.primemedicalcentre.exception.LanguageNotSupportException;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewOptionMapper {

    Long id;

    String title;

    String body;

    Photo photo;

    Integer orderNum;

    public NewOptionMapper(NewOption option, String lang) {
        this.id = option.getId();
        this.photo = option.getPhoto();
        this.orderNum= option.getOrderNum();
        switch (lang.toLowerCase()) {

            case "uz": {
                this.title = option.getTitleUz();
                this.body = option.getBodyUz();
                break;
            }

            case "ru": {
                this.title = option.getTitleRu();
                this.body = option.getBodyRu();
                break;
            }

            default:
                throw new LanguageNotSupportException("Language is not supported: " + lang);
        }
    }

    public static NewOption updateDtoToEntity(NewOptionUpdateDTO updateDTO) {
        if (updateDTO == null) {
            return null;
        }
        NewOption option = new NewOption();
        option.setId(updateDTO.getId());
        option.setPhoto(updateDTO.getPhoto());
        option.setOrderNum(updateDTO.getOrderNum());
        if (updateDTO.getTitle() != null) {
            option.setTitleUz(updateDTO.getTitle().getUz());
            option.setTitleRu(updateDTO.getTitle().getRu());
        }
        if (updateDTO.getBody() != null) {
            option.setBodyUz(updateDTO.getBody().getUz());
            option.setBodyRu(updateDTO.getBody().getRu());
        }
        return option;
    }

}
