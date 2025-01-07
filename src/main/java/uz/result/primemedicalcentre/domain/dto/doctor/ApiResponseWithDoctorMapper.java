package uz.result.primemedicalcentre.domain.dto.doctor;

import io.swagger.v3.oas.annotations.media.Schema;
import uz.result.primemedicalcentre.domain.dto.ApiResponse;

@Schema(description = "API Response containing TeamResponseDTO")
public class ApiResponseWithDoctorMapper extends ApiResponse<DoctorMapper> {

    public ApiResponseWithDoctorMapper(String message, DoctorMapper data) {
        super(message, data);
    }

}
