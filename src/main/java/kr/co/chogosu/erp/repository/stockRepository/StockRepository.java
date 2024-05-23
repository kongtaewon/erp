package kr.co.chogosu.erp.repository.stockRepository;

import kr.co.chogosu.erp.domain.Product;
import kr.co.chogosu.erp.domain.Stock;
import kr.co.chogosu.erp.repository.search.stock.StockSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long>, StockSearch {
@Query (value = "select now()", nativeQuery = true)
    String getTime();

Stock findByFKpname(String FKpname);
}
