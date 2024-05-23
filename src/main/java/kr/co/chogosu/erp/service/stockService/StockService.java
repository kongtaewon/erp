package kr.co.chogosu.erp.service.stockService;

import kr.co.chogosu.erp.domain.Order1;
import kr.co.chogosu.erp.domain.StockHistory;
import kr.co.chogosu.erp.dto.stock.PageRequestDTO;
import kr.co.chogosu.erp.dto.stock.PageResponseDTO;
import kr.co.chogosu.erp.dto.stock.StockDTO;
import kr.co.chogosu.erp.dto.stock.StockHistoryDTO;

import java.util.List;

public interface StockService {

    long register(StockDTO stockDTO);

//    StockDTO readOne(Long sno);

    void modify(StockDTO stockDTO);

    void remove(Long sno);

    void histrotyRegister(Order1 order1);
    PageResponseDTO<StockDTO> list(PageRequestDTO pageRequestDTO);

    List<StockHistory> detail(String fkpname);

    void store_enter(StockHistory stockHistoryDTO);

    void release_enter(StockHistory stockHistoryDTO);
}
