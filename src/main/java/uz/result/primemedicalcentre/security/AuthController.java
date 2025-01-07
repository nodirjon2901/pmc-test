package uz.result.primemedicalcentre.security;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import uz.result.primemedicalcentre.domain.dto.ApiResponse;
import uz.result.primemedicalcentre.domain.dto.ApiResponseWithToken;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authorization - Авторизация")
public class AuthController {

    private final UserService userService;

    @Operation(summary = "Authorization (get token) - Авторизация (получение токена)")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseWithToken.class))
                    }
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400",
                    description = "If get 400 status Please read response 'message' - Если получить статус 400, прочитайте ответное сообщение 'message'",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponse.class)))
    })
    @PostMapping(value = "/login", consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public ResponseEntity<ApiResponse<Token>> login(
            @RequestPart("username") String username,
            @RequestPart("password") String password
    ) {
        return userService.login(username, password);
    }

}
