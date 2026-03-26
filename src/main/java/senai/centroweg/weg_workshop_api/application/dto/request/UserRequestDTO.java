package senai.centroweg.weg_workshop_api.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import senai.centroweg.weg_workshop_api.domain.enums.UserType;

public record UserRequestDTO (

    @JsonProperty("name")
    String name,

    @JsonProperty("userType")
    UserType userType
) { }
