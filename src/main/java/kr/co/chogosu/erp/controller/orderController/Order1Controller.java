package kr.co.chogosu.erp.controller.orderController;

import kr.co.chogosu.erp.dto.order.PageRequestDTO;
import kr.co.chogosu.erp.dto.order.PageResponseDTO;
import kr.co.chogosu.erp.dto.order.Order1DTO;
import kr.co.chogosu.erp.service.orderService.Order1Service;
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

@Controller
@RequestMapping("/order1")
@Log4j2
@RequiredArgsConstructor
public class Order1Controller {

    private final Order1Service order1Service;

    @GetMapping("/order-list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {

        PageResponseDTO<Order1DTO> responseDTO = order1Service.list(pageRequestDTO);

        log.info(responseDTO);

        model.addAttribute("responseDTO", responseDTO);
    }

    @GetMapping("/order-register")
    public void registerGET() {


    }

    @PostMapping("/order-register")
    public String registerPost(@Valid Order1DTO order1DTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        log.info("order1 POST register.......");

        if (bindingResult.hasErrors()) {
            log.info("has errors........");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/order1/order-register";
        }

        log.info(order1DTO);

        Long orderNo = order1Service.register(order1DTO);

        redirectAttributes.addFlashAttribute("result", orderNo);

        return "redirect:/order1/order-list";
    }

    @GetMapping({"/order-read", "/order-modify"})
    public void read(Long orderNo, PageRequestDTO pageRequestDTO, Model model) {

        Order1DTO order1DTO = order1Service.readOne(orderNo);

        order1DTO.getProduct().setPname(order1DTO.getProduct().getPname());

        log.info(order1DTO);

        model.addAttribute("dto", order1DTO);

    }

    @PostMapping("/order-modify")
    public String modify(PageRequestDTO pageRequestDTO,
                         @Valid Order1DTO order1DTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        log.info("order1 modify post......" + order1DTO);

        if (bindingResult.hasErrors()) {
            log.info("has errors.....");

            String link = pageRequestDTO.getLink();

            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            redirectAttributes.addAttribute("orderNo", order1DTO.getOrderNo());

            return "redirect:/order1/order-modify?"+link;
        }
        order1Service.modify(order1DTO);

        redirectAttributes.addFlashAttribute("result", "modified");

        redirectAttributes.addAttribute("orderNo", order1DTO.getOrderNo());

        return "redirect:/order1/order-read";
    }

    @PostMapping("/order-remove")
    public String remove(Long orderNo, RedirectAttributes redirectAttributes) {

        log.info("remove post....." + orderNo);

        order1Service.remove(orderNo);

        redirectAttributes.addFlashAttribute("result", "remove");

        return "redirect:/order1/order-list";

    }
}
