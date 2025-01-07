package uz.result.primemedicalcentre.domain.dto.newness;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import uz.result.primemedicalcentre.domain.entity.Newness;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewnessMapper {

    Long id;

    String slug;

    Date createdDate;

    boolean active;

    List<NewOptionMapper> optionList;

    public NewnessMapper(Newness newness, String lang) {
        this.id = newness.getId();
        this.slug = newness.getSlug();
        this.active = newness.isActive();
        this.createdDate = newness.getCreatedDate();
        this.optionList = newness.getOptionList().stream().map(
                option -> new NewOptionMapper(option, lang)
        ).collect(Collectors.toList());
    }

    public static Newness updateDtoToEntity(NewnessUpdateDTO updateDTO) {
        if (updateDTO == null) {
            return null;
        }
        Newness newness = new Newness();
        newness.setId(updateDTO.getId());
        newness.setActive(newness.isActive());
        if (updateDTO.getOptionList() != null && !updateDTO.getOptionList().isEmpty()) {
            newness.setOptionList(
                    updateDTO.getOptionList().stream().map(
                            NewOptionMapper::updateDtoToEntity
                    ).collect(Collectors.toList())
            );
        }
        return newness;
    }


}
