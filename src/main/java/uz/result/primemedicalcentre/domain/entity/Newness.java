package uz.result.primemedicalcentre.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import uz.result.primemedicalcentre.domain.dto.newness.NewnessCreateDTO;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "newness")
public class Newness {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    String slug;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    Date createdDate;

    boolean active;

    @OneToMany(mappedBy = "newness", cascade = CascadeType.ALL, orphanRemoval = true)
    List<NewOption> optionList;

    @PostPersist
    @PostUpdate
    private void setOption() {
        if (this.optionList != null) {
            this.optionList.forEach(i -> i.setNewness(this));
        }
    }

    public Newness(NewnessCreateDTO createDTO) {
        if (createDTO == null)
            return;
        if (createDTO.getOptionList() != null && !createDTO.getOptionList().isEmpty()) {
            this.optionList = createDTO.getOptionList().stream().map(
                    NewOption::new
            ).collect(Collectors.toList());
        }
    }

}
