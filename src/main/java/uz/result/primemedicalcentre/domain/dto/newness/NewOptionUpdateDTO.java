package uz.result.primemedicalcentre.domain.dto.newness;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import uz.result.primemedicalcentre.domain.dto.LanguageDTO;
import uz.result.primemedicalcentre.domain.entity.Photo;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewOptionUpdateDTO {

    Long id;

    LanguageDTO title;

    LanguageDTO body;

    Photo photo;

    Integer orderNum;

}
