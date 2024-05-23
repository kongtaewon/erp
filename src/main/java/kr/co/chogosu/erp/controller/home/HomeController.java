package kr.co.chogosu.erp.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/manage/noEmail")
    public String noEmail() {
        return "/manage/noEmail";
    }

    @GetMapping("/manage/eCommerceTermsOfUse")
    public String eCommerceTermsOfUse() {
        return "/manage/eCommerceTermsOfUse";
    }

    @GetMapping("/manage/termsOfUse")
    public String termsOfUse() {
        return "/manage/termsOfUse";
    }

    @GetMapping("/manage/company")
    public String company() {
        return "/manage/company";
    }
}
