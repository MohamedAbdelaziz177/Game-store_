package com._Abdelaziz26.WWE.DTOs.Event;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventDto {

    @NotBlank(message = "Event name is required")
    @Size(max = 100, message = "Event name must be less than 100 characters")
    private String name;



    @NotNull(message = "Date is required")
    @Future(message = "Event date must be in the future")
    private Date date;


    @NotNull(message = "Venue ID is required")
    private Long venueId;

    @NotNull(message = "At least one image is required")
    @Size(min = 1, max = 10, message = "You can upload 1-10 images")
    private List<MultipartFile> images;

}
