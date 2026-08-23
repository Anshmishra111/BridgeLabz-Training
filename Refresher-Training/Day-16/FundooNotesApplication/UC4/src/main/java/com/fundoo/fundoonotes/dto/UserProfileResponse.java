package com.fundoo.fundoonotes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserProfileResponse — returned by GET /user/profile
 *
 * Contains the authenticated user's public-facing data.
 * userId is included so the client can cache it — but the server
 * never reads userId from the client to determine "who am I".
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponse {

    private Long    userId;
    private String  email;
    private String  firstName;
    private String  lastName;
    private Boolean isVerified;
}
