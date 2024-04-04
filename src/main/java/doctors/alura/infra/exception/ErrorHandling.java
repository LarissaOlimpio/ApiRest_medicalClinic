package doctors.alura.infra.exception;

import doctors.alura.domain.CustomValidationException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandling {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity error404Handling(){
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity error400Handling(MethodArgumentNotValidException ex){
        var errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(DataErrorsValidation::new));

    }
    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity handleCustomValidationException(CustomValidationException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    private record DataErrorsValidation(String field, String message){

        public DataErrorsValidation(FieldError error){
            this(error.getField(), error.getDefaultMessage());

        }
    }

}
