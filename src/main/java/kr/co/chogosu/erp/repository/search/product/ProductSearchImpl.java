package kr.co.chogosu.erp.repository.search.product;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import kr.co.chogosu.erp.domain.Product;
import kr.co.chogosu.erp.domain.QProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ProductSearchImpl extends QuerydslRepositorySupport implements ProductSearch {
   public ProductSearchImpl(){super((Product.class));}

    @Override
    public Page<Product> search(Pageable pageable) {
       QProduct product = QProduct.product;
       JPQLQuery<Product> query = from(product);
       BooleanBuilder booleanBuilder = new BooleanBuilder();
       booleanBuilder.or(product.pname.contains("마우스"));
       booleanBuilder.or(product.pcode.contains("Mou"));
       booleanBuilder.or(product.pcontent.contains("이름"));
       this.getQuerydsl().applyPagination(pageable, query);
       List<Product> list = query.fetch();
       long count = query.fetchCount();
        return null;
    }

    @Override
    public Page<Product> searchAll(String types, String keyword, Pageable pageable) {
        QProduct product = QProduct.product;
        JPQLQuery<Product> query = from(product);

      if ((types != null) && keyword != null){
          BooleanBuilder booleanBuilder = new BooleanBuilder();
          switch (types){
              case "name":
                  booleanBuilder.or(product.pname.contains(keyword));
                  break;
              case "code":
                  booleanBuilder.or(product.pcode.contains(keyword));
                  break;
              case  "content":
                  booleanBuilder.or(product.pcontent.contains(keyword));
                  break;
          }
          query.where(booleanBuilder);
      }
      query.where(product.pname.gt(""));
      this.getQuerydsl().applyPagination(pageable, query);
      List<Product> list = query.fetch();
      long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);
    }
}
