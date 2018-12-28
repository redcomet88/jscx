## 中层干部民主评测

### 代码编写

- [x] 新建t_evaluate_record 表，增加t_menu表数据
- [x] 采用FLASH方式来显示述职报告
- [x] 点击姓名可以打开窗口，显示述职报告；点击“+”符号可以显示详细信息（头像，姓名，职务，述职报告查看按钮）
- [ ] 登录的时候判断是否是中层干部(user.gwdj)，如果不是，不能登录
- [ ] 密码用身份证来登录，未改变设计，董老师修改了数据库
- [ ] 在评测页面，增加一个全选按钮。在Filter里做文章，根据不同的模式来调用不同的service方法。



### 数据库设计
#### 1.评测表
+ ID
+ 评测用户
+ 被评测人工号 
+ 政治思想
+ 业务知识
+ 工作态度
+ 完成工作
+ 遵纪守法
+ 综合评价
+ 任职建议
+ 是否完成评价
+ 评价年
+ flag1
+ flag2
+ flag3
+ column1
+ column2
+ column3

#### 2.SQL
CREATE TABLE
    t_evaluate_record
    (
        id INT(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
        dah VARCHAR(50) NOT NULL COMMENT '评测人账号',
        eva_dah VARCHAR(50) NOT NULL COMMENT '被评测人工号',
        zzsx DOUBLE COMMENT '政治思想',
        ywzs DOUBLE COMMENT '业务知识',
        gztd DOUBLE COMMENT '工作态度',
        wcgz DOUBLE COMMENT '完成工作',
        zjsf DOUBLE COMMENT '遵纪守法',
        zhpj DOUBLE COMMENT '综合评价',
        rzjy DOUBLE COMMENT '任职建议',
        finished VARCHAR(2) COMMENT '是否完成评价',
        eva_year VARCHAR(8) COMMENT '评价年',
        flag1 VARCHAR(10)  COMMENT 'flag1',
        flag2 VARCHAR(10)  COMMENT 'flag2',
        flag3 VARCHAR(10)  COMMENT 'flag3',
        column1 VARCHAR(255)  COMMENT '备用1',
        column2 VARCHAR(255)  COMMENT '备用2',
        column3 VARCHAR(255)  COMMENT '备用3',
        PRIMARY KEY (id),
        CONSTRAINT code UNIQUE (dah,eva_dah,eva_year，finished)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='中层干部民主评测记录表';
    
INSERT INTO t_menu (id, sort, level, p_id, name, url, action, icon) VALUES ('menu008000', 8000, 1, '-1', '中层干部评测', '#', '#', 'fa fa-desktop');
INSERT INTO t_menu (id, sort, level, p_id, name, url, action, icon) VALUES ('menu008001', 8001, 2, 'menu008000', '评测页面', 'showEnluateInfo.do', 'showEnluateInfo', 'fab fa-telegram');
INSERT INTO t_dept_info (jgh, jgmc, sjjg, sfydzh, flag, num, jgjp) VALUES ('001', '苏州市职业大学', '000', null, '0', 1, null);

INSERT INTO t_menu (id, sort, level, p_id, name, url, action, icon) VALUES ('menu008002', 8002, 2, 'menu008000', '中层干部述职查看', 'showReportInfo.do', 'showReportInfo.do', 'fa fa-comments');
INSERT INTO t_menu (id, sort, level, p_id, name, url, action, icon) VALUES ('menu008003', 8003, 2, 'menu008000', '中层干部评测状态', 'showEvaStatus.do', 'showEvaStatus.do', 'fab fa-telegram');

INSERT INTO t_menu_function (id, menu_id, function_name, function_action) VALUES ('func008003', 'menu008002', '中层干部评测', '#');
INSERT INTO t_menu_function (id, menu_id, function_name, function_action) VALUES ('func008004', 'menu008003', '中层干部评测', '#');