package com.example.project.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RegistAddressDTO {
    private Long rgaIdx;
    private Long userIdx;
    private String rgaRevname;
    private String rgaZipcode;
    private String rgaUserhp;
    private String rgaAddress1;
    private String rgaAddress2;
    private String rgaRevaddname;
}
