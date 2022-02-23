package com.slj.controller;

import static com.slj.pojo.DataSource.META.COLUMS.url;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author suliujie
 * @since 2022-01-17 12:43
 */
@Service
public class User2Service {

  @Scheduled(cron = "0/3 * * * * ? ")
  public void updateTemplate(){
    RestTemplate template=new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    //HttpMethod method = HttpMethod.POST;
    Map<String,Object> tenant=new HashMap<>();
    tenant.put("id",1495970513401548800L);
    tenant.put("jdbcPassword","Dev9527`");
    tenant.put("jdbcUser","root");
    tenant.put("jdbcUrl","jdbc:mysql://192.168.8.246:3306/tenant3user?allowPublicKeyRetrieval=true&useUnicode=true&useServerPrepStmts=false&rewriteBatchedStatements=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8");
    tenant.put("jdbcClass","com.mysql.cj.jdbc.Driver");
    tenant.put("tenant","tenant3");
    tenant.put("modified","2022-02-23T00:00:00.0000");
    tenant.put("created","2022-02-23T00:00:00.0000");
    HttpEntity<Map> formEntity = new HttpEntity<Map>(tenant, headers);
    String url="http://127.0.0.1:8081/tenant/updateT";
    String             s          = template.postForEntity(url,formEntity,String.class).getBody();

    //headers.add("tenant-name","tenant1");

  }

  //@Scheduled(cron = "0/1 * * * * ? ")
  public void test0(){
    RestTemplate template=new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    HttpMethod method = HttpMethod.POST;
    Map<String, String> userDTO    = new HashMap<>();
//    userDTO.put("userName", "数据库3-啊强");
//    userDTO.put("age", "30");
//    userDTO.put("dataSourceId","dbtest3");
//    //headers.add(HttpHeaders.AUTHORIZATION, "dbtest3");
    headers.add("tenant-name","tenant1");
//headers.
    String url="http://127.0.0.1:8081/test";
    HttpEntity<Map> formEntity = new HttpEntity<Map>(headers);
    template.exchange(url,HttpMethod.GET,formEntity,Object.class);

  }

  //@Scheduled(cron = "0/1 * * * * ? ")
  public void test6(){
    RestTemplate template=new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    HttpMethod method = HttpMethod.POST;
    Map<String, String> userDTO    = new HashMap<>();
//    userDTO.put("userName", "数据库3-啊强");
//    userDTO.put("age", "30");
//    userDTO.put("dataSourceId","dbtest3");
//    //headers.add(HttpHeaders.AUTHORIZATION, "dbtest3");
    headers.add("tenant-name","tenant2");
//headers.
    String url="http://127.0.0.1:8081/test";
    HttpEntity<Map> formEntity = new HttpEntity<Map>(headers);
    template.exchange(url,HttpMethod.GET,formEntity,Object.class);

  }
//@Scheduled(cron = "0/1 * * * * ? ")
  public void test(){
    RestTemplate template=new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    HttpMethod        method = HttpMethod.POST;
  Map<String, String> userDTO    = new HashMap<>();
  userDTO.put("userName", "数据库3-啊强1");
  userDTO.put("age", "181");
  //userDTO.put("dataSourceId","dbtest2");
    String url="http://127.0.0.1:8082/update";
  //headers.add(HttpHeaders.AUTHORIZATION, "dbtest3");
  headers.add("tenant-name","tenant1");
  HttpEntity<Map> formEntity = new HttpEntity<Map>(userDTO, headers);
    String             s          = template.postForEntity(url,formEntity,String.class).getBody();

  }

  //@Scheduled(cron = "0/1 * * * * ? ")
  public void test2(){
    RestTemplate template=new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    HttpMethod method = HttpMethod.POST;
    Map<String, String> userDTO    = new HashMap<>();
    userDTO.put("userName", "数据库3-啊强");
    userDTO.put("age", "30");
    userDTO.put("dataSourceId","dbtest3");
    //headers.add(HttpHeaders.AUTHORIZATION, "dbtest3");
    headers.add("token","dbtest3");
//headers.
    String url="http://127.0.0.1:8082/update";
    HttpEntity<Map> formEntity = new HttpEntity<Map>(userDTO, headers);
    String             s          = template.postForEntity(url,formEntity,String.class).getBody();

  }

  //@Scheduled(cron = "0/2 * * * * ? ")
  public void test3(){
    RestTemplate template=new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    HttpMethod method = HttpMethod.POST;
    Map<String, String> userDTO    = new HashMap<>();
//    userDTO.put("userName", "数据库3-啊强");
//    userDTO.put("age", "30");
//    userDTO.put("dataSourceId","dbtest3");
//    //headers.add(HttpHeaders.AUTHORIZATION, "dbtest3");
    headers.add("token","dbtest3");
//headers.
    String url="http://127.0.0.1:8082/test";
    HttpEntity<Map> formEntity = new HttpEntity<Map>(headers);
    template.exchange(url,HttpMethod.GET,formEntity,Object.class);

  }
  //@Scheduled(cron = "0/2 * * * * ? ")
  public void test4(){
    RestTemplate template=new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    HttpMethod method = HttpMethod.POST;
    Map<String, String> userDTO    = new HashMap<>();
//    userDTO.put("userName", "数据库3-啊强");
//    userDTO.put("age", "30");
//    userDTO.put("dataSourceId","dbtest3");
//    //headers.add(HttpHeaders.AUTHORIZATION, "dbtest3");
    headers.add("token","dbtest2");
//headers.
    String url="http://127.0.0.1:8082/test";
    HttpEntity<Map> formEntity = new HttpEntity<Map>(headers);
    template.exchange(url,HttpMethod.GET,formEntity,Object.class);

  }
}
