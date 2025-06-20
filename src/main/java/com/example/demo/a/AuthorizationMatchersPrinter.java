//package com.example.demo.a;
//
//import jakarta.servlet.Filter;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authorization.AuthorizationManager;
//import org.springframework.security.web.FilterChainProxy;
//import org.springframework.security.web.access.intercept.AuthorizationFilter;
//import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
//import org.springframework.security.web.access.intercept.RequestMatcherDelegatingAuthorizationManager;
//
//import org.springframework.security.web.util.matcher.RequestMatcher;
//
//import java.lang.reflect.Field;
//import java.util.List;
//import java.util.Map;
//
//@Configuration
//public class AuthorizationMatchersPrinter {
//
//    @Bean
//    public ApplicationRunner printAuthorizationMatchers(FilterChainProxy filterChainProxy) {
//        return new ApplicationRunner() {
//            @Override
//            public void run(ApplicationArguments args) {
//                try {
//                    List<org.springframework.security.web.SecurityFilterChain> chains = filterChainProxy.getFilterChains();
//                    for (org.springframework.security.web.SecurityFilterChain chain : chains) {
//                        List<Filter> filters = chain.getFilters();
//                        for (Filter filter : filters) {
//                            if (filter instanceof AuthorizationFilter) {
//                                System.out.println("🔍 找到 AuthorizationFilter：" + filter.getClass().getName());
//
//                                Field managerField = AuthorizationFilter.class.getDeclaredField("authorizationManager");
//                                managerField.setAccessible(true);
//                                Object managerObj = managerField.get(filter);
//
//                                if (managerObj instanceof RequestMatcherDelegatingAuthorizationManager) {
//                                    RequestMatcherDelegatingAuthorizationManager manager =
//                                            (RequestMatcherDelegatingAuthorizationManager) managerObj;
//
//                                    Field mappingsField = RequestMatcherDelegatingAuthorizationManager.class.getDeclaredField("mappings");
//                                    mappingsField.setAccessible(true);
//                                    Object mappings = mappingsField.get(manager);
//
//                                    Field matcherMapField = mappings.getClass().getDeclaredField("mappings");
//                                    matcherMapField.setAccessible(true);
//                                    @SuppressWarnings("unchecked")
//                                    Map<RequestMatcher, AuthorizationManager<RequestAuthorizationContext>> matcherMap =
//                                            (Map<RequestMatcher, AuthorizationManager<RequestAuthorizationContext>>) matcherMapField.get(mappings);
//
//                                    for (Map.Entry<RequestMatcher, AuthorizationManager<RequestAuthorizationContext>> entry : matcherMap.entrySet()) {
//                                        System.out.println("🔹 Matcher: " + entry.getKey());
//                                        System.out.println("   └ AuthorizationManager: " + entry.getValue());
//                                    }
//                                }
//                            }
//                        }
//                    }
//                } catch (Exception e) {
//                    System.out.println("⚠️ 解析授权匹配器失败：" + e.getMessage());
//                    e.printStackTrace();
//                }
//            }
//        };
//    }
//
////    @Bean
////    public ApplicationRunner printSecurityMatchers(SecurityFilterChain securityFilterChain) {
////        return args -> {
////            System.out.println("🔍 打印 AuthorizationManager 中的所有 matcher:");
////            try {
////                // 1. 获取 AuthorizationManager 字段
////                Field amField = securityFilterChain.getClass().getDeclaredField("authorizationManager");
////                amField.setAccessible(true);
////                Object managerObj = amField.get(securityFilterChain);
////
////                if (managerObj instanceof RequestMatcherDelegatingAuthorizationManager) {
////                    RequestMatcherDelegatingAuthorizationManager manager =
////                            (RequestMatcherDelegatingAuthorizationManager) managerObj;
////
////                    // 2. 通过反射获取 matcher -> AuthorizationManager 的映射（是个 Map）
////                    Field mappingsField = RequestMatcherDelegatingAuthorizationManager.class
////                            .getDeclaredField("mappings");
////                    mappingsField.setAccessible(true);
////                    Object mappings = mappingsField.get(manager);
////
////                    Field mappingsMapField = mappings.getClass().getDeclaredField("mappings");
////                    mappingsMapField.setAccessible(true);
////                    Map<RequestMatcher, AuthorizationManager<RequestAuthorizationContext>> matcherMap =
////                            (Map<RequestMatcher, AuthorizationManager<RequestAuthorizationContext>>) mappingsMapField.get(mappings);
////
////                    // 3. 打印 matcher 列表
////                    matcherMap.forEach((matcher, authManager) -> {
////                        System.out.println("🔹 Matcher: " + matcher);
////                        System.out.println("   └ AuthorizationManager: " + authManager);
////                    });
////
////                } else {
////                    System.out.println("❌ 当前 AuthorizationManager 不是 RequestMatcherDelegatingAuthorizationManager 类型");
////                }
////
////            } catch (Exception e) {
////                System.out.println("⚠️ 反射失败: " + e.getMessage());
////                e.printStackTrace();
////            }
////        };
////    }
//
//}
//
