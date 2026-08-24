package com.fundoo.fundoonotes.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fundoo.fundoonotes.entity.TypeOfNote;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * NoteResponse — UC6
 *
 * UC6 addition: includes the list of active (non-deleted) labels attached to the note.
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

    /**
     * UC6: Active labels attached to this note.
     * Soft-deleted labels are filtered out in NoteService.mapToResponse().
     */
    @Builder.Default
    private List<NoteLabelResponse> labels = new ArrayList<>();
}
