package uz.result.primemedicalcentre.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import uz.result.primemedicalcentre.domain.dto.aboutUs.banner.*;
import uz.result.primemedicalcentre.domain.dto.aboutUs.laboratory.*;
import uz.result.primemedicalcentre.domain.dto.aboutUs.service.*;
import uz.result.primemedicalcentre.domain.dto.service.ApiResponseWithServiceResponseDTO;
import uz.result.primemedicalcentre.domain.dto.service.ServiceUpdateDTO;

@RestController
@RequestMapping("/v1/about-us")
@RequiredArgsConstructor
@Tag(name = "About Us - О Нас")
public class AboutUsController {

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "If successfully created you get status '201' - Если успешно создан, вы получите статус '201'",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWithAboutUsBannerResponseDTO.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "If you get status Please read response 'message' - Если получить статус 400, прочитайте ответное сообщение 'message'",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class))
            )
    })
    @Operation(summary = "Create new AboutUsBanner - Создать новую 'AboutUsBanner'")
    @PostMapping(value = "/banner/create", consumes = {"multipart/form-data"})
    public ResponseEntity<ApiResponse<AboutUsBannerResponseDTO>> createBanner(
            @Parameter(
                    name = "json",
                    description = "Insert text data as JSON format - Вставить текстовые данные в формате JSON",
                    required = true,
                    schema = @Schema(implementation = AboutUsBannerCreateDTO.class, format = "json", type = "string")
            )
            @RequestPart(value = "json") String json,
            @Parameter(
                    name = "photo",
                    description = "Select picture of 'aboutUsBanner' .jpg or .png or .svg file format - Выберите изображение «aboutUsBanner» .jpg или .png или .svg Формат файла",
                    required = true,
                    content = @Content(mediaType = "multipart/form-data",
                            schema = @Schema(type = "string", format = "binary"))
            )
            @RequestPart(value = "photo") MultipartFile photo
    ) {
        return ResponseEntity.ok(new ApiResponse<>());
    }

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200-a",
                    description = "a) Example scheme for all language - Пример схемы для всех языков",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWithAboutUsBannerResponseDTO.class)
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200-b",
                    description = "b) Example scheme for one language - Пример схемы для одного языка",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWithAboutUsBannerMapper.class)
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
    @GetMapping("/banner/get")
    public ResponseEntity<ApiResponse<?>> findBanner(
            @RequestHeader(value = "Accept-Language", required = false) String lang
    ) {
        return ResponseEntity.ok(new ApiResponse<>());
    }

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "You get 200 status code when data successfully edited - Вы получите код статуса 200, когда данные будут успешно отредактированы.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWithAboutUsBannerResponseDTO.class)
                    )),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "If get 400 Please READ RESPONSE MESSAGE! - Если получите 400, пожалуйста, ПРОЧИТАЙТЕ ОТВЕТНОЕ СООБЩЕНИЕ!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class)
                    ))
    })
    @Operation(summary = "Update and edit banner - Обновить и отредактировать «banner» ")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = SwaggerConstants.ABOUT_US_BANNER_UPDATE_DESCRIPTION,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AboutUsBannerUpdateDTO.class),
                    examples = {
                            @ExampleObject(
                                    name = "Edit All fields - Редактировать все поля",
                                    description = "Edit All fields at the same time - Редактировать все поля одновременно",
                                    value = SwaggerConstants.ABOUT_US_BANNER_FULL_FORM
                            ),
                            @ExampleObject(
                                    name = "Edit custom field - Изменить пользовательское поле",
                                    description = "Send field which you want - Отправьте поле, которое вы хотите",
                                    value = SwaggerConstants.ABOUT_US_BANNER_CUSTOM_FIELD
                            )
                    }
            )
    )
    @PutMapping(value = "/banner/update", consumes = {"application/json"})
    public ResponseEntity<ApiResponse<AboutUsBannerResponseDTO>> updateBanner(
            @RequestBody AboutUsBannerUpdateDTO updateDTO
    ) {
        return ResponseEntity.ok(new ApiResponse<>());
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
    @Operation(summary = "This API used for delete - Этот API используется для удаления.")
    @DeleteMapping("/banner/delete")
    public ResponseEntity<ApiResponse<?>> deleteBanner() {
        return ResponseEntity.ok(new ApiResponse<>());
    }

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "If successfully created you get status '201' - Если успешно создан, вы получите статус '201'",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWithAboutUsServiceResponseDTO.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "If you get status Please read response 'message' - Если получить статус 400, прочитайте ответное сообщение 'message'",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class))
            )
    })
    @Operation(summary = "Create new AboutUsService - Создать новую 'AboutUsService'")
    @PostMapping(value = "/service/create", consumes = {"multipart/form-data"})
    public ResponseEntity<ApiResponse<AboutUsServiceResponseDTO>> createService(
            @Parameter(
                    name = "json",
                    description = "Insert text data as JSON format - Вставить текстовые данные в формате JSON",
                    required = true,
                    schema = @Schema(implementation = AboutUsServiceCreateDTO.class, format = "json", type = "string")
            )
            @RequestPart(value = "json") String json,
            @Parameter(
                    name = "photo",
                    description = "Select picture of 'aboutUsService' .jpg or .png or .svg file format - Выберите изображение «aboutUsService» .jpg или .png или .svg Формат файла",
                    required = true,
                    content = @Content(mediaType = "multipart/form-data",
                            schema = @Schema(type = "string", format = "binary"))
            )
            @RequestPart(value = "photo") MultipartFile photo
    ) {
        return ResponseEntity.ok(new ApiResponse<>());
    }

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200-a",
                    description = "a) Example scheme for all language - Пример схемы для всех языков",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWithAboutUsServiceResponseDTO.class)
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200-b",
                    description = "b) Example scheme for one language - Пример схемы для одного языка",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWithAboutUsServiceMapper.class)
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
    @GetMapping("/service/get")
    public ResponseEntity<ApiResponse<?>> findService(
            @RequestHeader(value = "Accept-Language") String lang
    ) {
        return ResponseEntity.ok(new ApiResponse<>());
    }

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "You get 200 status code when data successfully edited - Вы получите код статуса 200, когда данные будут успешно отредактированы.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWithAboutUsServiceResponseDTO.class)
                    )),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "If get 400 Please READ RESPONSE MESSAGE! - Если получите 400, пожалуйста, ПРОЧИТАЙТЕ ОТВЕТНОЕ СООБЩЕНИЕ!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class)
                    ))
    })
    @Operation(summary = "Update and edit «AboutUsService» - Обновить и отредактировать «AboutUsService»")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = SwaggerConstants.ABOUT_US_SERVICE_UPDATE_DESCRIPTION,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AboutUsBannerUpdateDTO.class),
                    examples = {
                            @ExampleObject(
                                    name = "Edit All fields - Редактировать все поля",
                                    description = "Edit All fields at the same time - Редактировать все поля одновременно",
                                    value = SwaggerConstants.ABOUT_US_SERVICE_FULL_FORM
                            ),
                            @ExampleObject(
                                    name = "Edit custom field - Изменить пользовательское поле",
                                    description = "Send field which you want - Отправьте поле, которое вы хотите",
                                    value = SwaggerConstants.ABOUT_US_SERVICE_CUSTOM_FIELD
                            )
                    }
            )
    )
    @PutMapping(value = "/service/update", consumes = {"application/json"})
    public ResponseEntity<ApiResponse<AboutUsServiceResponseDTO>> updateService(
            @RequestBody AboutUsServiceUpdateDTO updateDTO
    ) {
        return ResponseEntity.ok(new ApiResponse<>());
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
    @Operation(summary = "This API used for delete - Этот API используется для удаления.")
    @DeleteMapping("/service/delete")
    public ResponseEntity<ApiResponse<?>> deleteService() {
        return ResponseEntity.ok(new ApiResponse<>());
    }

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "If successfully created you get status '201' - Если успешно создан, вы получите статус '201'",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWithAboutUsLaboratoryResponseDTO.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "If you get status Please read response 'message' - Если получить статус 400, прочитайте ответное сообщение 'message'",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class))
            )
    })
    @Operation(summary = "Create new AboutUsLaboratory - Создать новую 'AboutUsLaboratory'")
    @PostMapping(value = "/laboratory/create", consumes = {"multipart/form-data"})
    public ResponseEntity<ApiResponse<AboutUsLaboratoryResponseDTO>> createLaboratory(
            @Parameter(
                    name = "json",
                    description = "Insert text data as JSON format - Вставить текстовые данные в формате JSON",
                    required = true,
                    schema = @Schema(implementation = AboutUsLaboratoryCreateDTO.class, format = "json", type = "string")
            )
            @RequestPart(value = "json") String json,
            @Parameter(
                    name = "photo",
                    description = "Select picture of 'aboutUsLaboratory' .jpg or .png or .svg file format - Выберите изображение «aboutUsLaboratory» .jpg или .png или .svg Формат файла",
                    required = true,
                    content = @Content(mediaType = "multipart/form-data",
                            schema = @Schema(type = "string", format = "binary"))
            )
            @RequestPart(value = "photo") MultipartFile photo
    ) {
        return ResponseEntity.ok(new ApiResponse<>());
    }

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200-a",
                    description = "a) Example scheme for all language - Пример схемы для всех языков",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWithAboutUsLaboratoryResponseDTO.class)
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200-b",
                    description = "b) Example scheme for one language - Пример схемы для одного языка",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWithAboutUsLaboratoryMapper.class)
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
    @GetMapping("/laboratory/get")
    public ResponseEntity<ApiResponse<?>> findLaboratory(
            @RequestHeader(value = "Accept-Language", required = false) String lang
    ) {
        return ResponseEntity.ok(new ApiResponse<>());
    }

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "You get 200 status code when data successfully edited - Вы получите код статуса 200, когда данные будут успешно отредактированы.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWithAboutUsLaboratoryResponseDTO.class)
                    )),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "If get 400 Please READ RESPONSE MESSAGE! - Если получите 400, пожалуйста, ПРОЧИТАЙТЕ ОТВЕТНОЕ СООБЩЕНИЕ!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class)
                    ))
    })
    @Operation(summary = "Update and edit «AboutUsService» - Обновить и отредактировать «AboutUsService»")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = SwaggerConstants.ABOUT_US_LABORATORY_UPDATE_DESCRIPTION,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AboutUsLaboratoryUpdateDTO.class),
                    examples = {
                            @ExampleObject(
                                    name = "Edit All fields - Редактировать все поля",
                                    description = "Edit All fields at the same time - Редактировать все поля одновременно",
                                    value = SwaggerConstants.ABOUT_US_LABORATORY_FULL_FORM
                            ),
                            @ExampleObject(
                                    name = "Edit custom field - Изменить пользовательское поле",
                                    description = "Send field which you want - Отправьте поле, которое вы хотите",
                                    value = SwaggerConstants.ABOUT_US_LABORATORY_CUSTOM_FIELD
                            )
                    }
            )
    )
    @PutMapping("/laboratory/update")
    public ResponseEntity<ApiResponse<AboutUsLaboratoryResponseDTO>> updateLaboratory(
            @RequestBody AboutUsLaboratoryUpdateDTO updateDTO
    ) {
        return ResponseEntity.ok(new ApiResponse<>());
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
    @Operation(summary = "This API used for delete - Этот API используется для удаления.")
    @DeleteMapping("/laboratory/delete")
    public ResponseEntity<ApiResponse<?>> deleteLaboratory() {
        return ResponseEntity.ok(new ApiResponse<>());
    }

}
