package ge.shota.blog.conf;

import com.google.gson.Gson;
import ge.shota.blog.exception.GeneralException;
import ge.shota.blog.model.enums.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Slf4j
public class ExceptionConfig extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = GeneralException.class)
    public ResponseEntity<Object> handleTGException(GeneralException exc) {
        return buildResponseEntity(exc);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleTGException(Exception exc) {

        if (exc instanceof HttpServerErrorException){
            String errorBody = ((HttpServerErrorException) exc).getResponseBodyAsString();
            if (!errorBody.isEmpty()){
                if (!errorBody.startsWith("<!DOCTYPE")) {
                    GeneralException.BlogErrorException ex = new Gson().fromJson(errorBody, GeneralException.BlogErrorException.class);
                    return buildResponseEntity(new GeneralException(ex.getStatus(), ex.getErrorCode() != null ? ex.getErrorCode() : ErrorCode.INTERNAL_SERVER_ERROR, ex.getMessage(), exc));
                }
                return buildResponseEntity(new GeneralException(((HttpServerErrorException) exc).getStatusCode(), ErrorCode.INTERNAL_SERVER_ERROR, exc.getMessage(), exc));
            }
        }
        return buildResponseEntity(new GeneralException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.INTERNAL_SERVER_ERROR, exc.getMessage() != null ? exc.getMessage() : exc.toString(), exc));
    }


    private ResponseEntity<Object> buildResponseEntity(GeneralException ex) {
        log.error(ex.getErrorBody().getMessage(), ex);
        return new ResponseEntity<>(ex.getErrorBody(), ex.getStatus());
    }

}