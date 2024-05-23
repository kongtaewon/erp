package kr.co.chogosu.erp.dto.company;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.*;


import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {

    private String companyName;

    private String cFax;

    private String cPhonNumber;

    private String cCNumber;

    private String cAddress;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

}
