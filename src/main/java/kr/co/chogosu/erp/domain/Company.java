package kr.co.chogosu.erp.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "company")
public class Company extends BaseEntity{

    @Id
    @Column(name = "company_name")
    private String companyName;

    @Column(name = "c_fax")
    private String cFax;

    @Column(name = "c_phonnumber")
    private String cPhonNumber;

    @Column(name = "c_cnumber")
    private String cCNumber;

    @Column(name = "c_address")
    private String cAddress;

}
