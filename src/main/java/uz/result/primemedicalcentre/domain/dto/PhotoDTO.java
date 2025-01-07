package uz.result.primemedicalcentre.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.result.primemedicalcentre.domain.entity.Photo;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoDTO {

    private Long id;

    private String url;

    public PhotoDTO(Photo photo) {
        if (photo == null)
            return;
        this.id = photo.getId();
        this.url = photo.getHttpUrl();
    }

}
