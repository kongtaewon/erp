package kr.co.chogosu.erp.dto.contract;

import kr.co.chogosu.erp.domain.Company;
import kr.co.chogosu.erp.domain.Product;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContractDTO{

    private Long contractNo;

    private String contractCode;

    private String contractDate;

    private String contractState;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    private Company company;

    private Product product;

}
