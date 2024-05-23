package kr.co.chogosu.erp.service.contractService;

import kr.co.chogosu.erp.domain.Classification;
import kr.co.chogosu.erp.domain.Product;
import kr.co.chogosu.erp.dto.contract.ClassificationDTO;
import kr.co.chogosu.erp.dto.order.Order1DTO;
import kr.co.chogosu.erp.dto.order.PageRequestDTO;
import kr.co.chogosu.erp.dto.order.PageResponseDTO;
import kr.co.chogosu.erp.dto.contract.ContractDTO;
import kr.co.chogosu.erp.dto.product.ProductDTO;

import java.util.List;

public interface ContractService {

    PageResponseDTO<ContractDTO> contractList(PageRequestDTO pageRequestDTO);

    Long register(ContractDTO contractDTO);

    ContractDTO readOne(Long contractNo);

    List<ProductDTO> productFindAll(List<Product> productList);

    void modify(ContractDTO contractDTO);

//    List<ClassificationDTO> classificationFindAll(List<Classification> classificationList);

}
