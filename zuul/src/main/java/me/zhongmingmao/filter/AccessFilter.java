package me.zhongmingmao.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class AccessFilter extends ZuulFilter {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AccessFilter.class);
    
    @Override
    public String filterType() { // pre / route / post / error /static
        return "pre";
    }
    
    @Override
    public int filterOrder() {
        return 1;
    }
    
    @Override
    public boolean shouldFilter() {
        return true;
    }
    
    @Override
    public Object run() { // 过滤器的具体逻辑
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        LOGGER.info(String.format("send %s request to %s", request.getMethod(), request.getRequestURL()));
        if (StringUtils.isEmpty(request.getParameter("accesstoken"))) {
            String warnMsg = "access token is empty";
            LOGGER.warn(warnMsg);
            currentContext.setSendZuulResponse(false); // Zuul 过滤该请求，不进行路由
            currentContext.setResponseStatusCode(401);
            currentContext.setResponseBody(warnMsg);
            return null;
        }
        LOGGER.warn("access token is ok");
        return null;
    }
}
