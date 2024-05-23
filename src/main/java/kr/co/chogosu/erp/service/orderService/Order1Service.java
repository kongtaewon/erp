package kr.co.chogosu.erp.service.orderService;

import kr.co.chogosu.erp.domain.Order1;
import kr.co.chogosu.erp.dto.order.PageRequestDTO;
import kr.co.chogosu.erp.dto.order.PageResponseDTO;
import kr.co.chogosu.erp.dto.order.Order1DTO;

public interface Order1Service {

    Long register(Order1DTO order1DTO);

    Order1DTO readOne(Long orderNo);

    void modify(Order1DTO order1DTO);

    void remove(Long orderNo);

    PageResponseDTO<Order1DTO> list(PageRequestDTO pageRequestDTO);

    String orderRandomCode();

    String priceChange(String price);

    Order1 orderStateChange(Long orderNo);

}
