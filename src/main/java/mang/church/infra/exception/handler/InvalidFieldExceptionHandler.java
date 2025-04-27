package mang.church.infra.exception.handler;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import mang.church.infra.exception.model.InvalidFieldErrorDTO;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@AllArgsConstructor
@Hidden
public class InvalidFieldExceptionHandler {

    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<InvalidFieldErrorDTO>> handleArgumentNotValidException(MethodArgumentNotValidException e){
        var bindingResult = new ArrayList<InvalidFieldErrorDTO>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            var message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            bindingResult.add(buildErrorMessageDTO(error, message));
        });
        return new ResponseEntity<>(bindingResult, HttpStatus.BAD_REQUEST);
    }

    private InvalidFieldErrorDTO buildErrorMessageDTO(FieldError error, String message) {
        return InvalidFieldErrorDTO.builder()
                .message(message)
                .field(error.getField())
                .build();
    }
}
