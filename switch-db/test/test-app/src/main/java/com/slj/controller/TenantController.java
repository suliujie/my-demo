package com.slj.controller;//package com.slj.controller;
//
//import com.slj.com.slj.business.TenantService;
//import com.slj.domain.entity.Tenant;
//import org.springframework.http.RequestEntity;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//import work.myfavs.framework.orm.meta.pagination.Page;
//
//
//@RestController
//@RequestMapping("/tenant")
//public class TenantController {
//  private final TenantService tenantService;
//
//  public TenantController(TenantService userService) {
//    this.tenantService = userService;
//  }
//
//  @RequestMapping(value = "/save", method = RequestMethod.POST)
//  public ResponseEntity<Long> saveTenant(RequestEntity<Tenant> entity) {
//    Tenant tenant = tenantService.saveTenant(entity.getBody());
//    return ResponseEntity.ok().body(tenant.getId());
//  }
//
//  @RequestMapping(value = "/find-by-page")
//  public ResponseEntity<Page<Tenant>> findByPage(){
//
//    return ResponseEntity.ok().body(tenantService.findByPage());
//  }
//}
