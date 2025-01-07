package uz.result.primemedicalcentre.domain.dto.newness;

import io.swagger.v3.oas.annotations.media.Schema;
import uz.result.primemedicalcentre.domain.dto.ApiResponse;

@Schema(description = "API Response containing TeamResponseDTO")
public class ApiResponseWithNewnessMapper extends ApiResponse<NewnessMapper> {

    public ApiResponseWithNewnessMapper(String message, NewnessMapper data) {
        super(message, data);
    }

}
