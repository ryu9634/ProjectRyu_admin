package com.example.project.model.network.response;

import com.example.project.model.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserApiResponse {

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
