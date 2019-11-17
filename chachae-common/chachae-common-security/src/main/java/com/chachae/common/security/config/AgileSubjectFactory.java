package com.chachae.common.security.config;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.SubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;

/**
 * 禁用session
 *
 * @author chachae
 * @date 2019/11/15 21:37
 */
public class AgileSubjectFactory extends DefaultWebSubjectFactory {

  @Override
  public Subject createSubject(SubjectContext context) {
    context.setSessionCreationEnabled(false);
    return super.createSubject(context);
  }
}
