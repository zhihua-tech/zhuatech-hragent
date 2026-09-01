# 企业级 HR 员工案件决策治理

`POST /api/enterprise/hragent/employee-case-decision-release` 检查制度依据、最小知悉权限、受保护属性、证据、人工决策、高风险复核、员工告知、申诉和保留策略，返回 `RELEASE / HR_REVIEW / BLOCKED`。

AI 仅用于辅助案件分流与资料整理，不应自动作出解雇、处分等高影响决定。
