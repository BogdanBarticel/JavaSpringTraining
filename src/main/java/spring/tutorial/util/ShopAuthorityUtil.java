package spring.tutorial.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import java.util.Collection;

public class ShopAuthorityUtil {

    public static Model setAuthorityAttributes(Model model) {
        if(SecurityContextHolder.getContext().getAuthentication() == null){
            model.addAttribute("authenticated", false);
        } else {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            model.addAttribute("authenticated", auth.isAuthenticated());
            if (auth.isAuthenticated()) {
                Collection<? extends GrantedAuthority> grantedAuthorities = auth.getAuthorities();
                for (GrantedAuthority authority : grantedAuthorities) {
                    model.addAttribute(authority.getAuthority(), true);
                }
            }
        }
        return model;
    }
}
