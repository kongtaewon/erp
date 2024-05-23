package kr.co.chogosu.erp.service.orderService;

import com.querydsl.core.types.Order;
import kr.co.chogosu.erp.domain.Order1;
import kr.co.chogosu.erp.domain.StockHistory;
import kr.co.chogosu.erp.dto.order.PageRequestDTO;
import kr.co.chogosu.erp.dto.order.PageResponseDTO;
import kr.co.chogosu.erp.dto.order.Order1DTO;
import kr.co.chogosu.erp.repository.order.Order1Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class Order1ServiceImpl implements Order1Service {

    private final ModelMapper modelMapper;

    private final Order1Repository order1Repository;

    @Override
    public Long register(Order1DTO order1DTO) {

        Order1 order1 = modelMapper.map(order1DTO, Order1.class);

        Long orderNo = order1Repository.save(order1).getOrderNo();

        return orderNo;
    }

    @Override
    public Order1DTO readOne(Long orderNo) {

        Optional<Order1> result = order1Repository.findById(orderNo);

        Order1 order1 = result.orElseThrow();

        Order1DTO order1DTO = modelMapper.map(order1, Order1DTO.class);

        return order1DTO;
    }

    @Override
    public void modify(Order1DTO order1DTO) {

        Optional<Order1> result = order1Repository.findById(order1DTO.getOrderNo());

        Order1 order1 = result.orElseThrow();

        order1.change(order1DTO.getOrderProgress(), order1DTO.getOrderCount(), order1DTO.getOrderPrice(), order1DTO.getOrderState(), order1DTO.getOrderManager());

        order1Repository.save(order1);

    }

    @Override
    public void remove(Long orderNo) {

        order1Repository.deleteById(orderNo);

    }

    @Override
    public PageResponseDTO<Order1DTO> list(PageRequestDTO pageRequestDTO) {

        String types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("orderNo");
        Page<Order1> result = order1Repository.searchAll(types, keyword, pageable);

        List<Order1DTO> dtoList = result.getContent().stream().map(order1 -> modelMapper.map(order1, Order1DTO.class)).collect(Collectors.toList());

        return PageResponseDTO.<Order1DTO>orderWithAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .build();
    }

//    앞 8자리는 생성한 날짜 나머지 뒤 6자리는 알파벳 대문자와 숫자를 랜덤하게 생성하는 총 14자리의 코드를 만드는 메서드
    @Override
    public String orderRandomCode() {

        Date date = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String formatDate = dateFormat.format(date);

        int length = 14 - formatDate.length();

        String randomCode = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder stringBuilder = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(randomCode.length());
            char ch = randomCode.charAt(index);
            stringBuilder.append(ch);
        }

        String code = formatDate + stringBuilder.toString();

        return code;
    }

    @Override
    public String priceChange(String price) {

        String str = price;

        DecimalFormat decimalFormat = new DecimalFormat("#,###");

        int num = Integer.parseInt(str);
        String formattedNumber = decimalFormat.format(num);

        return formattedNumber;
    }

    @Override
    public Order1 orderStateChange(Long orderNo) {

        Optional<Order1> id = order1Repository.findById(orderNo);

        if (id.isPresent()) {

            Order1 order1 = id.get();

            order1.setOrderState("완료");

            return order1Repository.save(order1);

        } else {
            throw new EntityNotFoundException(orderNo + " 를 찾을수 없습니다");
        }

    }


}
