package kr.co.chogosu.erp.repository.search.company;

import kr.co.chogosu.erp.domain.Company;
import kr.co.chogosu.erp.domain.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompanySearch {

    Page<Company> searchAll(String types, String keyword, Pageable pageable);

}
