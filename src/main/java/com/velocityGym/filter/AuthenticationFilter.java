//package com.velocityGym.filter;
//
//import jakarta.servlet.Filter;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.FilterConfig;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//import com.velocityGym.util.CookieUtil;
//import com.velocityGym.util.SessionUtil;
//
//@WebFilter(asyncSupported = true, urlPatterns = "/*")
//public class AuthenticationFilter implements Filter {
//
//    private static final String LOGIN = "/login";
//    private static final String SIGNUP = "/signup";
//    private static final String LOGOUT = "/logout";
//    private static final String HOME = "/home";
//    private static final String ROOT = "/";
//    private static final String DASHBOARD = "/dashboard";
//    private static final String ABOUT = "/about";
//    private static final String CONTACT = "/contact";
//    private static final String PROFILE = "/profile";
//    private static final String PRODUCT = "/product";
//    private static final String EDITPROFILE = "/editProfile";
//    private static final String EDITPRODUCT = "/EditProduct";
//    private static final String CUSTOMERDETAILS = "/CustomerDetails";
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {}
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;
//
//        String uri = req.getRequestURI();
//
//        // Allow static resources
//        if (uri.endsWith(".png") || uri.endsWith(".jpg") || uri.endsWith(".jpeg")
//                || uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".woff")
//                || uri.endsWith(".woff2") || uri.endsWith(".ttf")) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        String userRole = CookieUtil.getCookie(req, "role") != null
//                ? CookieUtil.getCookie(req, "role").getValue()
//                : null;
//
//        if ("admin".equals(userRole)) {
//            if (uri.endsWith(LOGIN) || uri.endsWith(SIGNUP)) {
//                res.sendRedirect(req.getContextPath() + DASHBOARD);
//            } else if (
//                    uri.endsWith(DASHBOARD) || uri.endsWith(HOME) || uri.endsWith(ROOT)
//                    || uri.endsWith(ABOUT) || uri.endsWith(CONTACT)
//                    || uri.endsWith(PROFILE) || uri.endsWith(PRODUCT) || uri.endsWith(EDITPROFILE)
//                    || uri.endsWith(EDITPRODUCT) || uri.endsWith(CUSTOMERDETAILS)
//                    || uri.endsWith(LOGOUT)
//            ) {
//                chain.doFilter(request, response);
//            } else {
//                res.sendRedirect(req.getContextPath() + DASHBOARD);
//            }
//        } else if ("customer".equals(userRole)) {
//            if (uri.endsWith(LOGIN) || uri.endsWith(SIGNUP)) {
//                res.sendRedirect(req.getContextPath() + HOME);
//            } else if (
//                    uri.endsWith(HOME) || uri.endsWith(ROOT) || uri.endsWith(ABOUT)
//                    || uri.endsWith(CONTACT) || uri.endsWith(PROFILE)
//                    || uri.endsWith(PRODUCT) || uri.endsWith(EDITPROFILE)
//                    || uri.endsWith(LOGOUT)
//            ) {
//                chain.doFilter(request, response);
//            } else if (uri.endsWith(DASHBOARD) || uri.endsWith(EDITPRODUCT) || uri.endsWith(CUSTOMERDETAILS)) {
//                res.sendRedirect(req.getContextPath() + HOME);
//            } else {
//                res.sendRedirect(req.getContextPath() + HOME);
//            }
//        } else {
//            // Not logged in
//            if (uri.endsWith(LOGIN) || uri.endsWith(SIGNUP) || uri.endsWith(HOME)
//                    || uri.endsWith(ROOT) || uri.endsWith(LOGOUT)) {
//                chain.doFilter(request, response);
//            } else {
//                res.sendRedirect(req.getContextPath() + LOGIN);
//            }
//        }
//    }
//
//    @Override
//    public void destroy() {}
//}
