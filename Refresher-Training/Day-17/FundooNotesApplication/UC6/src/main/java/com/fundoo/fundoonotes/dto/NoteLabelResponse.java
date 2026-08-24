package com.fundoo.fundoonotes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * NoteLabelResponse — UC6
 *
 * API doc shape: { "label": "string", "isDeleted": true, "id": "string", "userId": "string" }
 *
 * @JsonProperty("isDeleted") ensures the field serializes as "isDeleted" in JSON
 * (Lombok with boolean strips one 'is' prefix by default).
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteLabelResponse {

    private Long   id;
    private String label;

    @JsonProperty("isDeleted")
    private boolean isDeleted;

    private Long   userId;
}
