package kr.co.chogosu.erp.repository.search;

import kr.co.chogosu.erp.domain.Order_Plan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Order_PlanSearch {

    Page<Order_Plan> opsearch(Pageable pageable);

    Page<Order_Plan> opsearchAll(String type, String keyword, Pageable pageable);
}
