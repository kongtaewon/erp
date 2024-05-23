package kr.co.chogosu.erp.repository.productRepository;

import kr.co.chogosu.erp.domain.Product;
import kr.co.chogosu.erp.repository.search.product.ProductSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String>, ProductSearch {
    @Query(value = "select now()", nativeQuery = true)
    String getTime();

    List<Product> findByPcodeContaining(String pcode);

//    @Modifying
//    @Query(value = "UPDATE erp e set e.discontinued = 1 where e.p_name = :p_name", nativeQuery = true)
//    void discontinued(@Param("p_name")String p_name) throws Exception;

}
