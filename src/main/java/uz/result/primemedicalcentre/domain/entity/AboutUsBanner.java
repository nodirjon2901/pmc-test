package uz.result.primemedicalcentre.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import uz.result.primemedicalcentre.domain.dto.aboutUs.banner.AboutUsBannerCreateDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "about_us_banner")
public class AboutUsBanner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String titleUz;

    String titleRu;

    @Column(length = 3000)
    String bodyUz;

    @Column(length = 3000)
    String bodyRu;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    Photo photo;

    public AboutUsBanner(AboutUsBannerCreateDTO createDTO) {
        if (createDTO == null) {
            return;
        }
        if (createDTO.getTitle()!=null){
            this.titleUz = createDTO.getTitle().getUz();
            this.titleRu = createDTO.getTitle().getRu();
        }
        if (createDTO.getBody()!=null){
            this.bodyUz = createDTO.getBody().getUz();
            this.bodyRu = createDTO.getBody().getRu();
        }

    }

}
