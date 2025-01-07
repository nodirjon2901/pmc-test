package uz.result.primemedicalcentre.domain.dto.service;

import io.swagger.v3.oas.annotations.media.Schema;
import uz.result.primemedicalcentre.domain.dto.ApiResponse;

@Schema(description = "API Response containing TeamResponseDTO")
public class ApiResponseWithServiceResponseDTO extends ApiResponse<ServiceResponseDTO> {

    public ApiResponseWithServiceResponseDTO(String message, ServiceResponseDTO data) {
        super(message, data);
    }

}
