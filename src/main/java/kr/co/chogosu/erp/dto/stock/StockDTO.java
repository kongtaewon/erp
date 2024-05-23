package kr.co.chogosu.erp.dto.stock;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {

    @Column(name = "s_no")
    private Long sno;

    @Column(name = "FK_p_name")
    private String FKpname; // 제품 이름

    @Column(name = "s_amount")
    private int samount; // 전체 수량

    @Column(name = "kind")
    private String kind;
}
