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
import uz.result.primemedicalcentre.domain.dto.doctor.*;
import uz.result.primemedicalcentre.domain.dto.newness.NewnessUpdateDTO;
import uz.result.primemedicalcentre.domain.dto.serviceOfDoctor.ServiceOfDoctorCreateDTO;
import uz.result.primemedicalcentre.domain.dto.serviceOfDoctor.ServiceOfDoctorResponseDTO;
import uz.result.primemedicalcentre.domain.dto.serviceOfDoctor.ServiceOfDoctorUpdate;
import uz.result.primemedicalcentre.service.DoctorService;

import java.util.List;

@RestController
@RequestMapping("/v1/doctor")
@RequiredArgsConstructor
@Tag(name = "Doctor - Доктор")
public class DoctorController {

    private final DoctorService doctorService;

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "If successfully created you get status '201' - Если успешно создан, вы получите статус '201'",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWithDoctorResponseDTO.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "If get 400 status Please read response 'message' - Если получить статус 400, прочитайте ответное сообщение 'message'",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class))
            )
    })
    @Operation(summary = "Add or create new 'Doctor' - Добавить или создать новую 'Доктор'")
    @PostMapping(value = "/create", consumes = {"multipart/form-data"})
    public ResponseEntity<ApiResponse<DoctorResponseDTO>> create(
            @Parameter(
                    name = "json",
                    description = "Insert text data as JSON format - Вставить текстовые данные в формате JSON",
                    required = true,
                    schema = @Schema(implementation = DoctorCreateDTO.class, format = "json", type = "string")
            )
            @RequestPart(value = "json") String json,

            @Parameter(
                    name = "photo",
                    description = "Select picture of 'Doctor' .jpg or .png or .svg file format - Выберите изображение «Доктор» .jpg или .png или .svg Формат файла",
                    required = true,
                    content = @Content(mediaType = "multipart/form-data",
                            schema = @Schema(type = "string", format = "binary"))
            )
            @RequestPart(value = "photo") MultipartFile photo
    ) {
        return doctorService.create(json, photo);
    }

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200-a",
                    description = "a) Example scheme for all language - Пример схемы для всех языков",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWithDoctorResponseDTO.class)
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200-b",
                    description = "b) Example scheme for one language - Пример схемы для одного языка",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWithDoctorMapper.class)
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "If get 400 Please READ RESPONSE MESSAGE! - Если получите 400, пожалуйста, ПРОЧИТАЙТЕ ОТВЕТНОЕ СООБЩЕНИЕ!"
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
            description = "Slug of the doctor to be retrieved - Slug доктор, которую необходимо получить",
            required = true)
    @GetMapping("/get/{slug}")
    public ResponseEntity<ApiResponse<?>> findBySlug(
            @RequestHeader(value = "Accept-Language", required = false) String lang,
            @PathVariable String slug
    ) {
        return doctorService.findBySlug(slug, lang);
    }

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200-a",
                    description = "a) Example scheme for all language - Пример схемы для всех языков",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWithDoctorResponseDTO.class)
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200-b",
                    description = "b) Example scheme for one language - Пример схемы для одного языка",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseWithDoctorMapper.class)
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "If get 400 Please READ RESPONSE MESSAGE! - Если получите 400, пожалуйста, ПРОЧИТАЙТЕ ОТВЕТНОЕ СООБЩЕНИЕ!"
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
            description = "ID of the doctor to be retrieved - Идентификатор доктор, которую необходимо получить",
            required = true)
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ApiResponse<?>> findById(
            @RequestHeader(value = "Accept-Language", required = false) String lang,
            @PathVariable Long id
    ) {
        return doctorService.findById(id, lang);
    }

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200-a",
                    description = "a) List of doctor for all language - Список доктор для всех языков",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = DoctorResponseDTO.class))
                    )),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200-b",
                    description = "b) List of doctor for one language - Список доктор для одного языка",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = DoctorMapper.class))
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
    @Operation(summary = "Get list of doctors - Получить список доктор")
    @Parameter(
            name = "Accept-Language",
            description = "Select language code one of : 'uz','ru' - Выберите один из кодов языка: 'uz','ru'",
            required = true,
            examples = {
                    @ExampleObject(
                            name = "uz",
                            description = "List of doctors in uzbek - Список врачей на узбекском языке",
                            summary = "uz",
                            value = "uz"
                    ),
                    @ExampleObject(
                            name = "ru",
                            description = "List of doctors in russian - Список врачей на русском языке",
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
                    If you need a limited number of doctors, enter a limit. If you need all services, do not enter a limit \s
                    Если вам нужно ограниченное количество врачей, введите лимит. Если вам нужны все услуги, не вводите лимит
                    """,
            required = false,
            schema = @Schema(type = "integer")
    )
    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse<?>> findAll(
            @RequestHeader(value = "Accept-Language", required = false) String lang,
            @RequestParam(value = "limit", required = false) Integer limit
    ) {
        return doctorService.findAll(lang, limit);
    }

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "You get 200 status code when data successfully edited - Вы получите код статуса 200, когда данные будут успешно отредактированы.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = NewnessUpdateDTO.class)
                    )),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "If get 400 Please READ RESPONSE MESSAGE! - Если получите 400, пожалуйста, ПРОЧИТАЙТЕ ОТВЕТНОЕ СООБЩЕНИЕ!",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class)
                    ))
    })
    @Operation(summary = "Update and edit newness - Обновить и отредактировать «новости» ")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = SwaggerConstants.DOCTOR_UPDATE_DESCRIPTION,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = DoctorUpdateDTO.class),
                    examples = {
                            @ExampleObject(
                                    name = "Edit All fields - Редактировать все поля",
                                    description = "Edit All fields at the same time - Редактировать все поля одновременно",
                                    value = SwaggerConstants.DOCTOR_FULL_FORM
                            ),
                            @ExampleObject(
                                    name = "Edit custom field - Изменить пользовательское поле",
                                    description = "Send field which you want - Отправьте поле, которое вы хотите",
                                    value = SwaggerConstants.DOCTOR_CUSTOM_FIELD
                            ),
                            @ExampleObject(
                                    name = "Add Specialization - Добавить Специализации",
                                    description = SwaggerConstants.ADD_SPECIALIZATION_DESC,
                                    value = SwaggerConstants.ADD_SPECIALIZATION_JSON
                            ),
                            @ExampleObject(
                                    name = "Delete Specialization - Удалить Специализации",
                                    description = SwaggerConstants.DELETE_SPECIALIZATION_DESC,
                                    value = SwaggerConstants.DELETE_SPECIALIZATION_JSON
                            ),
                            @ExampleObject(
                                    name = "Add Education - Добавить Образование",
                                    description = SwaggerConstants.ADD_EDUCATION_DESC,
                                    value = SwaggerConstants.ADD_EDUCATION_JSON
                            ),
                            @ExampleObject(
                                    name = "Delete Education  - Удалить Образование",
                                    description = SwaggerConstants.DELETE_EDUCATION_DESC,
                                    value = SwaggerConstants.DELETE_EDUCATION_JSON
                            )
                    }
            )
    )
    @PutMapping("/update")
    public ResponseEntity<ApiResponse<DoctorResponseDTO>> update(
            @RequestBody DoctorUpdateDTO updateDTO
    ) {
        return doctorService.update(updateDTO);
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
            description = "ID of the doctor to be deleted - Идентификатор удаляемой доктор",
            required = true)
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<?>> delete(
            @PathVariable Long id
    ) {
        return doctorService.delete(id);
    }

    @PostMapping("/education/create/{doctorId}")
    public ResponseEntity<ApiResponse<DoctorResponseDTO>> addEducation(
            @RequestBody EducationCreateDTO createDTO,
            @PathVariable Long doctorId
    ) {
        return doctorService.addEducation(doctorId, createDTO);
    }

    @PutMapping("/education/update")
    public ResponseEntity<ApiResponse<DoctorResponseDTO>> updateEducation(
            @RequestBody EducationResponseDTO updateDTO
    ) {
        return doctorService.updateEducation(updateDTO);
    }

    @DeleteMapping("/education/delete/{educationId}")
    public ResponseEntity<ApiResponse<?>> deleteEducation(
            @PathVariable Long educationId
    ) {
        return doctorService.deleteEducation(educationId);
    }

    @PostMapping("/service/create/{doctorId}")
    public ResponseEntity<ApiResponse<DoctorResponseDTO>> addService(
            @RequestBody ServiceOfDoctorCreateDTO createDTO,
            @PathVariable Long doctorId
    ) {
        return doctorService.addService(doctorId, createDTO);
    }

    @GetMapping("/service/get-all/{doctorId}")
    public ResponseEntity<ApiResponse<List<ServiceOfDoctorResponseDTO>>> serviceList(
            @PathVariable Long doctorId
    ) {
        return doctorService.findAllServiceByDoctorId(doctorId);
    }

    @PutMapping("/service/update")
    public ResponseEntity<ApiResponse<DoctorResponseDTO>> updateService(
            @RequestBody ServiceOfDoctorUpdate updateDTO
    ) {
        return doctorService.updateService(updateDTO);
    }

    @DeleteMapping("/service/delete/{serviceId}")
    public ResponseEntity<ApiResponse<?>> deleteService(
            @PathVariable Long serviceId
    ) {
        return doctorService.deleteService(serviceId);
    }

    @PostMapping("/specialization/create/{doctorId}")
    public ResponseEntity<ApiResponse<DoctorResponseDTO>> addSpecialization(
            @RequestBody SpecializationCreateDTO createDTO,
            @PathVariable Long doctorId
    ) {
        return doctorService.addSpecialization(doctorId, createDTO);
    }

    @PutMapping("/specialization/update")
    public ResponseEntity<ApiResponse<DoctorResponseDTO>> updateSpecialization(
            @RequestBody SpecializationResponseDTO updateDTO
    ) {
        return doctorService.updateSpecialization(updateDTO);
    }

    @DeleteMapping("/specialization/delete/{specializationId}")
    public ResponseEntity<ApiResponse<?>> deleteSpecialization(
            @PathVariable Long specializationId
    ) {
        return doctorService.deleteSpecialization(specializationId);
    }

}
