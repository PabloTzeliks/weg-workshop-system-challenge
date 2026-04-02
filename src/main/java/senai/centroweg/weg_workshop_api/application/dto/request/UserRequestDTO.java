package senai.centroweg.weg_workshop_api.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import senai.centroweg.weg_workshop_api.domain.enums.UserType;

public record UserRequestDTO(

        @JsonProperty("name")
        @NotBlank(message = "Name is mandatory")
        String name,

        @JsonProperty("user_type")
        @NotNull(message = "User type is mandatory")
        UserType userType
) { }
