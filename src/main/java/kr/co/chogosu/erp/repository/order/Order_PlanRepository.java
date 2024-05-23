package kr.co.chogosu.erp.repository.order;

import kr.co.chogosu.erp.domain.Order_Plan;
import kr.co.chogosu.erp.repository.search.Order_PlanSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Order_PlanRepository extends JpaRepository<Order_Plan, Long>, Order_PlanSearch {

    //select now() -> 현재 시간 가져오는 쿼리문, nativeQuery ->  JPQL을 사용할 수 없을 때, JPA는 SQL을 직접 사용할 수 있는 기능
    @Query(value = "select now()", nativeQuery = true)
    String getTime();

    /*@Query(value = "update Order_Plan set optuseprocess,optusedate,optusecount,opdeliverydate,optstate where opno")*/


}
