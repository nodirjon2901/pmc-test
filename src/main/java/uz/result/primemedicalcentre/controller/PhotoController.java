package uz.result.primemedicalcentre.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.result.primemedicalcentre.domain.dto.ApiResponse;
import uz.result.primemedicalcentre.domain.dto.PhotoDTO;
import uz.result.primemedicalcentre.domain.entity.Photo;
import uz.result.primemedicalcentre.service.PhotoService;

import java.util.List;

@RestController
@RequestMapping("/v1/photo")
@RequiredArgsConstructor
@Tag(name = "Photo - Фото")
public class PhotoController {

    private final PhotoService photoService;


    @Operation(summary = "Upload photo to server - Загрузить фото на сервер")
    @PostMapping(value = "/create", consumes = {"multipart/form-data"})
    public ResponseEntity<ApiResponse<List<Photo>>> upload(
            @Parameter(
                    description = "Select picture on format .jpg or .png or .svg - Выберите изображение в формате .jpg или .png или .svg",
                    content = @Content(mediaType = "multipart/form-data",
                            schema = @Schema(
                                    type = "string",
                                    format = "binary",
                                    example = "image.png"
                            ))
            )
            @RequestPart(value = "photos") List<MultipartFile> photos
    ) {
        return photoService.upload(photos);
    }

    @GetMapping("/{name}")
    @Operation(summary = "Show a photo - Показать фото")
    @Hidden
    public ResponseEntity<byte[]> getPhoto(
            @PathVariable(name = "name") String name
    ) {
        return photoService.findByName(name);
    }

    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Photo updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PhotoDTO.class))
            )
    })
    @Operation(summary = "Replace photo to another - Заменить фото на другое")
    @Parameters({
            @Parameter(name = "id", description = "Show 'id' in url path for which photo is updated - Показывать «id» в URL-адресе, для которого обновлена фотография", required = true)
    })
    @PutMapping(value = "/update/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<ApiResponse<PhotoDTO>> updatePhoto(
            @PathVariable(name = "id") Long id,
            @Parameter(
                    content = @Content(mediaType = "multipart/form-data",
                            schema = @Schema(
                                    type = "string",
                                    format = "binary",
                                    example = "image.png"
                            ))
            )
            @RequestPart(value = "photo", required = false) MultipartFile photo
    ) {
        return photoService.update(id, photo);
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
    @Operation(summary = "Send 'id' in path which 'photo' need delete - Отправьте «id» по пути, который «photo» нужно удалить")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<?>> deletePhoto(
            @Parameter(description = "Show 'id' which photo must deleted - Показать «id», какую фотографию необходимо удалить")
            @PathVariable Long id
    ) {
        return photoService.delete(id);
    }

}
