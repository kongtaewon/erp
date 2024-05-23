package kr.co.chogosu.erp.dto.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassificationDTO {

    private Long classificationNo;

    private String classificationName;

}
