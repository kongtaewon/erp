package kr.co.chogosu.erp.dto.stock;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockHistoryDTO {

    @Column(name = "h_no")
    int hno;

    @Column(name = "FK_p_name")
    String FKpName;

    @Column(name = "s_in")
    int sin;

    @Column(name = "s_out")
    int sout;

    @Column(name = "s_return")
    int sreturn;

    @Column(name = "h_manager")
    String hManager;

    @Column(name = "h_requester")
    String hRequester;

    @Column(name = "h_request")
    int hRequest;

    @Column(name = "regdate")
    LocalDateTime hRegdate;

    @Column(name = "moddate")
    LocalDateTime hModdate;

    @Column(name = "p_code")
    String pcode;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "h_duedate")
    Date hduedate;

    @Column(name = "p_price")
    String pprice;
}
