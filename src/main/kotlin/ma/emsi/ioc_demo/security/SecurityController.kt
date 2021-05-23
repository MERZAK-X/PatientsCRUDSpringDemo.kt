package ma.emsi.ioc_demo.security

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest


@Controller
class SecurityController {
    @GetMapping("/notAuthorized")
    fun error(): String {
        return "notAuthorized"
    }

    @GetMapping("/login")
    fun login(): String {
        return "login"
    }

    @GetMapping("/logout")
    @Throws(ServletException::class)
    fun logout(request: HttpServletRequest): String {
        request.logout()
        return "redirect:/login"
    }
}