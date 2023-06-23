package ge.shota.blog.exception;

import ge.shota.blog.model.enums.ErrorCode;
import org.springframework.http.HttpStatus;


public class GeneralException extends RuntimeException {

    private final HttpStatus status;
    private final ErrorCode errorCode;


    public GeneralException(ErrorCode code, String error) {
        this(HttpStatus.INTERNAL_SERVER_ERROR, code, error, null);
    }
    public GeneralException(HttpStatus status, ErrorCode code) {
        this(status, code, "Internal Server Error", null);
    }
    public GeneralException(HttpStatus status, ErrorCode code, String message) {
        this(status, code, message, null);
    }
    public GeneralException(HttpStatus status, ErrorCode code, String message, Throwable ex) {
        super(message, ex);
        this.status = status;
        this.errorCode = code;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
    public HttpStatus getStatus() {
        return status;
    }

    public BlogErrorException getErrorBody() {
        return new BlogErrorException(this);
    }

    public static class BlogErrorException {

        private final HttpStatus status;

        private final ErrorCode errorCode;

        private final int errorCodeRaw;

        private final String message;

        public BlogErrorException(GeneralException exc) {
            this.status = exc.getStatus();
            this.errorCode = exc.getErrorCode();
            this.message = exc.getMessage();
            this.errorCodeRaw = exc.getErrorCode().getErrorCode();
        }

        public ErrorCode getErrorCode() {
            return errorCode;
        }

        public HttpStatus getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

        public int getErrorCodeRaw() {
            return errorCodeRaw;
        }
    }

    public static class InvalidParams extends GeneralException {

        public InvalidParams() {
            super(HttpStatus.BAD_REQUEST, ErrorCode.INVALID_PARAMS);
        }
        public InvalidParams(String message) {
            super(HttpStatus.BAD_REQUEST, ErrorCode.INVALID_PARAMS, message);
        }
        public InvalidParams(ErrorCode code, String message) {
            super(HttpStatus.BAD_REQUEST, code, message);
        }

    }



    public static class ArticleNotFound extends GeneralException {
        public ArticleNotFound(Long id) {
            super(HttpStatus.NOT_FOUND, ErrorCode.ENTITY_NOT_FOUND, String.format("Article with id: %s not found", id));
        }
    }


    public static class ProfileNotFound extends GeneralException {
        public ProfileNotFound(Long profileId) {
            super(HttpStatus.NOT_FOUND, ErrorCode.ENTITY_NOT_FOUND, String.format("Profile With id: %s not found", profileId));
        }

        public ProfileNotFound() {
            super(HttpStatus.NOT_FOUND, ErrorCode.ENTITY_NOT_FOUND, String.format("Profile not found"));
        }
    }

    public static class ProfileAlreadyFound extends GeneralException {

        public ProfileAlreadyFound(Long profileId) {
            super(HttpStatus.CONFLICT, ErrorCode.UNIQUE_KEY_DUPLICATE, String.format("profile with customer number: %s already exists", profileId));
        }

        public ProfileAlreadyFound(String fieldName, String fieldValue) {
            super(HttpStatus.CONFLICT, ErrorCode.UNIQUE_KEY_DUPLICATE, String.format("profile with customer %s: %s already exists", fieldName, fieldValue));
        }

    }




    public static class FileSize extends GeneralException {
        public FileSize() {
            super(HttpStatus.CONFLICT, ErrorCode.FILE_SIZE, "File Size limit is 5mb");
        }

    }



}
