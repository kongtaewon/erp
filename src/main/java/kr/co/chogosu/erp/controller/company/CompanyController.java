package kr.co.chogosu.erp.controller.company;

import kr.co.chogosu.erp.dto.board.PageRequestDTO;
import kr.co.chogosu.erp.dto.board.PageResponseDTO;
import kr.co.chogosu.erp.dto.company.CompanyDTO;
import kr.co.chogosu.erp.dto.product.ProductDTO;
import kr.co.chogosu.erp.service.company.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/company")
@Log4j2
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/companyList")
    public void list(PageRequestDTO pageRequestDTO, Model model){

        PageResponseDTO<CompanyDTO> responseDTO = companyService.list(pageRequestDTO);

        model.addAttribute("responseDTO", responseDTO);
    }

}
