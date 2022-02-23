package com.slj.service.impl;//package com.slj.com.slj.business;

import cn.hutool.core.util.StrUtil;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.slj.Init.TenantDataSourceConfig;
import com.slj.mq.MQConstant;
import com.slj.persistence.TenantRepository;
import com.slj.pojo.Tenant;
import com.slj.switchdb.config.DynamicDataSource;
import com.slj.switchdb.tenant.DynamicDataSourceContextHolder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.apache.commons.codec.Charsets;
import org.beetl.core.Script;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import slj.TenantDTO;
import work.myfavs.framework.orm.DB;
import work.myfavs.framework.orm.DBTemplate;
import work.myfavs.framework.orm.JdbcConnFactory;
import work.myfavs.framework.orm.business.BaseService;
import work.myfavs.framework.orm.meta.clause.Sql;
import work.myfavs.framework.orm.meta.pagination.Page;

@Service
public class TenantService extends BaseService {
  private final TenantRepository  tenantRepository;
  private final DynamicDataSource dynamicDataSource;
  private final RabbitTemplate template;
  private final Logger logger= LoggerFactory.getLogger(TenantService.class);
  @Autowired
  private TenantDataSourceConfig config;
  @Resource(name = "primaryDataSource")
  private DataSource dataSource;

  public TenantService(TenantRepository tenantRepository,
      @Qualifier("dynamicDataSource") DynamicDataSource dynamicDataSource,
      RabbitTemplate template) {
    this.tenantRepository = tenantRepository;
    this.dynamicDataSource = dynamicDataSource;
    this.template = template;
  }

  public Tenant getByTenant(String tenant) {
    return tenantRepository.getByField("tenant", tenant);
  }

  @Transactional(rollbackFor = Exception.class)
  public Tenant saveTenant(Tenant tenant) {
    //tenant.setTenant(tenant.getTenant()+"user");
//    Sql sql=new Sql();
//    sql.appendLine("CREATE DATABASE IF NOT EXISTS "+tenant.getTenant()+" DEFAULT CHARSET utf8 COLLATE utf8_general_ci;");

//    List<String> pa =new ArrayList<>();
//    pa.add(tenant.getTenant());
//    sql.setParams(pa);
    tenantRepository.create(tenant);
    //tenantRepository.execute(sql);
    //dynamicDataSource.addDataSource(tenant);
    return tenant;
  }

  @Transactional(rollbackFor = Exception.class)
  public Tenant addTenant(Tenant tenant) throws Exception {
    //tenant.setTenant(tenant.getTenant()+"user");

//    Sql sql=new Sql();
//    sql.appendLine("CREATE DATABASE IF NOT EXISTS "+tenant.getTenant()+" DEFAULT CHARSET utf8 COLLATE utf8_general_ci;");

//    List<String> pa =new ArrayList<>();
//    pa.add(tenant.getTenant());
//    sql.setParams(pa);
    //tenantRepository.create(tenant);
    //tenantRepository.execute(sql);
    //dynamicDataSource.addDataSource(tenant);
    TenantDTO tenantDTO=new TenantDTO();
    tenantDTO.setTenant(tenant.getTenant()+"user");
    tenantDTO.setCreated(tenant.getCreated());
    tenantDTO.setJdbcClass(tenant.getJdbcClass());
    tenantDTO.setJdbcPassword(tenant.getJdbcPassword());
    tenantDTO.setJdbcUrl(tenant.getJdbcUrl());
    tenantDTO.setJdbcUser(tenant.getJdbcUser());
    tenantDTO.setModified(tenant.getModified());
    //addTenant(tenantDTO);
    template.convertAndSend(MQConstant.EXCHANGE,MQConstant.TENANT_ADD_ROUTING_KEY,tenantDTO);
    return tenant;
  }

  @Transactional(rollbackFor = Exception.class)
  public void addTenant(TenantDTO tenant) throws Exception {

    Tenant tenantDTO=new Tenant();
    tenantDTO.setTenant(tenant.getTenant());
    tenantDTO.setCreated(tenant.getCreated());
    tenantDTO.setJdbcClass(tenant.getJdbcClass());
    tenantDTO.setJdbcPassword(tenant.getJdbcPassword());
    tenantDTO.setJdbcUrl(tenant.getJdbcUrl());
    tenantDTO.setJdbcUser(tenant.getJdbcUser());
    tenantDTO.setModified(tenant.getModified());

    //tenant.setTenant(tenant.getTenant()+"user");
    DBTemplate               dbTemplate        = config.buildDbTemplate(dataSource, JdbcConnFactory.class);
    DB           db      = DB.conn(dbTemplate);
        Sql sql=new Sql();
    sql.appendLine("CREATE DATABASE IF NOT EXISTS "+tenantDTO.getTenant()+" DEFAULT CHARSET utf8 COLLATE utf8_general_ci;");

    db.execute(sql);


    DruidDataSource ds = new DruidDataSource();
    ds.setConnectProperties(DynamicDataSource.connectProperties);
    ds.setDriverClassName(tenantDTO.getJdbcClass());
    ds.setUrl(tenantDTO.getJdbcUrl());
    ds.setUsername(tenantDTO.getJdbcUser());
    ds.setPassword(tenantDTO.getJdbcPassword());
    Map<Object, Object> customDataSources =dynamicDataSource.getTargetDataSources();
    if (!customDataSources.containsKey(tenantDTO.getTenant())) {
      customDataSources.put(tenant.getTenant(), ds);
      logger.info("已加载租户库数据源" + tenant.getTenant());
      List<String> strs = getScript();
      DBTemplate dbTemplate2 = config.buildDbTemplate(ds, JdbcConnFactory.class);
      DB db2 = DB.conn(dbTemplate2);
      for (String st : strs) {
        if(st.length()<1){
          continue;
        }
        logger.info("sql语句:"+st);
        db2.execute(new Sql(st));
      }

      dynamicDataSource.setTargetDataSources(customDataSources);
      dynamicDataSource.afterPropertiesSet();
      tenantRepository.create(tenantDTO);
      //int i=1/0;
    }

  }

  public Page<Tenant> findByPage() {
    return tenantRepository.findPage(Tenant.class, new Sql("SELECT * FROM tenant"), true, 1, 10);
  }


  private List<String> getScript() throws Exception {
    URL url = Thread.currentThread().getContextClassLoader().getResource("db/migration/");
    if (url == null) {
      throw new Exception("找不到url " + "db/migration/");
    }
    List<String> sqlScripts = new ArrayList<>();
    if ("file".equalsIgnoreCase(url.getProtocol())) {
      File file = new File(url.getFile());
      if (!file.exists() || !file.isDirectory()) {
        throw new Exception("db/migration/" + " 不存在或者不是一个文件夹");
      }
      List<File> list = Arrays.stream(file.listFiles()).sorted(Comparator.comparing(File::getPath))
          .collect(Collectors.toList());
      for (File f : list) {
        try (InputStream is = new FileInputStream(f);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, Charsets.UTF_8))) {

          sqlScripts.addAll(readScript(br));
        }
      }
    } else if ("jar".equalsIgnoreCase(url.getProtocol())) {
      // jar包形式，用JarEntry获取资源路径
      String jarPath = url.getFile()
          .substring(url.getFile().indexOf(":") + 2, url.getFile().indexOf("!"));
      JarFile jarFile = new JarFile(new File(jarPath));
      List<String> list = jarFile.stream()
          .map(ZipEntry::toString)
          .filter(f -> f.contains("db/migration/"))
          .filter(f -> f.endsWith(".sql"))
          .sorted()
          .collect(Collectors.toList());
      for (String s : list) {
        try (
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(s);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(inputStreamReader)
        ) {
          sqlScripts.addAll(readScript(br));
        }
      }
    } else {
      throw new Exception("未知url协议");
    }
    return sqlScripts;
  }

  private List<String> readScript(BufferedReader br) throws IOException {
    List<String>  sqlScripts = new ArrayList<>();
    StringBuilder sqlScript  = new StringBuilder();
    for (String line = br.readLine(); line != null; line = br.readLine()) {
      String content = line.trim().toLowerCase();
      if (content.isEmpty()) {
        continue;
      }
      if (content.contains(";")) {
        sqlScript.append(line);
        sqlScripts.add(String.valueOf(sqlScript));
        sqlScript = new StringBuilder();
      } else {
        sqlScript.append(line).append("\r\n");
      }
    }
    if (sqlScript.length() > 0) {
      sqlScripts.add(sqlScript.toString());
    }
    return sqlScripts;
  }

  @Transactional(rollbackFor = Exception.class)
  public void updateTenant(Tenant body) throws SQLException, InterruptedException {
    //logger.info(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
    TenantDTO tenantDTO=new TenantDTO();
    tenantDTO.setTenant(body.getTenant());
    tenantDTO.setModified(body.getModified());
    tenantDTO.setCreated(body.getCreated());
    tenantDTO.setJdbcUser(body.getJdbcUser());
    tenantDTO.setJdbcUrl(body.getJdbcUrl());
    tenantDTO.setJdbcPassword(body.getJdbcPassword());
    tenantDTO.setJdbcClass(body.getJdbcClass());
    tenantDTO.setId(body.getId());
    Tenant tt=tenantRepository.getById(body.getId());

    logger.info("fjaskjdjds");
    logger.info("测试分支合并2");
    //body.setTenant("tenant3user");
    //tt.setJdbcPassword(body.getJdbcPassword());
    tt.setJdbcPassword(body.getJdbcPassword());
    tt.setModified(new Date());
    tenantRepository.update(tt);
    //updateTenant(tenantDTO);
    template.convertAndSend(MQConstant.EXCHANGE,MQConstant.TENANT_UPDATE_ROUTING_KEY,tenantDTO);

  }

  @Transactional(rollbackFor = Exception.class)
  public void updateTenant(TenantDTO dto) throws SQLException, InterruptedException {
    Tenant tenant=tenantRepository.getById(dto.getId());
    tenant.setTenant(dto.getTenant()+"user");
    tenant.setJdbcUrl(dto.getJdbcUrl());
    tenant.setJdbcPassword(dto.getJdbcPassword());
    tenant.setJdbcUser(dto.getJdbcUser());
    tenant.setJdbcClass(dto.getJdbcClass());

    //logger.info("连接池初始化前:"+LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    Map<Object, Object> customDataSources =dynamicDataSource.getTargetDataSources();
    //logger.info("连接池初始化中:"+LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    DruidDataSource ds= (DruidDataSource) customDataSources.get(tenant.getTenant());
    //logger.info("连接池初始化完成:"+LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    if(ds!=null){
      //ds.getConnection().close();
      //ds.close();
      //logger.info("数据源是否关闭："+ds.getConnection()+"次数"+ds.getConnectCount());
    }
    if(ds==null){
      ds=new DruidDataSource();
      }
      ds.setConnectProperties(DynamicDataSource.connectProperties);
      ds.setDriverClassName(tenant.getJdbcClass());
      ds.setUrl(tenant.getJdbcUrl());
      ds.setUsername(tenant.getJdbcUser());
      ds.setPassword(tenant.getJdbcPassword());
      //ds.setRemoveAbandonedTimeout(30);
      //ds.setMinEvictableIdleTimeMillis(30001);
      //ds.setTimeBetweenConnectErrorMillis(10000);
      //ds.setRemoveAbandoned(false);
      ds.setMaxActive(10);
      //ds.setMinEvictableIdleTimeMillis(30001);
    //当连接池连接数到达(getNumIdle() < 2) and (getNumActive() > getMaxActive() - 3)  [空闲的连接小于2并且活动的连接大于(最大连接-3)] 时便会启动连接回收
    ds.setRemoveAbandoned(true);
    ds.setRemoveAbandonedTimeout(5);
    ds.setTimeBetweenConnectErrorMillis(10000);
    //ds.setLogAbandoned(true);
    //ds.setRemoveAbandonedTimeoutMillis();
      //ds.setMaxPoolPreparedStatementPerConnectionSize(20);
      //ds.setMinEvictableIdleTimeMillis();
      //ds.getConnection().close();
    customDataSources.put(tenant.getTenant(),ds);
    dynamicDataSource.afterPropertiesSet();
    //ds.close();
    //ds.getConnection();
    //logger.info("开始获取连接:"+LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
   //DruidPooledConnection connection= ds.getConnection();

   //logger.info("连接池："+connection.toString());
   //Connection con=connection.getConnection();
   //logger.info("连接池"+connection.toString()+"连接次数"+ds.getConnectCount()+"池中连接数"+ds.getPoolingCount());
   //logger.info("驱逐数"+ds.e);
    //logger.info(ds.tim());
   //ds.getRemoveAbandonedCount();
   //logger.info("最大活跃连接数"+ds.getMaxActive()+"活跃连接数："+ds.getActiveCount()+"最大空闲的连接数:"+ds.getMaxIdle()+"最小空闲连接数"+ds.getMinIdle()+"空闲连接数"+ds.getPoolingCount());
   //Thread.sleep(10000);
   //connection.close();
   //logger.info("连接池是否关闭"+connection.isClosed());




  }
}
