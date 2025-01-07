package uz.result.primemedicalcentre.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uz.result.primemedicalcentre.domain.dto.ApiResponse;
import uz.result.primemedicalcentre.exception.AlreadyExistsException;
import uz.result.primemedicalcentre.exception.IllegalPhotoTypeException;
import uz.result.primemedicalcentre.exception.LanguageNotSupportException;
import uz.result.primemedicalcentre.exception.NotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleNotFoundException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(e.getMessage(), null));
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ApiResponse<?>> handleAlreadyExistsException(AlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(e.getMessage(), null));
    }

    @ExceptionHandler(LanguageNotSupportException.class)
    public ResponseEntity<ApiResponse<?>> handleLanguageNotSupportException(LanguageNotSupportException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>(e.getMessage(), null));
    }

    @ExceptionHandler(IllegalPhotoTypeException.class)
    public ResponseEntity<ApiResponse<?>> handleIllegalPhotoTypeException(IllegalPhotoTypeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>("Illegal photo: " + e.getMessage(), null));
    }

}
