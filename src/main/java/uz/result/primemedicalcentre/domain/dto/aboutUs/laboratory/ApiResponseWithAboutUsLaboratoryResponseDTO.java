package uz.result.primemedicalcentre.domain.dto.aboutUs.laboratory;

import io.swagger.v3.oas.annotations.media.Schema;
import uz.result.primemedicalcentre.domain.dto.ApiResponse;

@Schema(description = "API Response containing TeamResponseDTO")
public class ApiResponseWithAboutUsLaboratoryResponseDTO extends ApiResponse<AboutUsLaboratoryResponseDTO> {

    public ApiResponseWithAboutUsLaboratoryResponseDTO(String message, AboutUsLaboratoryResponseDTO data) {
        super(message, data);
    }

}
