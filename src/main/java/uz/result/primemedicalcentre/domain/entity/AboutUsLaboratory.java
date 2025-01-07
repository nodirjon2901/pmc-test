package uz.result.primemedicalcentre.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "about_us_laboratory")
public class AboutUsLaboratory {

    @Id
    @GeneratedValue
    Long id;

    String titleUz;

    String titleRu;

    @Column(length = 3000)
    String bodyUz;

    @Column(length = 3000)
    String bodyRu;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    Photo photo;

}
