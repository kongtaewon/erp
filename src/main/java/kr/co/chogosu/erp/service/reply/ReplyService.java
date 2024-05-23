package kr.co.chogosu.erp.service.reply;

import kr.co.chogosu.erp.dto.board.PageRequestDTO;
import kr.co.chogosu.erp.dto.board.PageResponseDTO;
import kr.co.chogosu.erp.dto.reply.ReplyDTO;

public interface ReplyService {

    Long register(ReplyDTO replyDTO);

    ReplyDTO read(Long rno);

    void modify(ReplyDTO replyDTO);

    void remove(Long rno);

    PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO);

}
