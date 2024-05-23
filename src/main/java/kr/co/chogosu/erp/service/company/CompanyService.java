package kr.co.chogosu.erp.service.company;

import kr.co.chogosu.erp.domain.Product;
import kr.co.chogosu.erp.dto.board.PageRequestDTO;
import kr.co.chogosu.erp.dto.board.PageResponseDTO;
import kr.co.chogosu.erp.dto.company.CompanyDTO;
import kr.co.chogosu.erp.dto.product.ProductDTO;

import java.util.List;

public interface CompanyService {

    PageResponseDTO<CompanyDTO> list(PageRequestDTO pageRequestDTO);

}
