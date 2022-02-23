package com.slj.business;//package com.slj.com.slj.business;
//
//import java.util.HashMap;
//import java.util.Map;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
///**
// * @author suliujie
// * @since 2022-01-17 12:43
// */
//@Service
//public class Test2Service {
//
//
////@Scheduled(cron = "0/1 * * * * ? ")
//  public void test(){
//    RestTemplate template=new RestTemplate();
//    HttpHeaders headers = new HttpHeaders();
//    HttpMethod        method = HttpMethod.POST;
//  Map<String, Object> userDTO    = new HashMap<>();
//  userDTO.put("id",1492150022916149244L);
//  userDTO.put("username", "bbb");
//  userDTO.put("email", "18");
//  //userDTO.put("dataSourceId","dbtest2");
//    String url="http://127.0.0.1:9527/user/update";
//  //headers.add(HttpHeaders.AUTHORIZATION, "dbtest3");
//  headers.add("tenant-name","tenant1");
//  HttpEntity<Map> formEntity = new HttpEntity<Map>(userDTO, headers);
//    String             s          = template.postForEntity(url,formEntity,String.class).getBody();
//
//  }
//
//  //@Scheduled(cron = "0/1 * * * * ? ")
//  public void test2(){
//    RestTemplate template=new RestTemplate();
//    HttpHeaders headers = new HttpHeaders();
//    HttpMethod method = HttpMethod.POST;
//    Map<String, Object> userDTO    = new HashMap<>();
//    userDTO.put("id",1492150022916149248L);
//    userDTO.put("username", "aaa");
//    userDTO.put("email", "30");
//    //userDTO.put("dataSourceId","dbtest2");
//    String url="http://127.0.0.1:9527/user/update";
//    //headers.add(HttpHeaders.AUTHORIZATION, "dbtest3");
//    headers.add("tenant-name","tenant2");
////headers.
//    //String url="http://127.0.0.1:8082/update";
//    HttpEntity<Map> formEntity = new HttpEntity<Map>(userDTO, headers);
//    String             s          = template.postForEntity(url,formEntity,String.class).getBody();
//
//  }
//
//  //@Scheduled(cron = "0/2 * * * * ? ")
//  public void test3(){
//    RestTemplate template=new RestTemplate();
//    HttpHeaders headers = new HttpHeaders();
//    HttpMethod method = HttpMethod.POST;
//    Map<String, String> userDTO    = new HashMap<>();
////    userDTO.put("userName", "数据库3-啊强");
////    userDTO.put("age", "30");
////    userDTO.put("dataSourceId","dbtest3");
////    //headers.add(HttpHeaders.AUTHORIZATION, "dbtest3");
//    headers.add("tenant-name","tenant1");
////headers.
//    String url="http://127.0.0.1:9527/user/find-by-page";
//    HttpEntity<Map> formEntity = new HttpEntity<Map>(headers);
//    template.exchange(url,HttpMethod.GET,formEntity,Object.class);
//
//  }
//  //@Scheduled(cron = "0/2 * * * * ? ")
//  public void test4(){
//    RestTemplate template=new RestTemplate();
//    HttpHeaders headers = new HttpHeaders();
//    HttpMethod method = HttpMethod.POST;
//    Map<String, String> userDTO    = new HashMap<>();
////    userDTO.put("userName", "数据库3-啊强");
////    userDTO.put("age", "30");
////    userDTO.put("dataSourceId","dbtest3");
////    //headers.add(HttpHeaders.AUTHORIZATION, "dbtest3");
//    headers.add("tenant-name","tenant2");
////headers.
//    String url="http://127.0.0.1:9527/user/find-by-page";
//    HttpEntity<Map> formEntity = new HttpEntity<Map>(headers);
//    template.exchange(url,HttpMethod.GET,formEntity,Object.class);
//
//  }
//  //@Scheduled(cron = "0/2 * * * * ? ")
//  public void test5(){
//    RestTemplate template=new RestTemplate();
//    HttpHeaders headers = new HttpHeaders();
//    HttpMethod method = HttpMethod.POST;
//    Map<String, String> userDTO    = new HashMap<>();
////    userDTO.put("userName", "数据库3-啊强");
////    userDTO.put("age", "30");
////    userDTO.put("dataSourceId","dbtest3");
////    //headers.add(HttpHeaders.AUTHORIZATION, "dbtest3");
//    headers.add("tenant-name","tenant3");
////headers.
//    String url="http://127.0.0.1:9527/user/find-by-page";
//    HttpEntity<Map> formEntity = new HttpEntity<Map>(headers);
//    template.exchange(url,HttpMethod.GET,formEntity,Object.class);
//
//  }
//}
