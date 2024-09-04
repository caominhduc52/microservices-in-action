package org.duccao.licensingservice.utils;

/**
 * Description of the class goes here.
 *
 * @author Duc Cao
 * @version 1.0
 * @since 9/3/2024
 */
public class UserContextHolder {

  private static final ThreadLocal<UserContext> userContextHolder = new ThreadLocal<>();

  public static UserContext getContext() {
    UserContext userContext = userContextHolder.get();
    if (userContext == null) {
      userContext = createEmptyContext();
      userContextHolder.set(userContext);
    }
    return userContextHolder.get();
  }

  public static void setUserContext(UserContext context) {
    if (context == null) {
      throw new NullPointerException("UserContext is null");
    }
    userContextHolder.set(context);
  }

  private static UserContext createEmptyContext() {
    return new UserContext();
  }
}
