package com.slj.advice;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.dubbo.rpc.RpcContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import work.myfavs.framework.orm.DBTemplateContext;
import work.myfavs.framework.orm.repository.BaseRepository;

/**
 * ControllerAdvice
 */
@Aspect
@Component
public class ControllerAdvice2 {
  private        Logger log  =LoggerFactory.getLogger(ControllerAdvice2.class);
  private static Lock                     lock         = new ReentrantLock(false);
  private final  Map<Object, ReentrantLock> boxSaveLocks = Collections.synchronizedMap(new WeakHashMap<>());

  @Pointcut("@within(org.springframework.stereotype.Repository) || "
      + "execution(* work.myfavs.framework.orm.repository.Repository.*(..)) || "
      + "execution(* work.myfavs.framework.orm.repository.Query.*(..)) ")
  private void pointCutClassRepository(){

  }


  //@Around("pointCutClassRepository()")
  public Object aaa(ProceedingJoinPoint point) throws Throwable {

    ReentrantLock l = boxSaveLocks.get(point.getTarget());
    if (l == null) {
      point.getSignature();
      synchronized (point.getTarget()) {
        l = boxSaveLocks.get(point.getTarget());
        if (l == null) {
          l = new ReentrantLock();
          boxSaveLocks.put(point.getTarget(), l);
        }
      }
    }
    Object ob=null;
    try {
      if (!l.tryLock(15, TimeUnit.SECONDS)) {
        throw new Exception();
      }
      BaseRepository repository = (BaseRepository) point.getTarget();

      String value = RequestHeaderHolder.getDataSourceId();
      if (value == null) {
        value = "default";
      }

      //RequestContextHolder.getRequestAttributes().
//    Object             value      =RequestHeaderHolder.get
      log.info("当前dataSourceId" + value);
      repository.setDbTemplate(value);
      ob=point.proceed();
    }finally {
      if (l.isLocked() && l.isHeldByCurrentThread()) {
        l.unlock();
      }
    }
    //lock.lock();
//    Object ob=null;
//    try {
//      BaseRepository repository = (BaseRepository) point.getTarget();
//
//      Object value = RequestHeaderHolder.getDataSourceId();
//      if (value == null) {
//        value = "default";
//      }
//
//      //RequestContextHolder.getRequestAttributes().
////    Object             value      =RequestHeaderHolder.get
//      log.info("当前dataSourceId" + value);
//      repository.setDbTemplate((String) value);
//      ob=point.proceed();
//    }catch (Exception e){
//      e.printStackTrace();
//    }finally {
//      //lock.unlock();
//    }
    return ob;
  }

  /**
   * 转换ISO字符串为UTF-8
   *
   * @param isoStr ISO字符串
   * @return UTF-8字符串
   */
  private String parse2UTF8(String isoStr) {
    if (isoStr == null || isoStr.length() == 0) {
      return "";
    }
    return new String(isoStr.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
  }

}
