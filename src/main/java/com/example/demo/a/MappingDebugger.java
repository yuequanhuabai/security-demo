package com.example.demo.a;

import jakarta.annotation.Resource;
import jakarta.servlet.Filter;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.authorization.ObservationAuthorizationManager;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.access.intercept.RequestMatcherDelegatingAuthorizationManager;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcherEntry;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

@Configuration
public class MappingDebugger {

    //    @Resource(name = "controllerEndpointHandlerMapping")
    @Resource(name = "requestMappingHandlerMapping")
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Bean
    public ApplicationRunner printAuthorizationMatchers(FilterChainProxy filterChainProxy) {

        return new ApplicationRunner() {
            @Override
            public void run(ApplicationArguments args) throws Exception {
                System.out.println("hello world!");
                printMatchers(filterChainProxy);
            }
        };
    }

    private void printMatchers(FilterChainProxy filterChainProxy) {
        try {
            List<SecurityFilterChain> filterChains = filterChainProxy.getFilterChains();
            for (SecurityFilterChain filterChain : filterChains) {
                List<Filter> filters = filterChain.getFilters();
                for (Filter filter : filters) {
                    if (filter instanceof AuthenticationFilter) {
                        System.out.println("找到 AuthorizationFilter: " + filter.getClass().getName());
                        Field managerField = AuthorizationFilter.class.getDeclaredField("authorizationManager");
                        managerField.setAccessible(true);
                        Object managerObj = null;
                        managerObj = managerField.get(filter);

                        if (managerObj instanceof ObservationAuthorizationManager) {
                            Field delegateField = managerObj.getClass().getDeclaredField("delegate");
                            delegateField.setAccessible(true);
                            managerObj = delegateField.get(managerObj);
                            System.out.println(" success");
                        } else {
                            System.out.println(" fail");
                        }

                        if (managerObj instanceof RequestMatcherDelegatingAuthorizationManager) {
                            RequestMatcherDelegatingAuthorizationManager delegationManger =
                                    (RequestMatcherDelegatingAuthorizationManager) managerObj;

                            Field mappingsMapField
                                    = RequestMatcherDelegatingAuthorizationManager.class.getDeclaredField("mappings");
                            mappingsMapField.setAccessible(true);
                            List<RequestMatcherEntry<AuthorizationManager<RequestAuthorizationContext>>> mappings =
                                    (List<RequestMatcherEntry<AuthorizationManager<RequestAuthorizationContext>>>) mappingsMapField.get(managerObj);
                            Iterator<RequestMatcherEntry<AuthorizationManager<RequestAuthorizationContext>>> iterator = mappings.iterator();
                            while (iterator.hasNext()) {
                                RequestMatcherEntry<AuthorizationManager<RequestAuthorizationContext>> entry = iterator.next();
                                Field matcherField = entry.getClass().getDeclaredField("requestMatcher");
                                matcherField.setAccessible(true);
                                Object matcher = matcherField.get(entry);
//                                if(matcher !=null && matcher.toString().contains("/**/api-docs/**")){
//                                    System.out.println("delete matcher: "+matcher.toString());
//                                    iterator.remove();
//                                }
                            }

                            for (RequestMatcherEntry<AuthorizationManager<RequestAuthorizationContext>> entry : mappings) {
                                RequestMatcher requestMatcher = entry.getRequestMatcher();
                                AuthorizationManager<RequestAuthorizationContext> authManager = entry.getEntry();
                                System.out.println("Matcher: " + requestMatcher);
                                System.out.println("AuthManager: " + authManager);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
