package com.beatrix.omniPM.user.advice;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class APIError
{
    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    private LocalDateTime time;
    private HttpStatus status;
    private String message;
    private List<String> subError;

}
