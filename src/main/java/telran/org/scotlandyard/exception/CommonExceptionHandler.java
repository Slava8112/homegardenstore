//package telran.org.scotlandyard.exception;
//
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//import telran.org.scotlandyard.entity.Category;
//import telran.org.scotlandyard.entity.Product;
//
//@ControllerAdvice
//public class CommonExceptionHandler {
//
//    @ExceptionHandler({ProductNotFoundException.class,
//            UserNotFoundException.class, CategoryNotFoundException.class, FavoriteNotFoundException.class})
//    public ResponseEntity handleNotFoundException(
//            Exception exception, WebRequest request) {
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
//    }
//
//    @ExceptionHandler(NoUniqueUserEmailException.class)
//    public ResponseEntity handleNoUniqueEmailException(Exception exception) {
//        return ResponseEntity.badRequest().body(exception.getMessage());
//    }
//}
package telran.org.scotlandyard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(NoUniqueUserEmailException.class)
    public ResponseEntity<String> handleNoUniqueUserEmailException(NoUniqueUserEmailException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler({ProductNotFoundException.class, UserNotFoundException.class, CategoryNotFoundException.class, FavoriteNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + ex.getMessage());
    }
}
