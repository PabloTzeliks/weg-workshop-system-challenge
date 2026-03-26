package senai.centroweg.weg_workshop_api.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import senai.centroweg.weg_workshop_api.domain.model.User;

import java.util.List;

public record ClassResponseDTO (

    @JsonProperty("id")
    Integer id,

    @JsonProperty("name")
    String name,

    @JsonProperty("teacher")
    User teacher,

    @JsonProperty("students")
    List<User> students
) { }
