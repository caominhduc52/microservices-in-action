//package org.duccao.licensingservice.interceptors;
//
//import java.io.IOException;
//import lombok.extern.slf4j.Slf4j;
//import org.duccao.licensingservice.utils.UserContext;
//import org.duccao.licensingservice.utils.UserContextHolder;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpRequest;
//import org.springframework.http.client.ClientHttpRequestExecution;
//import org.springframework.http.client.ClientHttpRequestInterceptor;
//import org.springframework.http.client.ClientHttpResponse;
//
///**
// * Description of the class goes here.
// *
// * @author Duc Cao
// * @version 1.0
// * @since 9/4/2024
// */
//
//@Slf4j
//public class UserContextInterceptor implements ClientHttpRequestInterceptor {
//
//  @Override
//  public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
//    throws IOException {
//    HttpHeaders headers = request.getHeaders();
//    headers.add(UserContext.CORRELATION_ID, UserContextHolder.getContext().getCorrelationId());
//    headers.add(UserContext.AUTH_TOKEN, UserContextHolder.getContext().getAuthToken());
//    return execution.execute(request, body);
//  }
//}
