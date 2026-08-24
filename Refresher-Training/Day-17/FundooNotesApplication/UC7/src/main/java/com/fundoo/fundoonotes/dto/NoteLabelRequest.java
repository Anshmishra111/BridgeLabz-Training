package com.fundoo.fundoonotes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * NoteLabelRequest — UC6
 * Payload for creating or updating a label.
 *
 * API doc shape: { "label": "string" }
 * userId is derived from the JWT — never accepted from the client.
 */
@Data
public class NoteLabelRequest {

    @NotBlank(message = "Label text is required")
    @Size(min = 1, max = 50, message = "Label must be 1–50 characters")
    private String label;
}
