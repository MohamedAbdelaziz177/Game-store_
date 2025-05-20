package com._Abdelaziz26.WWE.DTOs.Event;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class UpdateEventDto {

    @Size(max = 100, message = "Event name must be less than 100 characters")
    private String name;

    @Future(message = "Event date must be in the future")
    private Date date;

    private Long venueId;

    @Size(max = 10, message = "Maximum 10 images allowed")
    private List<MultipartFile> newImages;
}
