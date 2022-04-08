package com.example.project.model.network.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserApiRequest {
    private Long userIdx;
    private String userUserid;
    private String userUserpw;
    private String userName;
    private String userHp;
    private String userEmail;
    private LocalDateTime userRegdate;
    private String userSns;
    private String userZipcode;
    private String userAddress1;
    private String userAddress2;
}
