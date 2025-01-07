package uz.result.primemedicalcentre.domain.dto.serviceOfDoctor;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import uz.result.primemedicalcentre.domain.dto.newness.NewOptionUpdateDTO;
import uz.result.primemedicalcentre.domain.entity.NewOption;
import uz.result.primemedicalcentre.domain.entity.doctor.ServiceOfDoctor;
import uz.result.primemedicalcentre.exception.LanguageNotSupportException;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceOfDoctorMapper {

    Long id;

    String name;

    String price;

    boolean active;

    public ServiceOfDoctorMapper(ServiceOfDoctor service, String lang) {
        this.id = service.getId();
        this.active = service.isActive();
        switch (lang.toLowerCase()) {

            case "uz": {
                this.name = service.getNameUz();
                this.price = service.getPriceUz();
                break;
            }

            case "ru": {
                this.name = service.getNameRu();
                this.price = service.getPriceRu();
                break;
            }

            default:
                throw new LanguageNotSupportException("Language is not supported: " + lang);

        }
    }

    public static ServiceOfDoctor updateDtoToEntity(ServiceOfDoctorUpdate updateDTO) {
        if (updateDTO == null) {
            return null;
        }
        ServiceOfDoctor serviceOfDoctor = new ServiceOfDoctor();
        serviceOfDoctor.setId(updateDTO.getId());
        serviceOfDoctor.setActive(updateDTO.isActive());
        if (updateDTO.getName() != null) {
            serviceOfDoctor.setNameUz(updateDTO.getName().getUz());
            serviceOfDoctor.setNameRu(updateDTO.getName().getRu());
        }
        if (updateDTO.getPrice() != null) {
            serviceOfDoctor.setPriceUz(updateDTO.getPrice().getUz());
            serviceOfDoctor.setPriceRu(updateDTO.getPrice().getRu());
        }
        return serviceOfDoctor;
    }

}
