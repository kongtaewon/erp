package kr.co.chogosu.erp.dto.board;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardImageDTO {

    private String uuid;

    private String fileName;

    private int ord;
}
