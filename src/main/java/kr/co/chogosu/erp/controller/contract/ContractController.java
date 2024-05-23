package kr.co.chogosu.erp.controller.contract;

import kr.co.chogosu.erp.domain.Classification;
import kr.co.chogosu.erp.domain.Product;
import kr.co.chogosu.erp.dto.order.Order1DTO;
import kr.co.chogosu.erp.dto.order.PageRequestDTO;
import kr.co.chogosu.erp.dto.order.PageResponseDTO;
import kr.co.chogosu.erp.dto.contract.ContractDTO;
import kr.co.chogosu.erp.dto.product.ProductDTO;
import kr.co.chogosu.erp.repository.contract.ClassificationRepository;
import kr.co.chogosu.erp.repository.productRepository.ProductRepository;
import kr.co.chogosu.erp.service.contractService.ContractService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/contract")
@Log4j2
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;

    private final ProductRepository productRepository;

    private final ClassificationRepository classificationRepository;

    @GetMapping("/list")
    public void contractListAll(PageRequestDTO pageRequestDTO, Model model) {

        PageResponseDTO<ContractDTO> responseDTO = contractService.contractList(pageRequestDTO);

        model.addAttribute("responseDTO", responseDTO);

    }

    @GetMapping("/register")
    public void contractRegisterGET() {

    }

    @PostMapping("/register")
    public String contractRegisterPOST(@Valid ContractDTO contractDTO,
                                       BindingResult bindingResult,
                                       RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/contract/register";
        }

        //이유는 알 수 없지만 pName만 등록이 되지 않아서 수동으로 입력하는 코드
        contractDTO.getProduct().setPname(contractDTO.getProduct().getPname());

        Long contractNo = contractService.register(contractDTO);

        redirectAttributes.addFlashAttribute("result", contractNo);

        return "redirect:/contract/list";

    }

    @GetMapping({"/read", "/modify"})
    public void read(Long contractNo, PageRequestDTO pageRequestDTO, Model model) {

        ContractDTO contractDTO = contractService.readOne(contractNo);

        List<Product> all = productRepository.findAll();

        List<ProductDTO> productDTOList = contractService.productFindAll(all);

        List<Classification> all1 = classificationRepository.findAll();

//        List<ClassificationDTO> classificationDTOList = contractService.classificationFindAll(all1);

        model.addAttribute("dto", contractDTO);
        model.addAttribute("productList", productDTOList);
//        model.addAttribute("classificationList", classificationDTOList);

    }

    @PostMapping("/modify")
    public String modify(PageRequestDTO pageRequestDTO,
                         @Valid ContractDTO contractDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            String link = pageRequestDTO.getLink();

            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            redirectAttributes.addAttribute("orderNo", contractDTO.getContractNo());

            return "redirect:/contract/modify?" + link;
        }
        contractService.modify(contractDTO);

        redirectAttributes.addFlashAttribute("result", "modified");

        redirectAttributes.addAttribute("contractNo", contractDTO.getContractNo());

        return "redirect:/contract/read";
    }

}
