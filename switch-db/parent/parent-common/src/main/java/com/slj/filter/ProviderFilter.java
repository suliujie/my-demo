package com.slj.filter;

import cn.hutool.core.util.ObjectUtil;
import com.slj.advice.Constant;
import com.slj.advice.RequestHeaderHolder;
import com.slj.advice.RequestHeaderHolder.RequestHeader;
import com.slj.switchdb.tenant.DynamicDataSourceContextHolder;
import javax.servlet.ServletContext;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@Activate(
    group = {"provider"},
    order = -10000
)
@Component
public class ProviderFilter implements Filter {

//  @Value("${spring.application.name}")
//  private String appName;

  //private final Logger log = LoggerFactory.getLogger(RequestHeaderProviderFilter.class);

  @Override
  public Result invoke(Invoker<?> invoker, Invocation inv) throws RpcException {
    RequestHeader request = (RequestHeader) RpcContext.getContext().getObjectAttachment(Constant.BEEX_REQUEST);
    if (ObjectUtil.isNotNull(request)) {
      RpcContext.getContext().setObjectAttachment(Constant.BEEX_REQUEST, request);
    }
    RequestHeaderHolder.getThreadLocal().set(request);
    //ServletContext        sc  =;
    //WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(sc);
    //testUrl=cxt.getEnvironment().getProperty("test.testUrl");

    DynamicDataSourceContextHolder.setDataSource(request.getDataSourceId());
    //log.debug("Set request header from Dubbo context: {}", request);
    return invoker.invoke(inv);
  }
}
