package kr.co.chogosu.erp.service.member;

import kr.co.chogosu.erp.domain.Member;
import kr.co.chogosu.erp.domain.MemberRole;
import kr.co.chogosu.erp.dto.member.MemberJoinDTO;
import kr.co.chogosu.erp.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final ModelMapper modelMapper;

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void join(MemberJoinDTO memberJoinDTO) throws MidExistException {

        String mid = memberJoinDTO.getMid();

        boolean exist = memberRepository.existsById(mid);

        if(exist) {
            throw new MidExistException();
        }

        Member member = modelMapper.map(memberJoinDTO, Member.class);
        member.changePassword(passwordEncoder.encode(memberJoinDTO.getMpw()));
        member.addRole(MemberRole.USER);

        log.info("====================================");
        log.info(member);
        log.info(member.getRoleSet());

        memberRepository.save(member);

    }

    // 비밀번호만 수정
    @Transactional
    public void updatePassword(String mid, String newPassword ) {

            memberRepository.updatePassword(mid, newPassword);

    }
}
