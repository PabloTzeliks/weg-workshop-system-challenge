package senai.centroweg.weg_workshop_api.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record ClassRequestDTO (

    @JsonProperty("name") String name,

    @JsonProperty("teacher_id") Integer teacherId,

    @JsonProperty("student_ids") List<Integer> studentIds
) { }
