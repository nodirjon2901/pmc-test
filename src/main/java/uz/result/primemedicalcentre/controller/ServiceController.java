package uz.result.primemedicalcentre.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.result.primemedicalcentre.documentation.SwaggerConstants;
import uz.result.primemedicalcentre.domain.dto.ApiResponse;
import uz.result.primemedicalcentre.domain.dto.aboutUs.service.AboutUsServiceResponseDTO;
import uz.result.primemedicalcentre.domain.dto.service.*;
import uz.result.primemedicalcentre.service.ServiceOfService;

@RestController
@RequestMapping("/v1/service")
@RequiredArgsConstructor
@Tag(name = "Service - Услуга")
public class ServiceController {

    private final ServiceOfService service;

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "If successfully created you get status '201' - Если успешно создан, вы получите статус '201'",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWithServiceResponseDTO.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "If get 400 status Please read response 'message' - Если получить статус 400, прочитайте ответное сообщение 'message'",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class))
            )
    })
    @Operation(summary = "Add or create new 'Service' - Добавить или создать новую 'сервис'")
    @PostMapping(value = "/create", consumes = {"multipart/form-data"})
    public ResponseEntity<ApiResponse<ServiceResponseDTO>> create(
            @Parameter(
                    name = "json",
                    description = "Insert text data as JSON format - Вставить текстовые данные в формате JSON",
                    required = true,
                    schema = @Schema(implementation = ServiceCreateDTO.class, format = "json", type = "string")
            )
            @RequestPart(value = "json") String json,

            @Parameter(
                    name = "photo",
                    description = "Select picture of 'Service' .jpg or .png or .svg file format - Выберите изображение «Сервис» .jpg или .png или .svg Формат файла",
                    required = true,
                    content = @Content(mediaType = "multipart/form-data",
                            schema = @Schema(type = "string", format = "binary"))
            )
            @RequestPart(value = "photo") MultipartFile photo
    ) {
        return service.create(json, photo);
    }

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200-a",
                    description = "a) Example scheme for all language - Пример схемы для всех языков",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWithServiceResponseDTO.class)
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200-b",
                    description = "b) Example scheme for one language - Пример схемы для одного языка",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWithServiceMapper.class)
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "If get 400 Please READ RESPONSE MESSAGE! - Если получите 400, пожалуйста, ПРОЧИТАЙТЕ ОТВЕТНОЕ СООБЩЕНИЕ!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class)
                    )
            )
    })
    @Operation(summary = "Get full date in all/one language - Получите полные данные по всем или одному языку")
    @Parameter(
            name = "Accept-Language",
            description = "Select language code one of: 'uz', 'ru' - Выберите один из кодов языка: 'uz', 'ru'",
            examples = {
                    @ExampleObject(
                            name = "uz",
                            description = "Data in uzbek - Данные на узбекском языке",
                            summary = "uz",
                            value = "uz"
                    ),
                    @ExampleObject(
                            name = "ru",
                            description = "Data in russian - Данные на русском языке",
                            summary = "ru",
                            value = "ru"
                    ),
                    @ExampleObject(
                            name = "-",
                            description = "Data in all language - Данные на всех языках",
                            summary = "-",
                            value = "-"
                    )
            }
    )
    @Parameter(
            name = "id",
            description = "ID of the service to be retrieved - Идентификатор услуги, которую необходимо получить",
            required = true)
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ApiResponse<?>> findById(
            @RequestHeader(value = "Accept-Language", required = false) String lang,
            @PathVariable Long id
    ) {
        return service.findById(id, lang);
    }

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200-a",
                    description = "a) Example scheme for all language - Пример схемы для всех языков",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWithServiceResponseDTO.class)
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200-b",
                    description = "b) Example scheme for one language - Пример схемы для одного языка",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWithServiceMapper.class)
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "If get 400 Please READ RESPONSE MESSAGE! - Если получите 400, пожалуйста, ПРОЧИТАЙТЕ ОТВЕТНОЕ СООБЩЕНИЕ!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class)
                    )
            )
    })
    @Operation(summary = "Get full date in all/one language - Получите полные данные по всем или одному языку")
    @Parameter(
            name = "Accept-Language",
            description = "Select language code one of: 'uz', 'ru' - Выберите один из кодов языка: 'uz', 'ru'",
            examples = {
                    @ExampleObject(
                            name = "uz",
                            description = "Data in uzbek - Данные на узбекском языке",
                            summary = "uz",
                            value = "uz"
                    ),
                    @ExampleObject(
                            name = "ru",
                            description = "Data in russian - Данные на русском языке",
                            summary = "ru",
                            value = "ru"
                    ),
                    @ExampleObject(
                            name = "-",
                            description = "Data in all language - Данные на всех языках",
                            summary = "-",
                            value = "-"
                    )
            }
    )
    @Parameter(
            name = "slug",
            description = "SLUG of the service to be retrieved - SLUG услуги, которую необходимо получить",
            required = true)
    @GetMapping("/get/{slug}")
    public ResponseEntity<ApiResponse<?>> findBySlug(
            @RequestHeader(value = "Accept-Language", required = false) String lang,
            @PathVariable String slug
    ) {
        return service.findBySlug(slug, lang);
    }

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200-a",
                    description = "a) List of services for all language - Список услуг для всех языков",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ServiceResponseDTO.class))
                    )),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200-b",
                    description = "b) List of service for one language - Список услуг для одного языка",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ServiceMapper.class))
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "If get 400 Please READ RESPONSE MESSAGE! - Если получите 400, пожалуйста, ПРОЧИТАЙТЕ ОТВЕТНОЕ СООБЩЕНИЕ!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class)
                    ))
    })
    @Operation(summary = "Get list of services - Получить список услуг")
    @Parameter(
            name = "Accept-Language",
            description = "Select language code one of : 'uz','ru' - Выберите один из кодов языка: 'uz','ru'",
            required = true,
            examples = {
                    @ExampleObject(
                            name = "uz",
                            description = "List of services in uzbek - Список услуг на узбекском языке",
                            summary = "uz",
                            value = "uz"
                    ),
                    @ExampleObject(
                            name = "ru",
                            description = "List of services in russian - Список услуг на русском языке",
                            summary = "ru",
                            value = "ru"
                    ),
                    @ExampleObject(
                            name = "-",
                            description = "Data in all language - Данные на всех языках",
                            summary = "-",
                            value = "-"
                    )
            }
    )
    @Parameter(
            name = "limit",
            description = """
                    If you need a limited number of services, enter a limit. If you need all services, do not enter a limit \s
                    Если вам нужно ограниченное количество сервисов, введите лимит. Если вам нужны все сервисы, не вводите лимит
                    """,
            required = false,
            schema = @Schema(type = "integer")
    )
    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse<?>> findAll(
            @RequestHeader(value = "Accept-Language", required = false) String lang,
            @RequestParam(value = "limit", required = false) Integer limit
    ) {
        return service.findAll(lang, limit);
    }

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "You get 200 status code when data successfully edited - Вы получите код статуса 200, когда данные будут успешно отредактированы.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWithServiceResponseDTO.class)
                    )),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "If get 400 Please READ RESPONSE MESSAGE! - Если получите 400, пожалуйста, ПРОЧИТАЙТЕ ОТВЕТНОЕ СООБЩЕНИЕ!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class)
                    ))
    })
    @Operation(summary = "Update and edit service - Обновить и отредактировать «сервис» ")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = SwaggerConstants.SERVICE_UPDATE_DESCRIPTION,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ServiceUpdateDTO.class),
                    examples = {
                            @ExampleObject(
                                    name = "Edit All fields - Редактировать все поля",
                                    description = "Edit All fields at the same time - Редактировать все поля одновременно",
                                    value = SwaggerConstants.SERVICE_FULL_FORM
                            ),
                            @ExampleObject(
                                    name = "Edit custom field - Изменить пользовательское поле",
                                    description = "Send field which you want - Отправьте поле, которое вы хотите",
                                    value = SwaggerConstants.SERVICE_CUSTOM_FIELD
                            )
                    }
            )
    )
    @PutMapping(value = "/update", consumes = {"application/json"})
    public ResponseEntity<ApiResponse<ServiceResponseDTO>> update(
            @RequestBody ServiceUpdateDTO updateDTO
    ) {
        return service.update(updateDTO);
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
    @Operation(summary = "Send 'id' in path which 'service' need delete - Отправьте «id» по пути, который «service» нужно удалить")
    @Parameter(
            name = "id",
            description = "ID of the service to be deleted - Идентификатор удаляемой услуги",
            required = true)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<?>> delete(
            @PathVariable Long id
    ) {
        return service.delete(id);
    }

}
