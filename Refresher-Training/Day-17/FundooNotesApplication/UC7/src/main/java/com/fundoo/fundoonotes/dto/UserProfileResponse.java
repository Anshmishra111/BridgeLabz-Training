package com.fundoo.fundoonotes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
