# flowable

flowable 是个 Java 的工作流引擎，本项目是演示和 Spring Boot 集成，参考官网文档

## 运行

克隆项目
```bash
git clone https://github.com/yingw/flowable.git

cd flowable && ./mvnw spring-boot:run
```

## 测试

bash 运行
```bash
# 创建流程
curl http://localhost:8080/process -X POST -H "Content-Type:application/json" -d '{"assignee" : "jbarrez"}'

# 查询个人任务
curl http://localhost:8080/tasks?assignee=1
```

返回
```json
[{"id":"216559a8-f139-11e8-ba39-507b9d59bcf8","name":"my task"},{"id":"e413b7e0-f138-11e8-ba39-507b9d59bcf8","name":"my task"}]
```

访问 H2 数据库的 web 管理端 http://localhost:8080/h2-console

连接信息：
- URL: `jdbc:h2:mem:flowable`
- User Name: `sa`
- Password: 空

常用表定义可以查看 Activiti 的[文档说明](https://blog.csdn.net/hj7jay/article/details/51302829)

如：
```sql
-- 流程定义
SELECT * FROM ACT_RE_PROCDEF;
-- 任务实例
SELECT * FROM ACT_RU_TASK;
-- 任务历史
SELECT * FROM ACT_HI_TASKINST;
-- 流程历史
SELECT * FROM ACT_HI_PROCINST;
```