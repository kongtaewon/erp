package kr.co.chogosu.erp.dto.order_plan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order_PlanDTO {

    @Column(name = "op_no" ) // 자동번호 증가
    private Long opno;

    @Column(name = "op_code") //  품목코드
    private String opcode;

    @Column(name = "op_name") //  품목이름
    private String opname;

    @Column(name = "op_tuseprocess")  // 자재소요공정
    private String optuseprocess;

    @Column(name ="op_tusedate")
    private String optusedate; // 자재소요일정

    @Column(name ="op_tusecount" )
    private int optusecount; // 자재소요량

    @Column(name ="op_deliverydate" )
    private String opdeliverydate; // 조달납기

    @Column(name = "op_tstate")
    private String optstate; // 조달계획 상태

    private LocalDateTime regDate;      // 등록일

    private LocalDateTime modDate;      // 수정일


}
