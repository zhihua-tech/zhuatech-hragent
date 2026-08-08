/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.hragent.controller;
import cn.zhuatech.hragent.agent.AgentRuntime;
import cn.zhuatech.hragent.common.ApiResponse;
import cn.zhuatech.hragent.dto.HrAgentDto.*;
import cn.zhuatech.hragent.service.HrAgentService;
import cn.zhuatech.hragent.service.EmployeeCaseTriageService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController @RequestMapping("/api/shopfloor") @PreAuthorize("hasAnyRole('DOMAIN_USER','ADMIN')")
public class WorkspaceController {
 private final HrAgentService service; private final AgentRuntime runtime; private final EmployeeCaseTriageService domainAgent;
 public WorkspaceController(HrAgentService service,AgentRuntime runtime,EmployeeCaseTriageService domainAgent){this.service=service;this.runtime=runtime;this.domainAgent=domainAgent;}
 @GetMapping("/dashboard") public ApiResponse<Dashboard> dashboard(){return ApiResponse.ok(service.shopfloorDashboard());}
 @PostMapping("/work-orders/{id}/reports") public ApiResponse<ReportResult> report(@PathVariable Long id,@Valid @RequestBody ReportRequest request){return ApiResponse.ok("反馈提交成功",service.report(id,request));}
 @PostMapping("/agent-preview") public ApiResponse<AgentRuntime.AgentResult> preview(@RequestBody Map<String,String> body){return ApiResponse.ok(runtime.run(new AgentRuntime.AgentRequest(body.getOrDefault("objective","分析当前业务事项"),Map.of("mode","demo","approval","required"))));}
 @PostMapping("/case-triage") public ApiResponse<EmployeeCaseTriageService.CaseDecision> domainAction(@Valid @RequestBody EmployeeCaseTriageService.CaseRequest request){return ApiResponse.ok("员工服务分诊完成",domainAgent.triage(request));}
}

