package posctn.posctn_api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseDto<T> {

    private int statusCode;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    public ApiResponseDto(int status, String message, T data) {
        this.statusCode = status;
        this.message = message;
        this.data = data;

        this.timestamp = LocalDateTime.now();
    }
}