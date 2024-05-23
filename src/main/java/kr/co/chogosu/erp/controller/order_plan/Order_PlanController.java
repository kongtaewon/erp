package kr.co.chogosu.erp.controller.order_plan;

import kr.co.chogosu.erp.dto.order_plan.Order_PlanDTO;
import kr.co.chogosu.erp.dto.order_plan.PageRequestDTO;
import kr.co.chogosu.erp.dto.order_plan.PageResponseDTO;
import kr.co.chogosu.erp.service.order_plan.Order_PlanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/order_plan")
@Log4j2
@RequiredArgsConstructor
public class Order_PlanController {

    private final Order_PlanService orderPlanService;

    // 목록, 검색 보여주기(list)
    @GetMapping("/op-list")
    public void list(PageRequestDTO pageRequestDTO, Model model){

        PageResponseDTO<Order_PlanDTO> responseDTO = orderPlanService.list(pageRequestDTO);

        log.info(responseDTO);

        model.addAttribute("responseDTO", responseDTO);

    }

    // 등록 폼
    @GetMapping("/op-register")
    public void registerGET(){
    }

    // 등록
    @PostMapping("/op-register")
    public String registerPOST(@Valid Order_PlanDTO order_planDTO, BindingResult bindingResul, RedirectAttributes redirectAttributes){
        log.info("order_plan post register---");

        if (bindingResul.hasErrors()){
            log.info("has errors---");
            redirectAttributes.addFlashAttribute("errors",bindingResul.getAllErrors());
            return  "redirect:/order_plan/op-register";
        }

        log.info(order_planDTO);

        Long opno = orderPlanService.order_planRegister(order_planDTO);

        redirectAttributes.addFlashAttribute("opno", opno);

        return  "redirect:/order_plan/op-list";

    }

    // 조회안에 수정버튼 있음
    @GetMapping({"/op-read","/op-modify"})
    public void read(Long opno,PageRequestDTO pageRequestDTO, Model model){

        Order_PlanDTO order_planDTO = orderPlanService.order_planRead(opno);

        log.info(order_planDTO);

        model.addAttribute("dto", order_planDTO);

    }

    // 수정
    @PostMapping("/op-modify")
    public String modify(PageRequestDTO pageRequestDTO,
                         @Valid Order_PlanDTO order_planDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){

                log.info("order_plan modify post......" + order_planDTO);

                if (bindingResult.hasErrors()){
                    log.info("has errors....");

                    String link = pageRequestDTO.getLink();

                    redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

                    redirectAttributes.addAttribute("opno",order_planDTO.getOpno());

            return "redirect:/order_plan/op-modify?"+link;
        }

        orderPlanService.order_planModify(order_planDTO);

        redirectAttributes.addFlashAttribute("result","modified");

        redirectAttributes.addAttribute("opno",order_planDTO.getOpno());

        return "redirect:/order_plan/op-read";
    }

    // 삭제
    @PostMapping("/op-remove")
    public String remove(Long opno, RedirectAttributes redirectAttributes){
        log.info("remove ---------->" + opno);

        orderPlanService.order_planRemove(opno);

        redirectAttributes.addFlashAttribute("result", "remove");

        return "redirect:/order_plan/op-list";
    }


    @GetMapping("/modalread")
    @ResponseBody
    public Order_PlanDTO modalread(@RequestParam Map<String, Object> param){
        log.info("modalread->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        String str = (String)param.get("opno");
        Long opno = Long.parseLong(str);
        Order_PlanDTO dto = orderPlanService.order_planRead(opno);
        return dto;
    }

    @PostMapping("/completeSave")
    public String completeSave(Order_PlanDTO order_planDTO){
        log.info("completeSave->>>>>>>>>>>>>>>>>>>>>>>>");

        order_planDTO.setOptstate("완료");
        orderPlanService.completeModify(order_planDTO);
        return "redirect:/order_plan/op-list";
    }







}
