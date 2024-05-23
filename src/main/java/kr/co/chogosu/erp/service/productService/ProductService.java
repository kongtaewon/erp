package kr.co.chogosu.erp.service.productService;

import kr.co.chogosu.erp.domain.Product;
import kr.co.chogosu.erp.dto.board.PageRequestDTO;
import kr.co.chogosu.erp.dto.board.PageResponseDTO;
import kr.co.chogosu.erp.dto.product.ProductDTO;

import java.util.List;

public interface ProductService {
    String register(ProductDTO productDTO);

    ProductDTO readOne(String pname);

    void modify(ProductDTO productDTO);

    void remove(String pname);

    List<Product> searchType(String type);

    PageResponseDTO<ProductDTO> list(PageRequestDTO pageRequestDTO);

//    String priceSplit(String content);

}
