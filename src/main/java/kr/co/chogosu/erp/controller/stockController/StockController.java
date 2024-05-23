package kr.co.chogosu.erp.controller.stockController;

import kr.co.chogosu.erp.domain.Product;
import kr.co.chogosu.erp.domain.StockHistory;
import kr.co.chogosu.erp.dto.stock.PageRequestDTO;
import kr.co.chogosu.erp.dto.stock.PageResponseDTO;
import kr.co.chogosu.erp.dto.stock.StockDTO;
import kr.co.chogosu.erp.dto.stock.StockHistoryDTO;
import kr.co.chogosu.erp.repository.productRepository.ProductRepository;
import kr.co.chogosu.erp.repository.stockRepository.StockHistoryRepository;
import kr.co.chogosu.erp.service.stockService.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/stock")
@Log4j2
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    private final StockHistoryRepository stockHistoryRepository;

    private final ProductRepository productRepository;

    @GetMapping("/stock-list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {

        PageResponseDTO<StockDTO> responseDTO = stockService.list(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);

    }

    @GetMapping("/stock-register")
    public void registerGET(Model model){

    }


    @PostMapping("/stock-register")
    public String registerPost(@Valid StockDTO stockDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        log.info("board POST register.......");

        if(bindingResult.hasErrors()) {
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors() );
            return "redirect:/stock/stock-register";
        }

        log.info(stockDTO);

        Long bno  = stockService.register(stockDTO);

        redirectAttributes.addFlashAttribute("result", bno);

        return "redirect:/board/list";
    }

    @GetMapping("/stock-detail")
    public void stock_detail(String FKpName, Model model){
       model.addAttribute("dto", stockHistoryRepository.findByFKpName(FKpName));
       model.addAttribute("FKpName", FKpName);
    }

    @GetMapping("/stock-supply")
    public void stock_apply(Model model){
        List<Product> list = new ArrayList<>();

        LocalDate now = LocalDate.now();
        model.addAttribute("now", now);
        model.addAttribute("list", list);
        model.addAttribute("mouseList" ,productRepository.findByPcodeContaining("MOU"));
        model.addAttribute("cpuList" ,productRepository.findByPcodeContaining("CPU"));
        model.addAttribute("caseList" ,productRepository.findByPcodeContaining("CAS"));
        model.addAttribute("keyList" ,productRepository.findByPcodeContaining("KEY"));
        model.addAttribute("memList" ,productRepository.findByPcodeContaining("MEM"));
        model.addAttribute("monList" ,productRepository.findByPcodeContaining("MON"));
        model.addAttribute("powerList" ,productRepository.findByPcodeContaining("POW"));
        model.addAttribute("speakerList" ,productRepository.findByPcodeContaining("SPE"));
        model.addAttribute("graphicList" ,productRepository.findByPcodeContaining("GRA"));
        model.addAttribute("netList" ,productRepository.findByPcodeContaining("NET"));
        model.addAttribute("wlnList" ,productRepository.findByPcodeContaining("WLN"));
    }

    @PostMapping("/stock-supply")
    public String stock_apply(StockHistoryDTO stockHistoryDTO, BindingResult bindingResult){
        System.out.println(stockHistoryDTO);
//        StockHistory history = StockHistory.builder().FKpName(stockHistoryDTO.getFKpName()).hManager(stockHistoryDTO.getHManager()).hduedate(stockHistoryDTO.getHduedate()).pprice(stockHistoryDTO.getPprice()).build();
        StockHistory history = new StockHistory(stockHistoryDTO.getFKpName(), stockHistoryDTO.getHRequest(), stockHistoryDTO.getHManager(), stockHistoryDTO.getHRequest(), stockHistoryDTO.getHduedate(), stockHistoryDTO.getPprice());

        System.out.println(history);
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());
            return "redirect:/stock/stock-supply";
        }

        stockHistoryRepository.save(history);
        stockService.store_enter(history);

        return "redirect:/stock/stock-list";

    }

}
