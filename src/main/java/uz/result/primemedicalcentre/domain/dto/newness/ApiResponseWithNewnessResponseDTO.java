package uz.result.primemedicalcentre.domain.dto.newness;

import io.swagger.v3.oas.annotations.media.Schema;
import uz.result.primemedicalcentre.domain.dto.ApiResponse;

@Schema(description = "API Response containing TeamResponseDTO")
public class ApiResponseWithNewnessResponseDTO extends ApiResponse<NewnessResponseDTO> {

    public ApiResponseWithNewnessResponseDTO(String message, NewnessResponseDTO data) {
        super(message, data);
    }

}
