package kr.co.chogosu.erp.repository.search.order1;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import kr.co.chogosu.erp.domain.Order1;
import kr.co.chogosu.erp.domain.QOrder1;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class Order1SearchImpl extends QuerydslRepositorySupport implements Order1Search {

    public Order1SearchImpl() {
        super(Order1.class);
    }


    @Override
    public Page<Order1> search1(Pageable pageable) {

        QOrder1 order1 = QOrder1.order1;

        JPQLQuery<Order1> query = from(order1);

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        booleanBuilder.or(order1.orderCode.contains("1"));

        booleanBuilder.or(order1.orderManager.contains("만기"));

        query.where(order1.orderCode.contains("1"));

        this.getQuerydsl().applyPagination(pageable, query);

        List<Order1> list = query.fetch();

        long count = query.fetchCount();

        return null;
    }

    @Override
    public Page<Order1> searchAll(String types, String keyword, Pageable pageable) {

        QOrder1 order1 = QOrder1.order1;
        JPQLQuery<Order1> query = from(order1);

        if ((types != null) && keyword != null) {

            BooleanBuilder booleanBuilder = new BooleanBuilder();
                switch (types) {
                    case "code":
                        booleanBuilder.or(order1.orderCode.contains(keyword));
                        break;
                    case "manager":
                        booleanBuilder.or(order1.orderManager.contains(keyword));
                        break;
                }
            query.where(booleanBuilder);
        }
        query.where(order1.orderNo.gt(0L));

        this.getQuerydsl().applyPagination(pageable, query);

        List<Order1> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);
    }
}
