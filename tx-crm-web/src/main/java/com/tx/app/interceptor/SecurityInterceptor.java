package com.tx.app.interceptor;


import com.tx.app.annotation.Security;
import com.tx.app.dao.home.User;
import com.tx.app.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 安全拦截器
 *
 * @author chenqingxia
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {

    private final transient Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断会话
        String no = (String)WebUtils.getSessionAttribute(request, "no");
        if (StringUtils.isEmpty(no)) {
            response.sendRedirect("/login");
            return false;
        }
        // 帐号锁定
        User user = userService.queryByNo(no);
        if (user == null || user.getLocked() == 0) {
            response.sendError(HttpStatus.FORBIDDEN.value());
            return false;
        }
        // 控制访问
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            Security security = method.getMethodAnnotation(Security.class);
            if (security != null) {
                String url = security.url();

                logger.debug("url:{}", url);

                // 获取session中用户的rights
                List<String> rights = (List<String>) WebUtils.getSessionAttribute(request, "rights");
                //List<String> rights = (List<String>) session.getAttribute("rights");
                // 如果用户拥有该权限
                if (rights.contains(url)) {
                    return true;
                }
                // return 403 page
                response.sendError(HttpStatus.FORBIDDEN.value());
                return false;
            }
        }
        return true;
    }

}
