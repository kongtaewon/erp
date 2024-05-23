package kr.co.chogosu.erp.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private String pname;

    private String pcode;

    private String pcontent;

    private byte discontinued;

    private String pprice;

    private LocalDateTime regDate, modDate;


}
