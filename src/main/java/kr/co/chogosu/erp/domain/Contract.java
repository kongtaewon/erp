package kr.co.chogosu.erp.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "contract")
public class Contract extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contract_no")
    private Long contractNo;

    @Column(name = "contract_Code")
    private String contractCode;

    @Column(name = "contract_Date")
    private String contractDate;

    @Column(name = "contract_State")
    private String contractState;

    @ManyToOne
    @JoinColumn(name = "p_name")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "company_name")
    private Company company;

    public void change(String contractState, Product product, Company company) {
        this.contractState = contractState;
        this.product = product;
        this.company = company;
    }

}
