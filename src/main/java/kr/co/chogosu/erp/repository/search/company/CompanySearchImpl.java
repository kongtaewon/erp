package kr.co.chogosu.erp.repository.search.company;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import kr.co.chogosu.erp.domain.Company;
import kr.co.chogosu.erp.domain.QCompany;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class CompanySearchImpl extends QuerydslRepositorySupport implements CompanySearch {

    public CompanySearchImpl() {
        super(Company.class);
    }

    @Override
    public Page<Company> searchAll(String types, String keyword, Pageable pageable) {
        QCompany company = QCompany.company;
        JPQLQuery<Company> query = from(company);

        if ((types != null) && keyword != null) {

            BooleanBuilder booleanBuilder = new BooleanBuilder();
            switch (types) {
                case "name":
                    booleanBuilder.or(company.companyName.contains(keyword));
                    break;
                case "cNumber":
                    booleanBuilder.or(company.cCNumber.contains(keyword));
                    break;
                case "cPhonNumber":
                    booleanBuilder.or(company.cPhonNumber.contains(keyword));
                    break;
                case "cFax":
                    booleanBuilder.or(company.cFax.contains(keyword));
                    break;
                case "cAddress":
                    booleanBuilder.or(company.cAddress.contains(keyword));
                    break;
            }
            query.where(booleanBuilder);
        }
        query.where(company.companyName.gt(""));

        this.getQuerydsl().applyPagination(pageable, query);

        List<Company> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);

    }
}
