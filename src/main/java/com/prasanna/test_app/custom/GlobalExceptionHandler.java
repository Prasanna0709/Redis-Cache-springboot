package com.prasanna.test_app.custom;

import com.prasanna.test_app.dto.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorResponseDto>  handleStudentNotFoundException(StudentNotFoundException exception, HttpServletRequest request){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponseDto.builder()
                            .status(HttpStatus.NOT_FOUND.value())
                            .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                            .message(exception.getMessage())
                            .path(request.getRequestURI())
                            .timeStamp(LocalDateTime.now().toString())
                    .build());
    }

    @ExceptionHandler(IllegalArgumentsException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalArgumentsException(IllegalArgumentsException exception,HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseDto.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .message(exception.getMessage())
                        .path(request.getRequestURI())
                        .timeStamp(LocalDateTime.now().toString())
                .build());
    }

}
