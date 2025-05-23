package com._Abdelaziz26.Game.DTOs.Game;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateGameDto {

    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    @Size(min = 2, max = 300)
    private String description;

    private MultipartFile image;

    @NotNull
    private Double price;

    @NotNull
    private Long genreId;

    @NotEmpty
    private List<Long> platformIds;

}
