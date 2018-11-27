package jssvc.base.enums;

public enum ActionType {

    login("登录"), ssoLogin("统一登录"), reLogin("重新登录"), logout("注销"), ssomslogin("统一用户单点登录"), sso("统一用户重定向方法"),

    user_addUser("创建员工"), user_updateUser("更新/启用员工信息"), user_deleteUser("停用员工"), user_resetPwd("密码重置"),

    user_updatePwd("修改密码"), jg_saveJgData("增/删/改机构"), user_batchAddPinyin("批量添加拼音"), user_addPinyin("添加单个用户拼音"),

    role_saveRole("创建/修改角色"), role_deleteRole("删除角色"), role_saveUserRole("分配角色"), role_saveDataAuthority("分配数据权限"),

    wpType_addWpType("物品品牌型号新增"), wpType_updateWpType("物品品牌型号修改"), wpType_deleteWpType("物品品牌型号删除"),

    gysInfo_addGysInfo("供应商新增"), gysInfo_updateGysInfo("供应商修改"), gysInfo_deleteGysInfo("供应商删除"), gysWp_saveGysWp("供应商物品型号关联保存"),

    showCreditIndex("显示诚信指标"), cg_exportApplyRecord("导出采购申请"), cg_getCgBanks("采购后评价"), cg_exportCgApplyRecordUndoneApprove("导出未审批的采购"),

    cg_cgInfoList(""), user_getUsers(""), wpType_getWpTypeList(""), wpType_getWpTypeListNew(""),

    gysInfo_getGysInfoList(""), log_listLog(""), getJghList(""), dcdb_dcdbInfoList(""), addDcdbInfoApply("添加督查督办"), showDcdbInfoApply("查看督查督办"),

    dcdb_dcdbInfoUndoneList(""), suggest_suggestInfoList(""), addSuggestInfoApply("添加合理化建议"),

    lc_lcProductAdd("新增理财产品申请"), lc_lcInfoList(""), addLcInfoApply("添加理财产品"), ssomsUndoneTask("统一用户代办"),

    rkInfo_addRkInfo("入库新增"),

    uploadImage("图片上传"), rkInfo_updateRkInfo("入库更新"), rkInfo_deleteRkInfo("入库单删除"), rk_approveRk("入库审批"),

    ck_addCkInfo("物品领用信息新增"), ck_updateCkInfo("物品领用信息修改"), ck_deleteCkInfo("物品领用单删除"), ck_approveCk("物品领用审批"),

    ck_updateCkConfirmInfo("物品出库"), pbInfo_updatePbInfo("排班信息修改"), jjbInfo_addJbInfo("交班信息新增"),

    jjbInfo_updateJjbInfo("交接班信息修改"), ck_printCk("出库物品领用单打印"), thbInfo_addThbInfo("替换班信息新增"),

    zcdj_confirmWp("确认物品到达"), zcwb_addWbInfo("添加维保商信息"), zcwb_deleteWbInfo("删除维保商信息"),

    zcwb_updateWBInfo("修改维保商信息"), zcdj_getZcdjInfoList("获取资产登记信息"), zcdj_updateZcdjInfo("修改资产登记信息"),

    thbInfo_agreeThbInfo("同意替换班"), thbInfo_disagreeThbInfo("不同意替换班"), thbInfo_deleteThbInfo("删除替换班申请"),

    uploadZcdjInfo("批量导入资产登记"), exportTemplates("导出资产登记模板"), exportWpInfoList("导出仓库库存"), exportRkCkList("导出入库出库"), purpose_addPurpose("创建用途"),

    purpose_updatePurpose("修改用途"), purpose_deletePurpose("删除用途"), pro_addProblem("问题新增"), pro_updateProblem("更新问题"),

    pro_deleteProblem("删除问题"), showProblemSearch("获取问题查询页面"), uploadKindEditor("富文本编辑器上传"),

    pro_dealWithProblem("待处理问题处理"), problem_addProblemType("新增问题分类"), problem_updateProblemType("编辑问题分类"),

    problem_deleteProblemType("删除问题分类"), trans_addTrans("新增交易"), trans_updateTrans("编辑交易"), trans_deleteTrans("删除交易"),

    trans_exportTransTemplates("导出交易数据"), trans_importTransList("导入交易数据"), rkInfo_exportWpInfoList("导出仓库库存"),

    rkInfo_exportWplyList("导出物品领用"), rkInfo_exportRkCkList("导出入库出库"), event_createEvent("创建一个新的事件"),

    event_uploadAttachments("附件上传"), event_updateEvent("更新事件"), event_cancelEventService("撤销事件"), event_rollback("事件服务台回退"),

    event_setEventCharger("分配事件负责人"), event_closeEvent("关闭事件"), event_rollbackEvent("处理事件——回退"), event_dealWithEvent("处理事件——解决方案"),

    ajax_deleteAttachment("删除附件"), event_createEventChildChange("创建当前事件的变更"), event_createEventChildProblem("创建当前事件的关联问题"),

    event_changeChargeUser("改派事件负责人"), event_updateEventAttachmentType("更新附件类型"), event_setEventOverCharger("事件直接解决"), uploadAttachTemplet("上传附件模板文件"),

    event_rollbackToInitByCharger("负责人回退到事件登记"), event_createEProblem("创建问题"), updateEProblem("更新问题"),

    event_setEProblemApprover("指定问题的业务审批人"), event_probBusiAprrove("业务审批登记问题"), event_probTechAprrove("科技部审批问题"),

    event_dealwithEProblem("处理问题"), event_testProblem("问题解决方案测试"), event_createEProbChildChange("创建问题的子变更"),

    event_setTestUser("测试经理指定测试人员"), event_createEChange("创建变更"), event_updateEChange("修改变更"), change_setChangeCharger("确认审批人员"),

    ajax_changeChecking("领导审批结果"), ajax_doTechApprove("领导审批结果"), event_prepareImplement("变更实施准备"),

    event_createBatchUpdate("创建批量变更"), event_batchApprove("批量变更审批"), event_batchImplement("批量变更实施"),

    uploadPbInfo("上传导入排班信息"), exportPbInfoTemaplate("导出排班信息模板"), exportPbInfo("导出排班信息"),

    zcwx_applyZcWx("申请维修"), zcbf_applyZcBf("申请报废"), zcwx_approveWx("审批/授权（资产维修）"), zcwx_confirmBfResult("审批/授权（资产报废）"), zcwx_confirmWxResult("确认维修结果"),

    cg_addCgInfo("新增采购申请"),addZcWpTypeInfo("采购物品类型新增"),updZcWpTypeInfo("采购物品类型修改"),delZcWpTypeInfo("采购物品类型管理删除"),
    credit_addIndex("增加诚信指标"), credit_editIndex("编辑诚信指标"),credit_delIndex("删除诚信指标"),
    addBxdProcessConfigInfo("添加费用报销流程配置"),updBxdProcessConfigInfo("修改费用报销流程配置"),delBxdProcessConfigInfo("删除费用报销流程配置"),
    rkInfo_getRkInfoList(""),

    rkInfo_getZcWpInfoList(""), rkInfo_getZcWpChartInfo(""), ck_getCkInfoList(""), jjbInfo_getJjbInfoList(""), pbInfo_getPbInfoList(""),

    zcdj_getNotConfirmedWpList(""), thbInfo_getThbInfoList(""), purpose_getPurposeList(""),

    pro_initProblemSearchAuto(""), pro_problemSearch(""), pro_getSolution(""), problem_getAllProblemTypeList(""), pro_problemList(""),

    problem_getProblemTypeList(""), pro_problemListAll(""), trans_getTransList(""), user_getWaibaoZhibanList(""), rkInfo_getZcCkSummaryList(""),

    rkInfo_getZcRkCkList(""), event_getEventEfectList(""), event_getEventSourceList(""), event_getEventTypeList(""), getAttachmentsList(""),

    event_getEventList(""), event_getTransferRecordList(""), event_getSolutionStatus(""), event_getActiveSolution(""), event_getFailedSolutionList(""),

    event_downloadAttachment(""), event_getEventWorkItems(""), getEventStatusList(""), event_getEChildProblemList(""), getAttachmentTypeListByLevel(""),

    getAttachmentTypeList(""), event_getTabNameList(""), event_getTabList(""), event_getAttachmentTypeListByTabId(""),

    getParentEventById(""), event_getWorkItem(""), event_getOptionList(""), event_getRequiredAttachmentTypeListByTabId(""), event_exportBIAData(""),

    event_getAttachmentByTabId(""), event_validateRequiredAttachmentType(""), event_downloadAttachmentsZipByEventId(""), event_exportEventList(""),

    preViewAttachment(""), event_getProStatusList(""), event_getChangeStatusList(""), getRelativeChange(""), event_getChangeListBy(""),

    bxd_getProcessConfigListNew(""),
    event_getBatchStatusList(""), zcwx_getZcWxInfoList(""), showCgInfo(""), zcbf_getZcBfInfoList(""), cgInfoUpd_getWpSuoShuBuMenByFlag(""), cgInfoUpd_addCgInfo(
            ""),  cg_wpType_getWpTypeListNew("采购物品类型管理查询"),hbh_getHbhMeetingList(""),hbh_showMeetingAdd("行办会会议新增"),dbh_getAttachList("董办会附件查询"),
    dbh_uploadAttach("董办会附件新增"),delDbhAttach("董办会附件删除"),downloadDbhAttach("董办会附件下载"),ysbh_searchYsbhInfo("采购预算编号查询"),ysbh_editYsbhInfo("采购预算编号编辑"),
    dbh_getCollectionList(""),dbh_showCollectionAdd("董办会议案征集新增"), sc_initiateSealControlMachineUseSealRequisition("发起印控机用印请求"), sc_searchSealControlHistoricalProcessList("查询印控历史流程"),
    sc_searchSealControlUndoneProcessList("查询印控待办流程"), sc_addSealControlProcess("申请印控流程"), retrieveDataFromUiams("从统一用户获取数据");
    

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    ActionType(String name) {
        this.name = name;
    }

}
