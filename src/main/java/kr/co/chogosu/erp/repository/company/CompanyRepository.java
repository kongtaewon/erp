package kr.co.chogosu.erp.repository.company;

import kr.co.chogosu.erp.domain.Company;
import kr.co.chogosu.erp.repository.search.company.CompanySearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, String>, CompanySearch {
}
