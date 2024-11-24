package umc.study.apiPayload.exception;

import lombok.Getter;

@Getter
public class PageValidationException extends RuntimeException {
    private final String message;

    public PageValidationException(String message) {
        super(message);
        this.message = message;
    }
}
