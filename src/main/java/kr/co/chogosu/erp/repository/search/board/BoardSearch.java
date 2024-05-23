package kr.co.chogosu.erp.repository.search.board;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import kr.co.chogosu.erp.domain.Board;
import kr.co.chogosu.erp.dto.board.BoardListAllDTO;
import kr.co.chogosu.erp.dto.board.BoardListReplyCountDTO;

public interface BoardSearch {

    Page<Board> search1(Pageable pageable);

    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);

    Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types,
                                                      String keyword,
                                                      Pageable pageable);

    Page<BoardListAllDTO> searchWithAll(String[] types,
                                        String keyword,
                                        Pageable pageable);
}
