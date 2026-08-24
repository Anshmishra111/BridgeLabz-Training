package com.fundoo.fundoonotes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fundoo.fundoonotes.entity.TypeOfNote;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * NoteResponse — UC5
 * Separates entities from serialization layers.
 * Annotated with @JsonProperty to preserve exact names in JSON.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteResponse {

    private Long          noteId;
    private String        title;
    private String        description;

    @JsonProperty("isPined")
    private boolean       isPined;

    @JsonProperty("isArchived")
    private boolean       isArchived;

    @JsonProperty("isDeleted")
    private boolean       isDeleted;

    private String        color;
    private TypeOfNote    typeOfNote;
    private String        imageUrl;
    private String        linkUrl;
    private Long          userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
