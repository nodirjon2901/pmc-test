package uz.result.primemedicalcentre.domain.dto.doctor;

import io.swagger.v3.oas.annotations.media.Schema;
import uz.result.primemedicalcentre.domain.dto.ApiResponse;

@Schema(description = "API Response containing TeamResponseDTO")
public class ApiResponseWithDoctorResponseDTO extends ApiResponse<DoctorResponseDTO> {

    public ApiResponseWithDoctorResponseDTO(String message, DoctorResponseDTO data) {
        super(message, data);
    }

}
