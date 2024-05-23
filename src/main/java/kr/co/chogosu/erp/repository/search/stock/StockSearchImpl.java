package kr.co.chogosu.erp.repository.search.stock;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import kr.co.chogosu.erp.domain.*;
import kr.co.chogosu.erp.repository.productRepository.ProductRepository;
import kr.co.chogosu.erp.repository.stockRepository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.ArrayList;
import java.util.List;

public class StockSearchImpl extends QuerydslRepositorySupport implements StockSearch {


    public StockSearchImpl() {
        super(Stock.class);
    }



    @Override
    public Page<Stock> searchAll(String types, Pageable pageable) {

        BooleanBuilder booleanBuilder1 = new BooleanBuilder();
        QStock stock = QStock.stock;
        QProduct product = QProduct.product;
        JPQLQuery<Stock> query = from(stock);
        JPQLQuery<Product> query1 = from(product);
        if (types != null) {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            switch (types) {
                case "케이스":
                    booleanBuilder.or(stock.kind.eq("케이스"));
                    break;
                case "CPU":
                    booleanBuilder.or(stock.kind.contains("CPU"));
                    break;
                case "키보드":
                    booleanBuilder.or(stock.kind.contains("키보드"));
                    break;
                case "메모리":
                    booleanBuilder.or(stock.kind.contains("메모리"));
                    break;
                case "모니터":
                    booleanBuilder.or(stock.kind.contains("모니터"));
                    break;
                case "마우스":
                    booleanBuilder.or(stock.kind.contains("마우스"));
                    break;
                case "파워":
                    booleanBuilder.or(stock.kind.contains("파워"));
                    break;
                case "그래픽카드":
                    booleanBuilder.or(stock.kind.contains("그래픽카드"));
                    break;
                case "유선랜카드":
                    booleanBuilder.or(stock.kind.contains("유선랜카드"));
                    break;
                case "무선랜카드":
                    booleanBuilder.or(stock.kind.contains("무선랜카드"));
                    break;
                case "스피커":
                    booleanBuilder.or(stock.kind.contains("스피커"));
                    break;
            }
            query.where(booleanBuilder);
        }

        query.where(stock.sno.gt(0L));

        this.getQuerydsl().applyPagination(pageable, query);
        List<Stock> stockList = query.fetch();
        long count = query.fetchCount();

        return new PageImpl<>(stockList, pageable, count);
    }

    @Override
    public Page<StockHistory> historyAll(Pageable pageable) {
        QStockHistory stockHistory = QStockHistory.stockHistory;
        JPQLQuery<StockHistory> query = from(stockHistory);
        return null;
    }

}
