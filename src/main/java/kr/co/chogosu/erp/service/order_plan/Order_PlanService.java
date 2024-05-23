package kr.co.chogosu.erp.service.order_plan;

import kr.co.chogosu.erp.domain.Order_Plan;
import kr.co.chogosu.erp.dto.order_plan.PageRequestDTO;
import kr.co.chogosu.erp.dto.order_plan.PageResponseDTO;
import kr.co.chogosu.erp.dto.order_plan.Order_PlanDTO;

public interface Order_PlanService {

    // 등록
    Long order_planRegister(Order_PlanDTO order_planDTO);

    // 조회
    Order_PlanDTO order_planRead(Long opno);

    // 수정
    void order_planModify(Order_PlanDTO order_planDTO);
    
    // 삭제
    void order_planRemove(Long opno);
    
    // 검색,목록
    PageResponseDTO<Order_PlanDTO> list(PageRequestDTO pageRequestDTO);

    void completeModify(Order_PlanDTO order_planDTO);

    // dto -> 엔티티
    default Order_Plan dtoToEntity(Order_PlanDTO dto){
        Order_Plan entity = Order_Plan.builder()
                .opno(dto.getOpno())
                .opcode(dto.getOpcode())
                .opname(dto.getOpname())
                .optuseprocess(dto.getOptuseprocess())
                .optusedate(dto.getOptusedate())
                .optusecount(dto.getOptusecount())
                .opdeliverydate(dto.getOpdeliverydate())
                .optstate(dto.getOptstate())
                .build();
        return entity;
    }

    //엔티티 -> dto
    default Order_PlanDTO entityToDto(Order_Plan entity){
        Order_PlanDTO dto = Order_PlanDTO.builder()
                .opno(entity.getOpno())
                .opcode(entity.getOpcode())
                .opname(entity.getOpname())
                .optuseprocess(entity.getOptuseprocess())
                .optusedate(entity.getOptusedate())
                .optusecount(entity.getOptusecount())
                .opdeliverydate(entity.getOpdeliverydate())
                .optstate(entity.getOptstate())
                .build();
        return dto;
    }


}
