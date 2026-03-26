package senai.centroweg.weg_workshop_api.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record ServiceOrderApproveRequestDTO (

    @JsonProperty("teacher_id")
    @NotNull(message = "Teacher ID is mandatory")
    Integer teacherId
) { }
