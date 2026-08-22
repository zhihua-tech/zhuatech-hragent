/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.hragent.agent;
import org.springframework.stereotype.Component; import java.util.List; import java.util.Map;
/** 企业人力资源智能体工作台运行边界；默认演示执行器不连接真实模型、业务系统或外部通信渠道。 */
public interface AgentRuntime {
 AgentResult run(AgentRequest request);
 record AgentRequest(String objective,Map<String,String> context){}
 record AgentStep(String name,String status,String evidence){}
 record AgentResult(String runtime,String summary,List<AgentStep> steps,Map<String,Object> metrics){}
}
@Component class DemoAgentRuntime implements AgentRuntime {
 public AgentResult run(AgentRequest request){
  return new AgentResult("hr-policy-demo","已完成制度检索与影响分析，涉及敏感人事判断的建议等待授权 HR 确认。",List.of(new AgentStep("制度检索","COMPLETED","引用 4 条现行制度"),new AgentStep("影响分析","COMPLETED","识别员工体验与合规影响"),new AgentStep("人事判断","PENDING","等待授权 HR 复核")),Map.of("evidenceItems",12,"suggestedActions",3,"objectiveLength",request.objective().length()));
 }
}

