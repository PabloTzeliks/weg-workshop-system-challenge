package senai.centroweg.weg_workshop_api.application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import senai.centroweg.weg_workshop_api.domain.enums.UserType;

public record UserResponseDTO (

    @JsonProperty("id")
    Integer id,

    @JsonProperty("name")
    String name,

    @JsonProperty("user_type")
    UserType userType
) { }
