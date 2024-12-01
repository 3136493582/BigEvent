package org.example.interceptors;

import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.pojo.Result;
import org.example.pojo.User;
import org.example.utils.JwtUtils;
import org.example.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private ThreadLocalUtil threadLocalUtil;
    /**
     * 返回true放行，false拦截，登录拦截器
     * @param request 请求
     * @param response 响应
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String jwt = request.getHeader("Authorization");
        //判断是否有jwt
        if(!StringUtils.hasLength(jwt)){
            Result error=Result.error("未登录");
            String notlogin= JSONObject.toJSONString(error);
            response.setStatus(401);
            response.getWriter().write(notlogin);
            return false;
        }

        try{
            User user = jwtUtils.parseJwt(jwt);
            ThreadLocalUtil.set(user);
        }catch(Exception e){
            Result error=Result.error("令牌解析失败");
            String notlogin= JSONObject.toJSONString(error);
            response.setStatus(401);
            response.getWriter().write(notlogin);
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清空threadlocal
        threadLocalUtil.remove();
    }
}
