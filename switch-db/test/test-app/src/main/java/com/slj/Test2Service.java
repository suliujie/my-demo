package com.slj;

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
public class Test2Service {
@Scheduled(cron = "0/1 * * * * ? ")
  public void update2(){
    RestTemplate template=new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    HttpMethod        method = HttpMethod.POST;
    Map<String, String> userDTO    = new HashMap<>();
    userDTO.put("userName", "数据库3-啊强1");
    userDTO.put("age", "181");
    userDTO.put("dataSourceId","tenant1");
    String url="http://127.0.0.1:8082/update2";
    //headers.add(HttpHeaders.AUTHORIZATION, "dbtest3");
    headers.add("tenant-name","tenant1");
    HttpEntity<Map> formEntity = new HttpEntity<Map>(userDTO, headers);
    String             s          = template.postForEntity(url,formEntity,String.class).getBody();

  }

  @Scheduled(cron = "0/1 * * * * ? ")
  public void update3(){
    RestTemplate template=new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    HttpMethod        method = HttpMethod.POST;
    Map<String, String> userDTO    = new HashMap<>();
    userDTO.put("userName", "数据库3-啊强2");
    userDTO.put("age", "200");
    userDTO.put("dataSourceId","tenant2");
    String url="http://127.0.0.1:8082/update3";
    //headers.add(HttpHeaders.AUTHORIZATION, "dbtest3");
    headers.add("tenant-name","tenant2");
    HttpEntity<Map> formEntity = new HttpEntity<Map>(userDTO, headers);
    String             s          = template.postForEntity(url,formEntity,String.class).getBody();

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
