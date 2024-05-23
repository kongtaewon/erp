package kr.co.chogosu.erp.repository.stockRepository;

import kr.co.chogosu.erp.domain.StockHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockHistoryRepository extends JpaRepository<StockHistory, Integer> {

    @Query(value = "select now()", nativeQuery = true)
    String getTime();


    List<StockHistory> findByFKpName(String FKpName);
}
