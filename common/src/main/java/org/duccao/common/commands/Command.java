package org.duccao.common.commands;

import org.duccao.common.exceptions.ApplicationException;

public abstract class Command<T, R> {
  protected void preProcess(T request) {
  }

  protected void postProcess(T request, R response) {
  }

  protected abstract R process(T request) throws Exception;

  public R execute(T request) {
    this.preProcess(request);
    try {
      R response = this.process(request);
      this.postProcess(request, response);
      return response;
    } catch (ApplicationException exception) {
      throw exception;
    } catch (Exception exception) {
      throw new ApplicationException(exception);
    }
  }
}
