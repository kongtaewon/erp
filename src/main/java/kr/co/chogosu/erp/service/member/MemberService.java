package kr.co.chogosu.erp.service.member;

import kr.co.chogosu.erp.dto.member.MemberJoinDTO;

public interface MemberService {

    static class MidExistException extends Exception {

    }

    void join(MemberJoinDTO memberJoinDTO) throws MidExistException ;


}
