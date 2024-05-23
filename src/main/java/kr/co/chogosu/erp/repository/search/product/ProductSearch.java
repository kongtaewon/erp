package kr.co.chogosu.erp.repository.search.product;

import kr.co.chogosu.erp.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductSearch {
    Page<Product> search(Pageable pageable);
    Page<Product> searchAll(String types, String keyword, Pageable pageable);
}
