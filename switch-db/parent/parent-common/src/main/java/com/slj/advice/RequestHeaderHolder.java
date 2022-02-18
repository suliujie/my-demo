package com.slj.advice;

import cn.hutool.core.util.StrUtil;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求头持有类
 *
 * @author liufuhong
 * @since 2020-04-17 15:45
 */

public class RequestHeaderHolder {

  private static final ThreadLocal<RequestHeader> threadLocal = ThreadLocal.withInitial(() -> {
    var header = new RequestHeader();
    header.userName = "DEFAULT";
    return header;
  });

  public static long getUserId() {
    return threadLocal.get().getUserId();
  }

  public static void setUserId(long userId) {
    threadLocal.get().setUserId(userId);
  }
  public static String getUserName() {

    if (StrUtil.isBlank(threadLocal.get()
                                   .getUserName())) {
      return "DEFAULT";
    }
    return threadLocal.get()
                      .getUserName();
  }

  public static void setUserName(String userName) {
    threadLocal.get().setUserName(userName);
  }

  public static String getUserType() {
    return threadLocal.get().getUserType();
  }

  public static void setUserType(String userType) {
    threadLocal.get().setUserType(userType);
  }

  public static long getPartnerId() {
    return threadLocal.get().getPartnerId();
  }

  public static void setPartnerId(long partnerId) {
    threadLocal.get().setPartnerId(partnerId);
  }

  public static String getPartnerCode() {
    return threadLocal.get().getPartnerCode();
  }

  public static void setPartnerCode(String partnerCode) {
    threadLocal.get().setPartnerCode(partnerCode);
  }

  public static String getPartnerName() {
    return threadLocal.get().getPartnerName();
  }

  public static void setPartnerName(String partnerName) {
    threadLocal.get().setPartnerName(partnerName);
  }

  public static String getPartnerType() {
    return threadLocal.get().getPartnerType();
  }

  public static void setPartnerType(String partnerType) {
    threadLocal.get().setPartnerType(partnerType);
  }
  public static void setDataSourceId(String dataSourceId){
    threadLocal.get().setDataSourceId(dataSourceId);
  }
  public static String getDataSourceId(){
    return threadLocal.get().getDataSourceId();
  }

  public static void setAllPermission(boolean haveAllPermission) {
    threadLocal.get().setAllPermission(haveAllPermission);
  }

  public static boolean getAllPermission() {
    return threadLocal.get().getAllPermission();
  }

  public static Object get(String key) {
    return threadLocal.get().getAttributes(key);
  }

  public static void put(String key, Object obj) {
    threadLocal.get().putAttributes(key, obj);
  }

  public static void setRequestHeader(RequestHeader requestHeader) {
    threadLocal.set(requestHeader);
  }

  public static RequestHeader getRequestHeader() {
    return threadLocal.get();
  }

  public static ThreadLocal<RequestHeader> getThreadLocal() {
    return threadLocal;
  }

  public static boolean getAdminTag() {
    return threadLocal.get().getAdminTag();
  }

  public static void setAdminTag(boolean adminTag) {
    threadLocal.get().setAdminTag(adminTag);
  }

  public static class RequestHeader implements Serializable {

    private       long                userId;
    private       String              userName;
    private       String              userType;
    private       long                partnerId;
    private       String              partnerCode;
    private       String              partnerName;
    private       String              partnerType;
    private       boolean             allPermission;
    private       boolean             adminTag;
    private String dataSourceId;
    private final Map<String, Object> attributes = new HashMap<>();

    public boolean getAdminTag() {
      return adminTag;
    }

    public void setAdminTag(boolean adminTag) {
      this.adminTag = adminTag;
    }

    public String getUserName() {
      return userName;
    }

    public void setUserName(String userName) {
      this.userName = userName;
    }

    public long getUserId() {
      return userId;
    }

    public void setUserId(long userId) {
      this.userId = userId;
    }

    public long getPartnerId() {
      return partnerId;
    }

    public void setPartnerId(long partnerId) {
      this.partnerId = partnerId;
    }

    public String getPartnerCode() {
      return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
      this.partnerCode = partnerCode;
    }

    public String getPartnerName() {
      return partnerName;
    }

    public void setPartnerName(String partnerName) {
      this.partnerName = partnerName;
    }

    public Object getAttributes(String key) {
      return attributes.get(key);
    }

    public void putAttributes(String key, Object obj) {
      this.attributes.put(key, obj);
    }

    public String getUserType() {
      return userType;
    }

    public void setUserType(String userType) {
      this.userType = userType;
    }

    public String getPartnerType() {
      return partnerType;
    }

    public void setPartnerType(String partnerType) {
      this.partnerType = partnerType;
    }

    public boolean getAllPermission() {
      return allPermission;
    }

    public void setAllPermission(boolean allPermission) {
      this.allPermission = allPermission;
    }

    public String getDataSourceId() {
      return dataSourceId;
    }

    public void setDataSourceId(String dataSourceId) {
      this.dataSourceId = dataSourceId;
    }

    @Override
    public String toString() {
      return "RequestHeader{" +
          "userId=" + userId +
          ", userName='" + userName + '\'' +
          ", userType='" + userType + '\'' +
          ", partnerId=" + partnerId +
          ", partnerCode='" + partnerCode + '\'' +
          ", partnerName='" + partnerName + '\'' +
          ", partnerType='" + partnerType + '\'' +
          ", allPermission=" + allPermission +
          ", attributes=" + attributes +
          '}';
    }
  }

}
