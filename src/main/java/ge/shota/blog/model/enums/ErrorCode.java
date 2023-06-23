package ge.shota.blog.model.enums;

public enum ErrorCode {

    GENERAL_EXCEPTION(9001),
    INVALID_PARAMS(9002),
    ENTITY_NOT_FOUND(9003),
    UNIQUE_KEY_DUPLICATE(9004),
    INVALID_SEARCH_TEXT_RANK(9005),
    INTERNAL_SERVER_ERROR(0),
    NOT_FOUND(404),
    FILE_SIZE(9006);

    private final int errorCode;

    ErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
