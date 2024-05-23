package kr.co.chogosu.erp.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product extends BaseEntity{

    @Id
    @Column(name = "p_name")
    private String pname;

    @Column(name = "p_code")
    private String pcode;

    @Column(name = "p_content")
    private String pcontent;

    @Column(name = "discontinued")
    private byte discontinued;

    @Column(name = "p_price")
    private String pprice;

    @ManyToOne
    @JoinColumn(name = "classification_no")
    private Classification classification;

    public void change(String pcontent, String pprice) {
        this.pcontent = pcontent;
        this.pprice = pprice;
    }

}
