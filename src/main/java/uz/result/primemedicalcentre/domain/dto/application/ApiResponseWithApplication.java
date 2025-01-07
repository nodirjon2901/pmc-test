package uz.result.primemedicalcentre.domain.dto.application;

import io.swagger.v3.oas.annotations.media.Schema;
import uz.result.primemedicalcentre.domain.dto.ApiResponse;
import uz.result.primemedicalcentre.domain.entity.Application;

@Schema(description = "API Response containing TeamResponseDTO")
public class ApiResponseWithApplication extends ApiResponse<Application> {

    public ApiResponseWithApplication(String message, Application data) {
        super(message, data);
    }

}
