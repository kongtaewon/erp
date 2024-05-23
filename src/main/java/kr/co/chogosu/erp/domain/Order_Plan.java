package kr.co.chogosu.erp.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order_Plan extends BaseEntity{
    // @Id는 기본키로 지정, GenerationType.IDENTITY -> 데이터베이스에 위임 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "op_no")     // 자동증가
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
    private String optstate; // 조달계획

    //수정 부분 :  자재소요공정,자재소요일정,자재소요량,조달납기,조달계획
    public void changeOrder_Plan(String optuseprocess,String optusedate,int optusecount,String opdeliverydate){
        this.optuseprocess = optuseprocess;
        this.optusedate = optusedate;
        this.optusecount = optusecount;
        this.opdeliverydate = opdeliverydate;
    }

    public void change(String optstate){
        this.optstate = optstate;
    }


}
