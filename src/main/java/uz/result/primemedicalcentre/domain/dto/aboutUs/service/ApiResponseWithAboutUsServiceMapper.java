package uz.result.primemedicalcentre.domain.dto.aboutUs.service;

import io.swagger.v3.oas.annotations.media.Schema;
import uz.result.primemedicalcentre.domain.dto.ApiResponse;

@Schema(description = "API Response containing TeamResponseDTO")
public class ApiResponseWithAboutUsServiceMapper extends ApiResponse<AboutUsServiceMapper> {

    public ApiResponseWithAboutUsServiceMapper(String message, AboutUsServiceMapper data) {
        super(message, data);
    }

}
