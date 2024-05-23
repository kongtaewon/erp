package kr.co.chogosu.erp.dto.order;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InspectionDTO {

    private Long inspectionNo;

    private String inspectionState;

    private String inspectionDate;

    private String inspectionManager;

    private long inspectionOrderNo;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

}
