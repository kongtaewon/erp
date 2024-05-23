package kr.co.chogosu.erp.service.company;

import kr.co.chogosu.erp.domain.Company;
import kr.co.chogosu.erp.dto.board.PageRequestDTO;
import kr.co.chogosu.erp.dto.board.PageResponseDTO;
import kr.co.chogosu.erp.dto.company.CompanyDTO;
import kr.co.chogosu.erp.dto.product.ProductDTO;
import kr.co.chogosu.erp.repository.company.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final ModelMapper modelMapper;
    private final CompanyRepository companyRepository;

    @Override
    public PageResponseDTO<CompanyDTO> list(PageRequestDTO pageRequestDTO) {
        String types = pageRequestDTO.getType();
        String keyword = pageRequestDTO.getKeyword();

        Pageable pageable = pageRequestDTO.getPageable("companyName");
        Page<Company> result = companyRepository.searchAll(types, keyword, pageable);

        List<CompanyDTO> dtoList = result.getContent().stream().map(company -> modelMapper.map(company, CompanyDTO.class)).collect(Collectors.toList());

        return PageResponseDTO.<CompanyDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .build();
    }
}
