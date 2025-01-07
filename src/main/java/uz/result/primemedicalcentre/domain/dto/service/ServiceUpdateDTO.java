package uz.result.primemedicalcentre.domain.dto.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import uz.result.primemedicalcentre.domain.dto.LanguageDTO;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceUpdateDTO {

    Long id;

    LanguageDTO name;

    LanguageDTO description;

    List<Long> doctorIds;

    boolean active;

}
