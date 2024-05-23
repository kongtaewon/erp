package kr.co.chogosu.erp.service.order_plan;

import kr.co.chogosu.erp.domain.Order_Plan;
import kr.co.chogosu.erp.dto.order_plan.PageRequestDTO;
import kr.co.chogosu.erp.dto.order_plan.PageResponseDTO;
import kr.co.chogosu.erp.dto.order_plan.Order_PlanDTO;
import kr.co.chogosu.erp.repository.order.Order_PlanRepository;
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
public class Order_PlanServiceImpl implements  Order_PlanService{

   private final ModelMapper modelMapper;

   private final Order_PlanRepository orderPlanRepository;

    // 등록
    @Override
    public Long order_planRegister(Order_PlanDTO order_planDTO) {

        Order_Plan entity = dtoToEntity(order_planDTO);

        orderPlanRepository.save(entity);

        return entity.getOpno();
    }

    // 조회
    @Override
    public Order_PlanDTO order_planRead(Long opno) {

        Optional<Order_Plan> result = orderPlanRepository.findById(opno);

        Order_Plan order_plan = result.orElseThrow();

        Order_PlanDTO order_planDTO = modelMapper.map(order_plan, Order_PlanDTO.class);

        return order_planDTO;
    }
    
    // 수정
    @Override
    public void order_planModify(Order_PlanDTO order_planDTO) {

        Optional<Order_Plan> result = orderPlanRepository.findById(order_planDTO.getOpno());

        Order_Plan order_plan =result.orElseThrow();

        //수정 부분 :  자재소요공정,자재소요일정,자재소요량,조달납기 String optuseprocess,String optusedate,int optusecount,String opdeliverydate
        order_plan.changeOrder_Plan(
                order_planDTO.getOptuseprocess(),
                order_planDTO.getOptusedate(),
                order_planDTO.getOptusecount(),
                order_planDTO.getOpdeliverydate());

        orderPlanRepository.save(order_plan);


    }

    // 삭제
    @Override
    public void order_planRemove(Long opno) {
        orderPlanRepository.deleteById(opno);

    }

    // 목록,검색
    @Override
    public PageResponseDTO<Order_PlanDTO> list(PageRequestDTO pageRequestDTO) {

        String types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("opno");

        Page<Order_Plan> result = orderPlanRepository.opsearchAll(types, keyword, pageable);

        List<Order_PlanDTO> dtoList = result.getContent().stream().map(orderPlan -> modelMapper.map(orderPlan, Order_PlanDTO.class)).collect(Collectors.toList());


        return PageResponseDTO.<Order_PlanDTO>opWithAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .build();
    }

    @Override
    public void completeModify(Order_PlanDTO order_planDTO) {
        Optional<Order_Plan> result = orderPlanRepository.findById(order_planDTO.getOpno());
        if (result.isPresent()){
            Order_Plan entity = result.get();
            entity.change(order_planDTO.getOptstate());
            orderPlanRepository.save(entity);
        }
    }


}
