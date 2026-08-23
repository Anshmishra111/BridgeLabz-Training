package com.fundoo.fundoonotes.dto;

import com.fundoo.fundoonotes.entity.TypeOfNote;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * NoteResponse — UC4
 * Separates entities from serialization layers.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteResponse {

    private Long          noteId;
    private String        title;
    private String        description;
    private boolean       isPined;
    private boolean       isArchived;
    private boolean       isDeleted;
    private String        color;
    private TypeOfNote    typeOfNote;
    private String        imageUrl;
    private String        linkUrl;
    private Long          userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
