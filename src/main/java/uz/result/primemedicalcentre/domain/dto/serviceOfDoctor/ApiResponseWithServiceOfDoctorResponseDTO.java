package uz.result.primemedicalcentre.domain.dto.serviceOfDoctor;

import io.swagger.v3.oas.annotations.media.Schema;
import uz.result.primemedicalcentre.domain.dto.ApiResponse;

@Schema(description = "API Response containing TeamResponseDTO")
public class ApiResponseWithServiceOfDoctorResponseDTO extends ApiResponse<ServiceOfDoctorResponseDTO> {

    public ApiResponseWithServiceOfDoctorResponseDTO(String message, ServiceOfDoctorResponseDTO data) {
        super(message, data);
    }

}
