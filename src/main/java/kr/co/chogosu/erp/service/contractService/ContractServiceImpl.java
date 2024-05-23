package kr.co.chogosu.erp.service.contractService;

import kr.co.chogosu.erp.domain.Contract;
import kr.co.chogosu.erp.domain.Order1;
import kr.co.chogosu.erp.domain.Product;
import kr.co.chogosu.erp.dto.contract.ContractDTO;
import kr.co.chogosu.erp.dto.order.Order1DTO;
import kr.co.chogosu.erp.dto.order.PageRequestDTO;
import kr.co.chogosu.erp.dto.order.PageResponseDTO;
import kr.co.chogosu.erp.dto.product.ProductDTO;
import kr.co.chogosu.erp.repository.contract.ContractRepository;
import kr.co.chogosu.erp.repository.productRepository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    @Override
    public PageResponseDTO<ContractDTO> contractList(PageRequestDTO pageRequestDTO) {

        String types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("contractNo");

        Page<Contract> result = contractRepository.searchAll(types, keyword, pageable);

        List<ContractDTO> dtoList = result.getContent().stream().map(contract -> modelMapper.map(contract, ContractDTO.class)).collect(Collectors.toList());

        return PageResponseDTO.<ContractDTO>orderWithAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .build();
    }

    @Override
    public Long register(ContractDTO contractDTO) {

        Contract contract = modelMapper.map(contractDTO, Contract.class);

        Long contractNo = contractRepository.save(contract).getContractNo();

        return contractNo;
    }

    @Override
    public ContractDTO readOne(Long contractNo) {

        Optional<Contract> result = contractRepository.findById(contractNo);

        Contract contract = result.orElseThrow();

        ContractDTO contractDTO = modelMapper.map(contract, ContractDTO.class);

        return contractDTO;
    }

    @Override
    public List<ProductDTO> productFindAll(List<Product> productList) {

        List<Product> all = productRepository.findAll();

        List<ProductDTO> productDTOList = all.stream()
                .map(product -> {
                    ProductDTO productDTO = new ProductDTO();
                    productDTO.setPname(product.getPname());
                    productDTO.setPcode(product.getPcode());
                    productDTO.setPname(product.getPprice());
                    productDTO.setPcontent(product.getPcontent());
                    productDTO.setDiscontinued(product.getDiscontinued());
                    return productDTO;
                })
                .collect(Collectors.toList());

        return productDTOList;
    }

    @Override
    public void modify(ContractDTO contractDTO) {

        Optional<Contract> result = contractRepository.findById(contractDTO.getContractNo());

        Contract contract = result.orElseThrow();

        contract.change(contractDTO.getContractState(), contractDTO.getProduct(), contractDTO.getCompany());

        contractRepository.save(contract);

    }

//    @Override
//    public List<ClassificationDTO> classificationFindAll(List<Classification> classificationList) {
//
//        List<Classification> all = classificationRepository.findAll();
//
//        List<ClassificationDTO> classificationDTOList = all.stream()
//                .map(classification -> {
//                    ClassificationDTO classificationDTO = new ClassificationDTO();
//                    classificationDTO.setClassificationNo(classification.getClassificationNo());
//                    classificationDTO.setClassificationName(classification.getClassificationName());
//                    return classificationDTO;
//                })
//                .collect(Collectors.toList());
//
//        return classificationDTOList;
//    }
}