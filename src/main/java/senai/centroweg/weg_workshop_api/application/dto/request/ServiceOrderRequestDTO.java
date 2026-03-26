package senai.centroweg.weg_workshop_api.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record ServiceOrderRequestDTO(

    @JsonProperty("teacher_id")
    Integer teacherId,

    @JsonProperty("equipment")
    String equipment,

    @JsonProperty("reported_defect")
    String reportedDefect,

    @JsonProperty("studentIds")
    List<Integer> studentIds
) {}
