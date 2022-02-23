package com.slj.advice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.slj.switchdb.tenant.DynamicDataSourceContextHolder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import javax.annotation.Resource;
import org.apache.dubbo.rpc.RpcContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import work.myfavs.framework.orm.DB;
import work.myfavs.framework.orm.DBTemplate;
import work.myfavs.framework.orm.repository.BaseRepository;

/**
 * ControllerAdvice
 */
@Aspect
@Order(1)
@Component
public class ControllerAdvice {

  private final static Logger log = LoggerFactory.getLogger(ControllerAdvice.class);
  @Value("${spring.application.name}")
  private String appName;
  @Value("${spring.datasource.url")
  private String url;
  @Resource(name = "dbTemplate")
  private DBTemplate dbTemplate;

  @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping) || "
      + "@annotation(org.springframework.web.bind.annotation.GetMapping) || "
      + "@annotation(org.springframework.web.bind.annotation.PutMapping) ||"
      + "@annotation(org.springframework.web.bind.annotation.DeleteMapping) ||"
      + "@annotation(org.springframework.web.bind.annotation.RequestMapping) ||"
      + "@annotation(org.springframework.web.bind.annotation.PatchMapping)")
  private void pointCutMethodService() {

  }

  @Pointcut("@annotation(org.springframework.amqp.rabbit.annotation.RabbitListener)")
  private void pointCutMethodService2() {

  }
  @Before("pointCutMethodService()")
//  @Around("@annotation(org.springframework.web.bind.annotation.PostMapping)"
//      + "&@annotation(org.springframework.web.bind.annotation.GetMapping)"
//      + "&@annotation(org.springframework.web.bind.annotation.RequestMapping)")
  public void writeRequestHeaderHolder(JoinPoint point) throws NoSuchFieldException {

   //String st = JSON.toJSONString(point.getArgs());

    //String[] argNames = ((MethodSignature)point.getSignature()).getParameterNames();
    Optional.ofNullable(RequestContextHolder.getRequestAttributes())
        .map(ra -> ((ServletRequestAttributes) ra).getRequest())
        .ifPresent(request -> {
          try {
            RequestHeaderHolder.setUserId(Long.parseLong(request.getHeader(RequestHeaderConstant.X_USER_ID)));
          } catch (Exception e) {
            RequestHeaderHolder.setUserId(-1);
          }
          try {
            RequestHeaderHolder.setPartnerId(Long.parseLong(request.getHeader(RequestHeaderConstant.X_PARTNER_ID)));
          } catch (Exception e) {
            RequestHeaderHolder.setPartnerId(-1);
          }
          RequestHeaderHolder.setUserName(parse2UTF8(request.getHeader(RequestHeaderConstant.X_USER_NAME)));
          RequestHeaderHolder.setUserType(parse2UTF8(request.getHeader(RequestHeaderConstant.X_USER_TYPE)));
          RequestHeaderHolder.setPartnerCode(parse2UTF8(request.getHeader(RequestHeaderConstant.X_PARTNER_CODE)));
          RequestHeaderHolder.setPartnerName(parse2UTF8(request.getHeader(RequestHeaderConstant.X_PARTNER_NAME)));
          RequestHeaderHolder.setPartnerType(parse2UTF8(request.getHeader(RequestHeaderConstant.X_PARTNER_TYPE)));
          try {
            RequestHeaderHolder
                .setAllPermission(Boolean.parseBoolean(request.getHeader(RequestHeaderConstant.X_ALL_PERMISSION)));
          } catch (Exception e) {
            RequestHeaderHolder.setAllPermission(false);
          }
          try {
            RequestHeaderHolder.setAdminTag(Boolean.parseBoolean(request.getHeader(RequestHeaderConstant.X_ADMIN_TAG)));
          } catch (Exception e) {
            RequestHeaderHolder.setAdminTag(false);
          }
          RequestHeaderHolder.setDataSourceId(parse2UTF8(request.getHeader("tenant-name"))+appName);
          DynamicDataSourceContextHolder.setDataSource(parse2UTF8(request.getHeader("tenant-name"))+appName);
        });

  }

  @Before("pointCutMethodService2()")
  public void ww(JoinPoint point) throws NoSuchFieldException {
    //point.get
    Object[] ars=point.getArgs();
    String value=null;
    Object     bb=null;

    for(Object ar:ars) {

      //ar.getClass().getField("dataSourceId");
      JSONObject ob = JSONObject.parseObject(JSONObject.toJSONString(ar));
       bb= ob.get("dataSourceId");
       if(bb!=null){
         break;
       }
      //Field[] fs=ar.getClass().getDeclaredFields();

    }
    if(bb==null){
      return;
    }

    //JSONObject jsonObject = JSON.parseObject("{\"timeStamp\":21602756894612,\"status\":0,\"results\":{\"userName\":\"yang20102\",\"userLevel\":\"3\"},\"errorCode\":null,\"errorMessage\":null}");
    // 获取json最外层字符串
    //Object timeStamp = jsonObject.get("timeStamp");
    DynamicDataSourceContextHolder.setDataSource(bb+appName);
    RequestHeaderHolder.setDataSourceId(bb+appName);
  }

//  @Pointcut("@within(org.springframework.stereotype.Repository)")
//  private void point(){
//
//  }
//  @Before("point()")
//  public void aaa(JoinPoint point){
//    BaseRepository repository =(BaseRepository) point.getTarget();
//    Object             value      =RpcContext.getContext().getAttachment("user");
//    repository.setDbTemplate((String) value);
//  }

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
