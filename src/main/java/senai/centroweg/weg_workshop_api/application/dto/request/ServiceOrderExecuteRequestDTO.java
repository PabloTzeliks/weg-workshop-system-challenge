package senai.centroweg.weg_workshop_api.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ServiceOrderExecuteRequestDTO (

    @JsonProperty("student_id") Integer studentId,

    @JsonProperty("used_materials") String usedMaterials,

    @JsonProperty("technical_conclusion") String technicalConclusion

) { }
