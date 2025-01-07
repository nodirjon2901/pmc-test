package uz.result.primemedicalcentre.domain.dto.service;

import io.swagger.v3.oas.annotations.media.Schema;
import uz.result.primemedicalcentre.domain.dto.ApiResponse;

@Schema(description = "API Response containing TeamResponseDTO")
public class ApiResponseWithServiceMapper extends ApiResponse<ServiceMapper> {

    public ApiResponseWithServiceMapper(String message, ServiceMapper data) {
        super(message, data);
    }

}
