package kr.co.chogosu.erp.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StockHistory {

    @Id
    @Column(name = "h_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hno;

    @Column(name = "FK_p_name")
    private String FKpName;

    @Column(name = "s_return")
    private int sreturn;

    @Column(name = "s_in")
    private int sin;

    @Column(name = "s_out")
    private int sout;

    @Column(name = "h_manager")
    private String hManager;

    @Column(name = "h_requester")
    private String hRequester;

    @Column(name = "h_request")
    private int hRequest;

    @Column(name = "p_code")
    private String pcode;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "h_duedate")
    private Date hduedate;

    @Column(name = "p_price")
    private String pprice;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "regdate")
    private Date hregdate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "moddate")
    private Date hmoddate;

    public StockHistory(String FKpName,int sin, String hManager, int hRequest, Date hduedate, String pprice) {
        this.FKpName = FKpName;
        this.sin = sin;
        this.hManager = hManager;
        this.hRequest = hRequest;
        this.hduedate = hduedate;
        this.pprice = pprice;
    }
}
