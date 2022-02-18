package com.slj.filter;

import com.slj.advice.Constant;
import com.slj.advice.RequestHeaderHolder;
import com.slj.advice.RequestHeaderHolder.RequestHeader;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcException;

@Activate(
    group = {"consumer"},
    order = -10000
)
public class ConsumerFilter implements Filter {


  @Override
  public Result invoke(Invoker<?> invoker, Invocation inv) throws RpcException {
    RequestHeader requestHeader = (RequestHeader) RpcContext.getContext().getObjectAttachment(Constant.BEEX_REQUEST);
    if (requestHeader == null) {
      RequestHeader request = RequestHeaderHolder.getThreadLocal().get();
      RpcContext.getContext().setObjectAttachment(Constant.BEEX_REQUEST, request);
      String ten=RequestHeaderHolder.getThreadLocal().get().getDataSourceId();
      //DynamicDataSourceContextHolder.setDataSource(ten);
      //log.debug("Set request header to Dubbo context: {}", request);
    }
    return invoker.invoke(inv);
  }
}
