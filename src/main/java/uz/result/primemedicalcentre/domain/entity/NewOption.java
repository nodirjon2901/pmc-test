package uz.result.primemedicalcentre.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import uz.result.primemedicalcentre.domain.dto.newness.NewOptionCreateDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "new_option")
public class NewOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(length = 500)
    String titleUz;

    @Column(length = 500)
    String titleRu;

    @Column(length = 5000)
    String bodyUz;

    @Column(length = 5000)
    String bodyRu;

    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    Photo photo;

    Integer orderNum;

    @ManyToOne
    @JsonIgnore
    Newness newness;

    public NewOption(NewOptionCreateDTO createDTO) {
        if (createDTO == null)
            return;
        if (createDTO.getTitle() != null) {
            this.titleUz = createDTO.getTitle().getUz();
            this.titleRu = createDTO.getTitle().getRu();
        }
        if (createDTO.getBody() != null) {
            this.bodyUz = createDTO.getBody().getUz();
            this.bodyRu = createDTO.getBody().getRu();
        }
        this.orderNum = createDTO.getOrderNum();
    }

}
