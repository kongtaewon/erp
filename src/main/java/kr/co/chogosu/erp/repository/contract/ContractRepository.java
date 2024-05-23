package kr.co.chogosu.erp.repository.contract;

import kr.co.chogosu.erp.domain.Contract;
import kr.co.chogosu.erp.repository.search.ContractSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContractRepository extends JpaRepository<Contract, Long>, ContractSearch {

    @Query("select c, co, p" +
            " from Contract c join Company co on c.company.companyName = co.companyName" +
            " join Product p on c.product.pname = p.pname")
    Page<Contract> queryContractByList(Pageable pageable);


}
