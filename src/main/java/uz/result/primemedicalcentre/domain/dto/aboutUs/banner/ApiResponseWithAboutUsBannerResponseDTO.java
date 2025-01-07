package uz.result.primemedicalcentre.domain.dto.aboutUs.banner;

import io.swagger.v3.oas.annotations.media.Schema;
import uz.result.primemedicalcentre.domain.dto.ApiResponse;

@Schema(description = "API Response containing TeamResponseDTO")
public class ApiResponseWithAboutUsBannerResponseDTO extends ApiResponse<AboutUsBannerResponseDTO> {

    public ApiResponseWithAboutUsBannerResponseDTO(String message, AboutUsBannerResponseDTO data) {
        super(message, data);
    }

}
