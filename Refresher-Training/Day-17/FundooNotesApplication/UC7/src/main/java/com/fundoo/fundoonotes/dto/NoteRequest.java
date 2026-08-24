package com.fundoo.fundoonotes.dto;

import com.fundoo.fundoonotes.entity.TypeOfNote;
import lombok.Data;

/**
 * NoteRequest — DTO for creating or updating a note's content.
 *
 * UC5/UC6: State flags (isPined, isArchived, isDeleted) are excluded.
 * State transitions go through dedicated endpoints only.
 */
@Data
public class NoteRequest {

    private String     title;
    private String     description;
    private String     color;
    private TypeOfNote typeOfNote;
    private String     imageUrl;
    private String     linkUrl;
}
