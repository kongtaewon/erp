package kr.co.chogosu.erp.service.inspection;

import kr.co.chogosu.erp.domain.Inspection;
import kr.co.chogosu.erp.dto.order.InspectionDTO;
import kr.co.chogosu.erp.repository.inspection.InspectionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class InspectionServiceImpl implements InspectionService{

    private final ModelMapper modelMapper;

    private final InspectionRepository inspectionRepository;

    @Override
    public Long register(InspectionDTO inspectionDTO) {

        Inspection inspection = modelMapper.map(inspectionDTO, Inspection.class);

        Long inspectionNo = inspectionRepository.save(inspection).getInspectionNo();

        return inspectionNo;

    }
}
