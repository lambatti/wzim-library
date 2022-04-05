package pl.sggw.wzimlibrary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import pl.sggw.wzimlibrary.model.constant.Role;

@Configuration
public class HierarchyConfig {
    @Bean
    public RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_" + Role.ADMIN + " > ROLE_" + Role.WORKER + " \n "
                + "ROLE_" + Role.WORKER + " > ROLE_" + Role.USER);
        return roleHierarchy;
    }
}
