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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.result.primemedicalcentre.documentation.SwaggerConstants;
import uz.result.primemedicalcentre.domain.dto.ApiResponse;
import uz.result.primemedicalcentre.domain.dto.newness.*;
import uz.result.primemedicalcentre.service.NewnessService;

import java.util.List;

@RestController
@RequestMapping("/v1/newness")
@RequiredArgsConstructor
@Tag(name = "Newness - Новости")
public class NewnessController {

    private final NewnessService newnessService;

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "If successfully created you get status '201' - Если успешно создан, вы получите статус '201'",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWithNewnessResponseDTO.class)
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "if get 400 status Please Read Response message - Если получить статус 400, прочитайте ответное сообщение 'message'",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class)
                    )
            )
    })
    @Operation(summary = "This API used for create new - Этот API используется для создания новости")
    @PostMapping(value = "/create", consumes = {"multipart/form-data"})
    public ResponseEntity<ApiResponse<NewnessResponseDTO>> create(
            @Parameter(
                    name = "json",
                    description = "New details in JSON format (excluding photo) - Подробности новости в формате JSON (кроме фото)",
                    required = true,
                    schema = @Schema(implementation = NewnessCreateDTO.class, format = "json", type = "string")
            )
            @RequestPart(value = "json") String json,
            MultipartHttpServletRequest photos
    ) {
        return newnessService.create(json, photos);
    }

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200-a",
                    description = "a) Example schema for all language - Пример схемы для всех языков",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWithNewnessResponseDTO.class)
                    )),

            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200-b",
                    description = "b) Example schema for one language - Пример схемы для одного языка",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWithNewnessMapper.class)
                    )),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "If get 400 Please READ RESPONSE MESSAGE! - Если получите 400, пожалуйста, ПРОЧИТАЙТЕ ОТВЕТНОЕ СООБЩЕНИЕ!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class)
                    ))
    })
    @Operation(summary = "Get full data in All/one Language - Получите полные данные по всем или одному языку")
    @Parameter(
            name = "Accept-Language",
            description = "Select language code one of : 'uz','ru','en' - Выберите один из кодов языка: 'uz','ru','en'",
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
            name = "Slug",
            description = "Slug of the newness to be retrieved - Slug новости, которую необходимо получить",
            required = true)
    @GetMapping("/get/{slug}")
    public ResponseEntity<ApiResponse<?>> findBySlug(
            @RequestHeader(value = "Accept-Language", required = false) String lang,
            @PathVariable String slug
    ) {
        return newnessService.findBySlug(slug, lang);
    }

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200-a",
                    description = "a) Example schema for all language - Пример схемы для всех языков",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWithNewnessResponseDTO.class)
                    )),

            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200-b",
                    description = "b) Example schema for one language - Пример схемы для одного языка",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWithNewnessMapper.class)
                    )),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "If get 400 Please READ RESPONSE MESSAGE! - Если получите 400, пожалуйста, ПРОЧИТАЙТЕ ОТВЕТНОЕ СООБЩЕНИЕ!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class)
                    ))
    })
    @Operation(summary = "Get full data in All/one Language - Получите полные данные по всем или одному языку")
    @Parameter(
            name = "Accept-Language",
            description = "Select language code one of : 'uz','ru','en' - Выберите один из кодов языка: 'uz','ru','en'",
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
            name = "ID",
            description = "ID of the newness to be retrieved - ID новости, которую необходимо получить",
            required = true)
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ApiResponse<?>> findById(
            @RequestHeader(value = "Accept-Language", required = false) String lang,
            @PathVariable Long id
    ) {
        return newnessService.findById(id, lang);
    }

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200-a",
                    description = "a) List of newness for all language - Список новости для всех языков",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = NewnessResponseDTO.class))
                    )),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200-b",
                    description = "b) List of newness for one language - Список новости для одного языка",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = NewnessMapper.class))
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
    @Operation(summary = "Get list of newness - Получить список новости")
    @Parameter(
            name = "Accept-Language",
            description = "Select language code one of : 'uz','ru' - Выберите один из кодов языка: 'uz','ru'",
            required = true,
            examples = {
                    @ExampleObject(
                            name = "uz",
                            description = "List of newness in uzbek - Список новости на узбекском языке",
                            summary = "uz",
                            value = "uz"
                    ),
                    @ExampleObject(
                            name = "ru",
                            description = "List of newness in russian - Список новости на русском языке",
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
            name = "page",
            description = """
                    You can specify the page and size to indicate how many news items should appear on each page. If you want all the news items to be displayed on one page, you don't need to provide anything. However, you either need to specify both page and size or neither. - \s
                    Вы можете указать страницу и размер, чтобы задать, сколько новостей должно отображаться на каждой странице. Если все новости должны быть на одной странице, ничего указывать не нужно. Однако вам нужно либо указать и страницу, и размер, либо не указывать их вовсе.
                    """,
            required = false,
            schema = @Schema(type = "integer")
    )
    @Parameter(
            name = "size",
            description = """
                    You can specify the page and size to indicate how many news items should appear on each page. If you want all the news items to be displayed on one page, you don't need to provide anything. However, you either need to specify both page and size or neither. - \s
                    Вы можете указать страницу и размер, чтобы задать, сколько новостей должно отображаться на каждой странице. Если все новости должны быть на одной странице, ничего указывать не нужно. Однако вам нужно либо указать и страницу, и размер, либо не указывать их вовсе.
                    """,
            required = false,
            schema = @Schema(type = "integer")
    )
    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse<?>> findAll(
            @RequestHeader(value = "Accept-Language", required = false) String lang,
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "page", required = false) Integer page
    ) {
        return newnessService.findAll(lang, size, page);
    }

    @Operation(summary = "This API used to update an existing newness - Этот API используется для обновления существующего новости.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "You get 200 status code when data successfully edited - Вы получите код статуса 200, когда данные будут успешно отредактированы.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWithNewnessResponseDTO.class)
                    )),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "If get 400 Please READ RESPONSE MESSAGE! - Если получите 400, пожалуйста, ПРОЧИТАЙТЕ ОТВЕТНОЕ СООБЩЕНИЕ!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class)
                    ))
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = SwaggerConstants.NEWNESS_UPDATE_DESCRIPTION,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = NewnessUpdateDTO.class),
                    examples = {
                            @ExampleObject(
                                    name = "Edit All fields - Редактировать все поля",
                                    description = "Edit All fields at the same time - Редактировать все поля одновременно",
                                    value = SwaggerConstants.NEWNESS_FULL_FORM
                            ),
                            @ExampleObject(
                                    name = "Edit custom field - Изменить пользовательское поле",
                                    description = "Send field which you want - Отправьте поле, которое вы хотите",
                                    value = SwaggerConstants.CUSTOM_FIELD_NEWNESS
                            ),
                            @ExampleObject(
                                    name = "Add option - Добавить option",
                                    description = SwaggerConstants.ADD_OPTION_DESC,
                                    value = SwaggerConstants.ADD_OPTION_JSON
                            ),
                            @ExampleObject(
                                    name = "Delete option - Удалить option",
                                    description = SwaggerConstants.DELETE_OPTION_DESC,
                                    value = SwaggerConstants.DELETE_OPTION_JSON
                            ),
                            @ExampleObject(
                                    name = "Add photo to exists option - Добавить фотографию к существующей опции",
                                    description = SwaggerConstants.ADD_PHOTO_TO_OPTION_DESC,
                                    value = SwaggerConstants.ADD_PHOTO_TO_OPTION_JSON
                            )
                    }
            )
    )
    @PutMapping(value = "/update", consumes = {"application/json"})
    public ResponseEntity<ApiResponse<NewnessResponseDTO>> update(
            @RequestBody NewnessUpdateDTO updateDTO
    ) {
        return newnessService.update(updateDTO);
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
    @Operation(summary = "Send 'id' in path which 'newness' need delete - Отправьте «id» по пути, который «новости» нужно удалить")
    @Parameter(
            name = "id",
            description = "ID of the newness to be deleted - Идентификатор удаляемой новости",
            required = true)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<?>> delete(
            @PathVariable Long id
    ) {
        return newnessService.delete(id);
    }

    @PostMapping("/block/add/{newnessId}")
    public ResponseEntity<ApiResponse<NewnessResponseDTO>> addBlock(
            @PathVariable Long newnessId,
            @RequestPart(value = "json") String json,
            @RequestPart(value = "photo",required = false) MultipartFile photo
    ) {
        return newnessService.addOption(newnessId, json, photo);
    }

    @DeleteMapping("/block/delete/{id}")
    public ResponseEntity<ApiResponse<?>> deleteBlock(
            @PathVariable Long id
    ) {
        return newnessService.deleteNewnessOption(id);
    }

    @PutMapping("/block/update/photo/{id}")
    public ResponseEntity<ApiResponse<NewOptionResponseDTO>> updateBlockPhoto(
            @PathVariable Long id,
            @RequestPart(value = "photo") MultipartFile photo
    ) {
        return newnessService.updateOptionPhoto(id, photo);
    }

}
