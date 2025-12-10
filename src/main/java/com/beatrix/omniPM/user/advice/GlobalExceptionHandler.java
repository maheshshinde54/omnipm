package com.beatrix.omniPM.user.advice;

import com.beatrix.omniPM.user.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse<?>> resourceNOtFoundException(ResourceNotFoundException exception)
    {
        APIError apiError = APIError.builder()
                                    .status(HttpStatus.NOT_FOUND)
                                    .message(exception.getMessage())
                                    .build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<?>> handleInternalServerError(Exception exception)
    {
        APIError apierror = APIError.builder()
                                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                                    .message(exception.getMessage())
                                    .build();
        return buildErrorResponseEntity(apierror);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<?>> handleInputValidationError(MethodArgumentNotValidException methodArgumentNotValidException)
    {
        List<String> listOfError = methodArgumentNotValidException.getBindingResult()
                                                                  .getAllErrors()
                                                                  .stream()
                                                                  .map(error -> error.getDefaultMessage())
                                                                  .toList();
        APIError inputValidationErrors = APIError.builder()
                                                 .status(HttpStatus.BAD_REQUEST)
                                                 .message("Input validation errors")
                                                 .subError(listOfError)
                                                 .build();
        return  buildErrorResponseEntity(inputValidationErrors);
    }

    private ResponseEntity<APIResponse<?>> buildErrorResponseEntity(APIError apiError)
    {
        return new ResponseEntity<>(new APIResponse<>(apiError), apiError.getStatus());
    }
}
