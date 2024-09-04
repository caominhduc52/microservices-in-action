package org.duccao.licensingservice.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.duccao.licensingservice.utils.UserContext;
import org.duccao.licensingservice.utils.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Description of the class goes here.
 *
 * @author Duc Cao
 * @version 1.0
 * @since 9/4/2024
 */
@Component
public class UserContextFilter implements Filter {

  private static final Logger logger = LoggerFactory.getLogger(UserContextFilter.class);

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
    throws IOException, ServletException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

    UserContext context = UserContextHolder.getContext();
    context.setCorrelationId(httpServletRequest.getHeader(UserContext.CORRELATION_ID));
    context.setUserId(httpServletRequest.getHeader(UserContext.USER_ID));
    context.setOrganizationId(httpServletRequest.getHeader(UserContext.ORGANIZATION_ID));
    context.setAuthToken(httpServletRequest.getHeader(UserContext.AUTH_TOKEN));

    logger.debug("UserContextFilter Correlation id: {}", context.getCorrelationId());

    filterChain.doFilter(servletRequest, servletResponse);
  }
}
