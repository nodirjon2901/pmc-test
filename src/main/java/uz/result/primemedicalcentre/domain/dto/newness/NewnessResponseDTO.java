package uz.result.primemedicalcentre.domain.dto.newness;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import uz.result.primemedicalcentre.domain.dto.LanguageDTO;
import uz.result.primemedicalcentre.domain.entity.Newness;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewnessResponseDTO {

    Long id;

    String slug;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    Date createdDate;

    boolean active;

    List<NewOptionResponseDTO> optionList;

    public NewnessResponseDTO(Newness newness) {
        this.id = newness.getId();
        this.slug= newness.getSlug();
        this.active = newness.isActive();
        this.createdDate = newness.getCreatedDate();
        this.optionList = newness.getOptionList().stream().map(
                NewOptionResponseDTO::new
        ).collect(Collectors.toList());
    }

}
