package senai.centroweg.weg_workshop_api.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ServiceOrderResponseDTO(

        @JsonProperty("id")
        @NotNull(message = "ID is mandatory")
        Integer id,

        @JsonProperty("equipment")
        @NotBlank(message = "ID is mandatory")
        String equipment,

        @JsonProperty("reported_defect")
        @NotBlank(message = "Reported Defect is mandatory")
        String reportedDefect,

        @JsonProperty("responsible_teacher")
        @NotBlank(message = "Responsible Teacher is mandatory")
        String responsibleTeacher,

        @JsonProperty("students_id")
        @NotNull(message = "Students IDs is mandatory")
        List<Integer> studentsId
) { }
