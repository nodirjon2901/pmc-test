package uz.result.primemedicalcentre.domain.dto.aboutUs.service;

import io.swagger.v3.oas.annotations.media.Schema;
import uz.result.primemedicalcentre.domain.dto.ApiResponse;

@Schema(description = "API Response containing TeamResponseDTO")
public class ApiResponseWithAboutUsServiceResponseDTO extends ApiResponse<AboutUsServiceResponseDTO> {

    public ApiResponseWithAboutUsServiceResponseDTO(String message, AboutUsServiceResponseDTO data) {
        super(message, data);
    }

}
