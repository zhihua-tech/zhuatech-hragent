/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.hragent.service;
import jakarta.validation.constraints.*; import org.springframework.stereotype.Service; import java.util.*;
/** 对员工服务事项做透明分诊；不生成自动录用、晋升、调薪或淘汰决定。 */
@Service public class EmployeeCaseTriageService {
 public record CaseRequest(@NotBlank String topic,@Min(0) int policyReferences,@Min(0) int evidenceItems,boolean sensitivePersonalData,boolean employmentDecision){}
 public record CaseDecision(int riskScore,String route,boolean humanApprovalRequired,List<String> checks,List<String> limitations){}
 public CaseDecision triage(CaseRequest r){int score=Math.min(100,(r.sensitivePersonalData()?35:0)+(r.employmentDecision()?45:0)+(r.policyReferences()==0?15:0)+(r.evidenceItems()<2?10:0));boolean human=r.sensitivePersonalData()||r.employmentDecision()||score>=40;List<String> checks=new ArrayList<>();checks.add("核验制度版本与适用组织");checks.add("仅使用完成当前事项所需的员工字段");if(r.employmentDecision())checks.add("由授权 HR 与业务负责人共同复核");return new CaseDecision(score,human?"HUMAN_REVIEW":"ASSISTED_SERVICE",human,checks,List.of("结果只用于辅助沟通","不得据此自动作出人事决定"));}}

