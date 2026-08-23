package com.fundoo.fundoonotes.dto;

import com.fundoo.fundoonotes.entity.TypeOfNote;
import lombok.Data;

/**
 * NoteRequest — DTO for creating or updating notes.
 *
 * Scopes fields matching the real shape from the API doc.
 */
@Data
public class NoteRequest {

    private String     title;
    private String     description;
    private boolean    isPined;
    private boolean    isArchived;
    private boolean    isDeleted;
    private String     color;
    private TypeOfNote typeOfNote;
    private String     imageUrl;
    private String     linkUrl;
}
