package com.fundoo.fundoonotes.dto;

import com.fundoo.fundoonotes.entity.TypeOfNote;
import lombok.Data;

/**
 * NoteRequest — DTO for creating or updating a note's content.
 *
 * UC5 IMPORTANT: State flags (isPined, isArchived, isDeleted) are intentionally
 * excluded here. State transitions must go through the dedicated UC5 endpoints:
 *   POST /notes/pinUnpinNotes  — toggles pin
 *   POST /notes/archiveNotes   — toggles archive
 *   POST /notes/trashNotes     — soft-deletes
 *   POST /notes/deleteForeverNotes — hard-deletes
 *
 * Allowing direct flag mutation via addNotes/updateNotes would bypass
 * the state-machine validation enforced in NoteService.
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
