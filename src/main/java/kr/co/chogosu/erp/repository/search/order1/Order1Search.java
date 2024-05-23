package kr.co.chogosu.erp.repository.search.order1;

import kr.co.chogosu.erp.domain.Order1;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Order1Search {

    Page<Order1> search1(Pageable pageable);

    Page<Order1> searchAll(String type, String keyword, Pageable pageable);

}
