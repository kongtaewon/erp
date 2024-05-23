package kr.co.chogosu.erp.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Inspection extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inspection_no")
    private Long inspectionNo;

    @Column(name = "inspection_state")
    private String inspectionState;

    @Column(name = "inspection_date")
    private String inspectionDate;

    @Column(name = "inspection_manager")
    private String inspectionManager;

    @Column(name = "FK_inspection_order_no")
    private long inspectionOrderNo;

}
