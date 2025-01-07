package uz.result.primemedicalcentre.domain.dto.aboutUs.banner;

import io.swagger.v3.oas.annotations.media.Schema;
import uz.result.primemedicalcentre.domain.dto.ApiResponse;

@Schema(description = "API Response containing TeamResponseDTO")
public class ApiResponseWithAboutUsBannerMapper extends ApiResponse<AboutUsBannerMapper> {

    public ApiResponseWithAboutUsBannerMapper(String message, AboutUsBannerMapper data) {
        super(message, data);
    }

}
