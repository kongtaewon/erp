package kr.co.chogosu.erp.dto.order;

import groovyjarjarpicocli.CommandLine;
import kr.co.chogosu.erp.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order1DTO {

    private Long orderNo;

    private String orderCode;

    private String orderDate;

    private String orderProgress;

    private String orderCount;

    private String orderPrice;

    private String orderState;

    private String orderManager;

    private Product product;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

}
