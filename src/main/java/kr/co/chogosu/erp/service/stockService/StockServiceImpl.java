package kr.co.chogosu.erp.service.stockService;

import kr.co.chogosu.erp.domain.Order1;
import kr.co.chogosu.erp.domain.Stock;
import kr.co.chogosu.erp.domain.StockHistory;
import kr.co.chogosu.erp.dto.stock.PageRequestDTO;
import kr.co.chogosu.erp.dto.stock.PageResponseDTO;
import kr.co.chogosu.erp.dto.stock.StockDTO;
import kr.co.chogosu.erp.dto.stock.StockHistoryDTO;
import kr.co.chogosu.erp.repository.stockRepository.StockHistoryRepository;
import kr.co.chogosu.erp.repository.stockRepository.StockRepository;
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
public class StockServiceImpl implements StockService {

    private final ModelMapper modelMapper;
    private final StockRepository stockRepository;
    private final StockHistoryRepository stockHistoryRepository;

    @Override
    public long register(StockDTO stockDTO) {

        Stock stock = modelMapper.map(stockDTO, Stock.class);

        long stockNo = stockRepository.save(stock).getSno();

        return stockNo;
    }

//    @Override
//    public StockDTO readOne(Long sno) {
//
//        Optional<Stock> result = stockRepository.findById(sno);
//
//        Stock stock = result.orElseThrow();
//
//        StockDTO stockDTO = modelMapper.map(stock, StockDTO.class);
//
//        return stockDTO;
//    }

    @Override
    public void modify(StockDTO stockDTO) {

        Optional<Stock> result = stockRepository.findById(stockDTO.getSno());

        Stock stock = result.orElseThrow();

        stock.change(stockDTO.getSno(), stockDTO.getFKpname(), stockDTO.getSamount());

        stockRepository.save(stock);

    }

    @Override
    public void remove(Long sno) {

        stockRepository.deleteById(sno);

    }

    @Override
    public void histrotyRegister(Order1 order1) {
        StockHistoryDTO dto = new StockHistoryDTO();
//         dto.builder().hRegdate(order1.getOrderDate()).FKpName(order1.getOrder)
    }

    @Override
    public PageResponseDTO<StockDTO> list(PageRequestDTO pageRequestDTO) {
        String types = pageRequestDTO.getTypes();
        Pageable pageable = pageRequestDTO.getPageable("sno");
        Page<Stock> result = stockRepository.searchAll(types, pageable);
        List<StockDTO> dtoList = result.getContent().stream().map(stock -> modelMapper.map(stock, StockDTO.class)).collect(Collectors.toList());
        return PageResponseDTO.<StockDTO>stockWithAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .build();
    }

    @Override
    public List<StockHistory> detail(String fkpname) {
        return stockHistoryRepository.findByFKpName(fkpname);
    }

    @Override
    public void store_enter(StockHistory stockHistoryDTO) {
        Stock stock = stockRepository.findByFKpname(stockHistoryDTO.getFKpName());
        stock.change1((stock.getSamount() + stockHistoryDTO.getSin()));
        stockHistoryRepository.save(stockHistoryDTO);
        stockRepository.save(stock);
    }

    @Override
    public void release_enter(StockHistory stockHistoryDTO) {
        Stock stock = stockRepository.findByFKpname(stockHistoryDTO.getFKpName());
        stock.change1((stock.getSamount() - stockHistoryDTO.getSout()));
        stockHistoryRepository.save(stockHistoryDTO);
        stockRepository.save(stock);
    }

//    @Override
//    public PageResponseDTO<StockDTO> list(PageRequestDTO pageRequestDTO) {
//        String types = pageRequestDTO.getType();
//        Pageable pageable = pageRequestDTO.getPageable("sno");
//        Page<Stock> result = stockRepository.searchAll(types, pageable);
//        List<StockDTO> dtoList = result.getContent().stream().map(stock -> modelMapper.map(stock, StockDTO.class)).collect(Collectors.toList());
//        System.out.println(dtoList + "asdasdasdasd");
//        return PageResponseDTO.<StockDTO>stockWithAll()
//                .pageRequestDTO(pageRequestDTO)
//                .dtoList(dtoList)
//                .total((int) result.getTotalElements())
//                .build();
//
//
//    }

//    @Override
//    public List<ProductDTO> productList() {
//        List<ProductDTO> list = new ArrayList<ProductDTO>();
//        for (Product product : productRepository.findAll()) {
//            list.add(ProductDTO.toDTO(product));
//        }
//
//        return list;
//    }


}
