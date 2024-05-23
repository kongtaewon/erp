package kr.co.chogosu.erp.service.productService;

import kr.co.chogosu.erp.domain.Product;
import kr.co.chogosu.erp.dto.board.PageRequestDTO;
import kr.co.chogosu.erp.dto.board.PageResponseDTO;
import kr.co.chogosu.erp.dto.product.ProductDTO;
import kr.co.chogosu.erp.repository.productRepository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    @Override
    public String register(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        String productName = productRepository.save(product).getPname();
        return productName;
    }

    @Override
    public ProductDTO readOne(String pname) {
        Optional<Product> result = productRepository.findById(pname);
        Product product = result.orElseThrow(NoSuchElementException::new);
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        return productDTO;
    }


    @Override
    public void modify(ProductDTO productDTO) {
        Optional<Product> result = productRepository.findById(productDTO.getPname());
        Product product = result.orElseThrow();
        product.change(productDTO.getPcontent(), productDTO.getPprice());
        productRepository.save(product);

    }

    @Override
    public void remove(String pname) {
        productRepository.deleteById(pname);
    }

    @Override
    public List<Product> searchType(String type) {
        return productRepository.findByPcodeContaining(type);
    }

    @Override
    public PageResponseDTO<ProductDTO> list(PageRequestDTO pageRequestDTO) {
        String types = pageRequestDTO.getType();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("pname");
        Page<Product> result = productRepository.searchAll(types, keyword, pageable);
        List<ProductDTO> dtoList = result.getContent().stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toList());
        return PageResponseDTO.<ProductDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .build();
    }

//    @Override
//    public String priceSplit(String content) {
//        content = content.split("가격 : ")[1].split(" ")[0];
//        DecimalFormat price = new DecimalFormat("#,###" + "원");
//
//        return price.format(Integer.parseInt(content));
//    }
}
