package com.hao.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class CommonInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        
        String userAgent = request.getHeader("USER-AGENT").toLowerCase();
        // 是微信浏览器
        if (userAgent.indexOf("micromessenger") > 0) {

            String openId = (String) session.getAttribute("openId");

            if (StringUtils.isBlank(openId)) {


                StringBuffer requestUrl = request.getRequestURL();
                String queryString = request.getQueryString();
                if (queryString != null && !queryString.equals("")) {
                    requestUrl.append("?" + queryString);
                }
                
                // 第一步先获取code，所以不会得到新的openId
                // 第二步根据backUrl再次请求原访问地址，本次会附加第一步返回的code，因此能得到新的openId
//                openId = accessTokenApi.getOpenId(request.getParameter("code"),
//                        requestUrl.toString(), response);
                
                if (!StringUtils.isBlank(openId)) {
                    session.setAttribute("openId", openId);
                } else {
                    return false;
                }
            }

            session.setAttribute("isFromWx", true);

        } else {
            session.setAttribute("isFromWx", false);
        }
        
        String redirectUrl = request.getParameter("redirectUrl");
        session.setAttribute("redirectUrl", redirectUrl);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

}
