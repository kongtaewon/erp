package kr.co.chogosu.erp.repository.search.stock;

import kr.co.chogosu.erp.domain.Stock;
import kr.co.chogosu.erp.domain.StockHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StockSearch {

    Page<Stock> searchAll(String types, Pageable pageable);

    Page<StockHistory> historyAll(Pageable pageable);
}
