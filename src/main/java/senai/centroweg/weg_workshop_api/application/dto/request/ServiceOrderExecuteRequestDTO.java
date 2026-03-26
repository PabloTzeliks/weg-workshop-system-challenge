package senai.centroweg.weg_workshop_api.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record ServiceOrderExecuteRequestDTO (

    @JsonProperty("student_id")
    @NotNull(message = "Student ID is mandatory")
    Integer studentId,

    @JsonProperty("used_materials")
    @NotNull(message = "Used materials are mandatory")
    String usedMaterials,

    @JsonProperty("technical_conclusion")
    @NotNull(message = "Technical conclusion is mandatory")
    String technicalConclusion
) { }
