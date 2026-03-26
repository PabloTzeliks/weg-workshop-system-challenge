package senai.centroweg.weg_workshop_api.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ServiceOrderApproveRequestDTO(

    @JsonProperty("teacher_id") Integer teacherId
) { }
