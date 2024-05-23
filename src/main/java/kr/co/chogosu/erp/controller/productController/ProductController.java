package kr.co.chogosu.erp.controller.productController;

import kr.co.chogosu.erp.dto.board.PageRequestDTO;
import kr.co.chogosu.erp.dto.board.PageResponseDTO;
import kr.co.chogosu.erp.dto.product.ProductDTO;
import kr.co.chogosu.erp.service.productService.ProductService;
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
import java.time.LocalDate;

@Controller
@RequestMapping("/product")
@Log4j2
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/product-list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        PageResponseDTO<ProductDTO> responseDTO = productService.list(pageRequestDTO);
        System.out.println(responseDTO);
    log.info(responseDTO);
    model.addAttribute("responseDTO", responseDTO);
    }
    @GetMapping("/product-register")
    public void registerGET(Model model){
        LocalDate now = LocalDate.now();
        model.addAttribute("now", now);
    }

    @PostMapping("/product-register")
    public String registerPOST(@Valid ProductDTO productDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        log.info(productDTO);

        if (bindingResult.hasErrors()) {
            log.info("has errors........");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/product/product-register";
        }

        String productName = productService.register(productDTO);
        redirectAttributes.addFlashAttribute("result", productName);

        return "redirect:/product/product-list";
    }
    @GetMapping({"/product-read", "/product-modify"})
    public void read(String pname, PageRequestDTO pageRequestDTO, Model model) {

        ProductDTO productDTO = productService.readOne(pname);

        log.info(productDTO);

        model.addAttribute("dto", productDTO);

    }
    @PostMapping("/product-modify")
    public String modify(PageRequestDTO pageRequestDTO, @Valid ProductDTO productDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){
        log.info("product modify post......");

        if (bindingResult.hasErrors()){
            log.info("has error..........");
            String link = pageRequestDTO.getLink();
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("pname", productDTO.getPname());
            return "redirect:/product/product-modify"+link;
        }

        log.info("이름 : "+productDTO.getPname());
        log.info("이름 : "+productDTO);
        productService.modify(productDTO);
        redirectAttributes.addFlashAttribute("result", "modified");
        redirectAttributes.addAttribute("pname", productDTO.getPname());
        return "redirect:/product/product-read";
    }
    @PostMapping("/product-remove")
    public String remove(String pname, RedirectAttributes redirectAttribute){
        log.info("remove post..." + pname);
        productService.remove(pname);
        redirectAttribute.addFlashAttribute("result", "remove");
        return "redirect:/product/product-list";
    }

}
