package com.jony.shop.spring.commons.context;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component(value = "springContext")
public final class SpringContext implements ApplicationContextAware, DisposableBean {

   private static final Logger logger = LoggerFactory.getLogger(SpringContext.class);

   private static ApplicationContext applicationContext;

   public static ApplicationContext getApplicationContext() {
      assertContextInjected();
      return applicationContext;
   }

   public void destroy() throws Exception {
      logger.debug("清除 SpringContext 中的 ApplicationContext: {}", applicationContext);
      SpringContext.applicationContext=null;
   }

   public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
      SpringContext.applicationContext=applicationContext;
   }

   public static <T> T getBean(String beanid){
      assertContextInjected();
      return (T)applicationContext.getBean(beanid);
   }

   public static <T> T getBean(Class<T> clazz) {
      assertContextInjected();
      return applicationContext.getBean(clazz);
   }

   /**
    * 断言 Context 已经注入
    */
   private static void assertContextInjected() {
      Validate.validState(applicationContext != null, "applicationContext 属性未注入，请在 spring-context.xml 配置中定义 SpringContext");
   }
}
