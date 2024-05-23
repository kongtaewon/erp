package kr.co.chogosu.erp.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order1 extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_no")  // 글번호
    private Long orderNo;

    @Column(name = "order_code")    // 발주코드
    private String orderCode;

    @Column(name = "order_date")    //발주 일자
    private String orderDate;

    @Column(name = "order_progress")    // 진척도
    private String orderProgress;

    @Column(name = "order_count")   //발주 수량
    private String orderCount;

    @Column(name = "order_price")   //발주 단가
    private String orderPrice;

    @Column(name = "order_state", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT '처리전'")   //발주 상태
    private String orderState;

    @Column(name = "order_manager") //발주 담당자
    private String orderManager;

    @ManyToOne
    @JoinColumn(name = "FK_p_name")
    private Product product;

    public void change(String orderProgress, String orderCount, String orderPrice, String orderState, String orderManager) {
        this.orderProgress = orderProgress;
        this.orderCount = orderCount;
        this.orderPrice = orderPrice;
        this.orderState = orderState;
        this.orderManager = orderManager;
    }

}
