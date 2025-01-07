package uz.result.primemedicalcentre.domain.dto.serviceOfDoctor;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import uz.result.primemedicalcentre.domain.dto.LanguageDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceOfDoctorUpdate {

    Long id;

    LanguageDTO name;

    LanguageDTO price;

    boolean active;

}
