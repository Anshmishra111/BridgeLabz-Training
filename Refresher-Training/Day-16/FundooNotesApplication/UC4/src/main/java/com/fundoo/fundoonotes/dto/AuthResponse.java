package com.fundoo.fundoonotes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AuthResponse — returned by both /user/userSignUp (201) and /user/login (200).
 *
 * Fields match the API doc's user model:
 *   { "token": "...", "userId": 1, "email": "...", "firstName": "...", "lastName": "..." }
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private String token;
    private Long   userId;
    private String email;
    private String firstName;
    private String lastName;
    private String message;
}
