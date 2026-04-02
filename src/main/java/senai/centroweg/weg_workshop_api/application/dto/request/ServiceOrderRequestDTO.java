package senai.centroweg.weg_workshop_api.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ServiceOrderRequestDTO(

        @JsonProperty("teacher_id")
        @NotNull(message = "Teacher ID is mandatory")
        Integer teacherId,

        @JsonProperty("equipment")
        @NotNull(message = "Equipment is mandatory")
        String equipment,

        @JsonProperty("reported_defect")
        @NotNull(message = "Reported defect is mandatory")
        String reportedDefect,

        @JsonProperty("studentIds")
        @NotNull(message = "Student IDs are mandatory")
        List<Integer> studentIds
) { }
