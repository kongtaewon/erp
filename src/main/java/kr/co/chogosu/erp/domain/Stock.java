package kr.co.chogosu.erp.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Stock extends BaseEntity{

    // 자재 보관 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "s_no")
    private Long sno;

    @Column(name = "FK_p_name")
    private String FKpname; // 제품 이름

    @Column(name = "s_amount")
    private long samount; // 전체 수량

    @Column(name = "kind")
    private String kind;

    public void change(Long sno, String FKpname, long samount) {
        this.sno = sno;
        this.FKpname = FKpname;
        this.samount = samount;
    }

    public void change1(long samount) {
        this.samount = samount;
    }
}
