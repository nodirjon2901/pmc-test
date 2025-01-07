package uz.result.primemedicalcentre.domain.dto.serviceOfDoctor;

import io.swagger.v3.oas.annotations.media.Schema;
import uz.result.primemedicalcentre.domain.dto.ApiResponse;

@Schema(description = "API Response containing TeamResponseDTO")
public class ApiResponseWithServiceOfDoctorMapper extends ApiResponse<ServiceOfDoctorMapper> {

    public ApiResponseWithServiceOfDoctorMapper(String message, ServiceOfDoctorMapper data) {
        super(message, data);
    }

}
