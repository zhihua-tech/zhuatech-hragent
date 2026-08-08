/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.hragent.config;
import cn.zhuatech.hragent.model.*; import cn.zhuatech.hragent.repository.*; import org.springframework.boot.CommandLineRunner; import org.springframework.context.annotation.*; import org.springframework.security.crypto.password.PasswordEncoder; import java.time.LocalDate; import java.util.List;
@Configuration public class DataInitializer {
 @Bean CommandLineRunner seed(OperatingUnitRepository units,WorkRecordRepository records,ResourceRegisterRepository resources,ReviewRecordRepository reviews,UserRepository users,PasswordEncoder encoder){return args->{if(units.count()>0)return;
  OperatingUnit first=units.save(new OperatingUnit("HR-EAST","华东 HRBP 组","人力资源中心",1800)),second=units.save(new OperatingUnit("HR-TALENT","总部人才组","组织发展中心",1200)),third=units.save(new OperatingUnit("HR-ER","员工关系组","人力资源中心",800));
  WorkRecord a=records.save(new WorkRecord("HRC-260808-031","EMP-E1026","新任主管试用期辅导建议",first,8,5,2,LocalDate.now().plusDays(2),WorkRecord.Status.RELEASED,"POLICY-V6")); WorkRecord b=records.save(new WorkRecord("HRC-260808-024","EMP-R0815","研发岗位内部流动咨询",second,6,6,0,LocalDate.now().plusDays(0),WorkRecord.Status.COMPLETED,"POLICY-V5")); WorkRecord c=records.save(new WorkRecord("HRC-260808-038","EMP-S0421","灵活办公制度适用确认",third,7,3,1,LocalDate.now().plusDays(3),WorkRecord.Status.RUNNING,"POLICY-V4"));
  resources.saveAll(List.of(new ResourceRegister("KNOW-HR-01","人力制度知识库",third,ResourceRegister.Status.RUNNING,96),new ResourceRegister("DATA-HRIS-02","HRIS 授权只读视图",first,ResourceRegister.Status.RUNNING,94),new ResourceRegister("GUARD-PII-03","员工隐私审查器",second,ResourceRegister.Status.ALARM,78)));
  reviews.saveAll(List.of(new ReviewRecord("REV-HR-0828",a,"偏差评测",18,2,ReviewRecord.Result.PENDING,"沈闻"),new ReviewRecord("REV-HR-0817",b,"引用准确性",12,0,ReviewRecord.Result.PASSED,"陈予安"),new ReviewRecord("REV-HR-0739",c,"隐私合规",16,2,ReviewRecord.Result.FAILED,"秦简")));
  String demo=encoder.encode("Demo@2026");
  users.saveAll(List.of(new UserAccount("operator",demo,"陈予安",UserAccount.Role.DOMAIN_USER,"HR-EAST"),new UserAccount("planner",demo,"沈闻",UserAccount.Role.DOMAIN_OPERATOR,null),new UserAccount("quality",demo,"评测负责人",UserAccount.Role.QUALITY,null),new UserAccount("admin",encoder.encode("ZhuaTech@2026"),"系统管理员",UserAccount.Role.ADMIN,null)));
 };}}

