package kr.co.chogosu.erp.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import kr.co.chogosu.erp.domain.Order_Plan;
import kr.co.chogosu.erp.domain.QOrder_Plan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class Order_PlanSearchImpl extends QuerydslRepositorySupport implements Order_PlanSearch{

    public Order_PlanSearchImpl() {
        super(Order_Plan.class);
    }


    @Override
    public Page<Order_Plan> opsearch(Pageable pageable) {

        QOrder_Plan Order_plan =QOrder_Plan.order_Plan;
        JPQLQuery<Order_Plan> query =from(Order_plan);
        query.where(Order_plan.opcode.contains("1"));
        List<Order_Plan> list = query.fetch();
        long count = query.fetchCount();

        return null;
    }

    @Override
    public Page<Order_Plan> opsearchAll(String type, String keyword, Pageable pageable) {

        QOrder_Plan order_plan = QOrder_Plan.order_Plan;
        JPQLQuery<Order_Plan> query =from(order_plan);

        if ((type != null) && keyword != null){

            BooleanBuilder booleanBuilder = new BooleanBuilder();
            switch (type){
                case "opcode":  // 품목코드
                    booleanBuilder.or(order_plan.opcode.contains(keyword));
                    break;
                case "opname":  // 품목이름
                    booleanBuilder.or(order_plan.opname.contains(keyword));
                    break;
                case "optuseprocess":   //자재소요공정
                    booleanBuilder.or(order_plan.optuseprocess.contains(keyword));
                    break;
            }
            query.where(booleanBuilder);
        }
        query.where(order_plan.opno.gt(0L));
        this.getQuerydsl().applyPagination(pageable,query);
        List<Order_Plan> list = query.fetch();
        long count = query.fetchCount();

        return new PageImpl<>(list,pageable,count);
    }
}
