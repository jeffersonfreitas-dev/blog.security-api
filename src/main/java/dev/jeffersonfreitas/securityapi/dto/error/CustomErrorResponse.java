package dev.jeffersonfreitas.securityapi.dto.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.ErrorResponse;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomErrorResponse {

    private int code;
    private String message;
    private LocalDateTime timestamp;

    public static CustomErrorResponse create(int value, String message) {
        return new CustomErrorResponse(value, message, LocalDateTime.now());
    }
}
