package kr.co.chogosu.erp.repository.order;

import kr.co.chogosu.erp.domain.Order1;
import kr.co.chogosu.erp.repository.search.order1.Order1Search;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Order1Repository extends JpaRepository<Order1, Long>, Order1Search {

    @Query(value = "select now()", nativeQuery = true)
    String getTime();
}
