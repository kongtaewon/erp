package kr.co.chogosu.erp.repository.search;

import kr.co.chogosu.erp.domain.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContractSearch {

    Page<Contract> searchAll(String types, String keyword, Pageable pageable);

}
