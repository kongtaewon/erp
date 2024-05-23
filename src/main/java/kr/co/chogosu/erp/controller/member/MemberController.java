package kr.co.chogosu.erp.controller.member;

import kr.co.chogosu.erp.security.dto.MemberSecurityDTO;
import kr.co.chogosu.erp.service.member.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import kr.co.chogosu.erp.dto.member.MemberJoinDTO;
import kr.co.chogosu.erp.service.member.MemberService;

@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {

    //의존성 주입
    private final MemberService memberService;

    private final MemberServiceImpl memberServiceImpl;

    @GetMapping("/login")
    public void loginGET(String errorCode, String logout) {
        log.info("login get....................");
        log.info("logout: " + logout);

        if(logout != null){
            log.info("user logout...................");
        }
    }

    @GetMapping("/join")
    public void joinGET() {

        log.info("join get...");

    }

    @PostMapping("/join")
    public String joinPOST(MemberJoinDTO memberJoinDTO, RedirectAttributes redirectAttributes) {

        log.info("join post...");
        log.info(memberJoinDTO);

        try {
            memberService.join((memberJoinDTO));
        } catch (MemberService.MidExistException e) {

            redirectAttributes.addFlashAttribute("error", "mid");
            return "redirect:/member/join";
        }

        redirectAttributes.addFlashAttribute("result", "success");

        return "redirect:/member/login"; //회원가입 후 로그인

    }

    @GetMapping("/modify")
    public String modifyGET(@AuthenticationPrincipal MemberSecurityDTO memberSecurityDTO, Model model) {

        log.info("modify get...");

      String mid = memberSecurityDTO.getMid();
      String email = memberSecurityDTO.getEmail();

      model.addAttribute("mid", mid);
      model.addAttribute("email", email);

      return "/member/modify";
    }

    @PostMapping("/modify")
    public ResponseEntity<String> modifyPassword(@RequestParam String mid, @RequestParam String newPassword) {

        try {
            memberServiceImpl.updatePassword(mid, newPassword);
            return ResponseEntity.ok("비밀번호 변경에 성공하였습니다. ");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("비밀번호 변경에 실패하였습니다.");
        }
     }



}



