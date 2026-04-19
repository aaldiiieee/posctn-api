package posctn.posctn_api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class ErrorResponseDto {
    private String errorCode;
    private String message;
    private int status;
    private Instant timestamp;
    private String path;
}
