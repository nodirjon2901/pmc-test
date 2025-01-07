package uz.result.primemedicalcentre.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.result.primemedicalcentre.domain.dto.ApiResponse;
import uz.result.primemedicalcentre.domain.dto.application.ApiResponseWithApplication;
import uz.result.primemedicalcentre.domain.dto.application.ApplicationCreateDTO;
import uz.result.primemedicalcentre.domain.entity.Application;
import uz.result.primemedicalcentre.service.ApplicationService;

@RestController
@RequestMapping("/v1/application")
@RequiredArgsConstructor
@Tag(name = "Application - Заяфка")
public class ApplicationController {

    private final ApplicationService applicationService;

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "If successfully created you get '201' - Если успешно создан, вы получите статус '201'",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWithApplication.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "If you get 400 status Please read response 'message' - Если получить статус 400, прочитайте ответное сообщение 'message'",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class))
            )
    })
    @Operation(summary = "Add or create new 'Application ' - Добавить или создать новую 'Заяфка'")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "CreateDto containing information of Application - CreateDto, содержащий информацию Заяфка",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApplicationCreateDTO.class)
            )
    )
    @PostMapping(value = "/create", consumes = {"application/json"})
    public ResponseEntity<ApiResponse<Application>> create(
            @RequestBody ApplicationCreateDTO createDTO
    ) {
        return applicationService.create(createDTO);
    }

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "You get 200 status code when successfully deleted - Вы получите код статуса 200, когда данные будут успешно удалены",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class)
                    )),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "If get 400 Please READ RESPONSE MESSAGE! - Если получите 400, пожалуйста, ПРОЧИТАЙТЕ ОТВЕТНОЕ СООБЩЕНИЕ!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class)
                    ))
    })
    @Operation(summary = "Send 'id' in path which 'application' need delete - Отправьте «id» по пути, который «заяфка» нужно удалить")
    @Parameter(
            name = "id",
            description = "ID of the application to be deleted - Идентификатор удаляемой заяфка",
            required = true)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<?>> delete(
            @PathVariable Long id
    ) {
        return applicationService.delete(id);
    }

}
