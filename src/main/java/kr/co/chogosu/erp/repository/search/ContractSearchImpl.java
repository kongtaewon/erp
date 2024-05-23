package kr.co.chogosu.erp.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import kr.co.chogosu.erp.domain.Contract;
import kr.co.chogosu.erp.domain.QContract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ContractSearchImpl extends QuerydslRepositorySupport implements ContractSearch {

    public ContractSearchImpl() {
        super(Contract.class);
    }

    @Override
    public Page<Contract> searchAll(String types, String keyword, Pageable pageable) {
        QContract contract = QContract.contract;
        JPQLQuery<Contract> query = from(contract);

        if ((types != null) && keyword != null) {

            BooleanBuilder booleanBuilder = new BooleanBuilder();
            switch (types) {
                case "code":
                    booleanBuilder.or(contract.contractCode.contains(keyword));
                    break;
                case "date":
                    booleanBuilder.or(contract.contractDate.contains(keyword));
                    break;
                case "state":
                    booleanBuilder.or(contract.contractState.contains(keyword));
                    break;
            }
            query.where(booleanBuilder);
        }
        query.where(contract.contractNo.gt(0L));

        this.getQuerydsl().applyPagination(pageable, query);

        List<Contract> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);

    }
}
