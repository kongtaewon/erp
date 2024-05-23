package kr.co.chogosu.erp.controller.orderController;

import kr.co.chogosu.erp.dto.order.InspectionDTO;
import kr.co.chogosu.erp.service.inspection.InspectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/order1")
public class InspectionController {

    private final InspectionService inspectionService;

    @GetMapping("/inspection-register")
    public void registerGET(@RequestParam long orderNo, Model model) {

        model.addAttribute("orderNo",orderNo);

    }

    @PostMapping("/inspection-register")
    public String registerPost(@Valid InspectionDTO inspectionDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/order1/inspection-register";
        }

        log.info(inspectionDTO);

        Long inspectionNo = inspectionService.register(inspectionDTO);

        redirectAttributes.addFlashAttribute("result", inspectionNo);

        return "redirect:/order1/order-list";
    }

}
