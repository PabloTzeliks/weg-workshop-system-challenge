package senai.centroweg.weg_workshop_api.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ClassRequestDTO (
    @JsonProperty("name")
    @NotBlank(message = "Name is mandatory")
    String name,

    @JsonProperty("teacher_id")
    @NotNull(message = "Teacher ID is mandatory")
    Integer teacherId,

    @JsonProperty("student_ids")
    @NotNull(message = "Student IDs are mandatory")
    List<Integer> studentIds
) { }
