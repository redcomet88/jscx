package jssvc.credit.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreditProcessExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CreditProcessExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCreditTitleIsNull() {
            addCriterion("credit_Title is null");
            return (Criteria) this;
        }

        public Criteria andCreditTitleIsNotNull() {
            addCriterion("credit_Title is not null");
            return (Criteria) this;
        }

        public Criteria andCreditTitleEqualTo(String value) {
            addCriterion("credit_Title =", value, "creditTitle");
            return (Criteria) this;
        }

        public Criteria andCreditTitleNotEqualTo(String value) {
            addCriterion("credit_Title <>", value, "creditTitle");
            return (Criteria) this;
        }

        public Criteria andCreditTitleGreaterThan(String value) {
            addCriterion("credit_Title >", value, "creditTitle");
            return (Criteria) this;
        }

        public Criteria andCreditTitleGreaterThanOrEqualTo(String value) {
            addCriterion("credit_Title >=", value, "creditTitle");
            return (Criteria) this;
        }

        public Criteria andCreditTitleLessThan(String value) {
            addCriterion("credit_Title <", value, "creditTitle");
            return (Criteria) this;
        }

        public Criteria andCreditTitleLessThanOrEqualTo(String value) {
            addCriterion("credit_Title <=", value, "creditTitle");
            return (Criteria) this;
        }

        public Criteria andCreditTitleLike(String value) {
            addCriterion("credit_Title like", value, "creditTitle");
            return (Criteria) this;
        }

        public Criteria andCreditTitleNotLike(String value) {
            addCriterion("credit_Title not like", value, "creditTitle");
            return (Criteria) this;
        }

        public Criteria andCreditTitleIn(List<String> values) {
            addCriterion("credit_Title in", values, "creditTitle");
            return (Criteria) this;
        }

        public Criteria andCreditTitleNotIn(List<String> values) {
            addCriterion("credit_Title not in", values, "creditTitle");
            return (Criteria) this;
        }

        public Criteria andCreditTitleBetween(String value1, String value2) {
            addCriterion("credit_Title between", value1, value2, "creditTitle");
            return (Criteria) this;
        }

        public Criteria andCreditTitleNotBetween(String value1, String value2) {
            addCriterion("credit_Title not between", value1, value2, "creditTitle");
            return (Criteria) this;
        }

        public Criteria andCreditContentIsNull() {
            addCriterion("credit_Content is null");
            return (Criteria) this;
        }

        public Criteria andCreditContentIsNotNull() {
            addCriterion("credit_Content is not null");
            return (Criteria) this;
        }

        public Criteria andCreditContentEqualTo(String value) {
            addCriterion("credit_Content =", value, "creditContent");
            return (Criteria) this;
        }

        public Criteria andCreditContentNotEqualTo(String value) {
            addCriterion("credit_Content <>", value, "creditContent");
            return (Criteria) this;
        }

        public Criteria andCreditContentGreaterThan(String value) {
            addCriterion("credit_Content >", value, "creditContent");
            return (Criteria) this;
        }

        public Criteria andCreditContentGreaterThanOrEqualTo(String value) {
            addCriterion("credit_Content >=", value, "creditContent");
            return (Criteria) this;
        }

        public Criteria andCreditContentLessThan(String value) {
            addCriterion("credit_Content <", value, "creditContent");
            return (Criteria) this;
        }

        public Criteria andCreditContentLessThanOrEqualTo(String value) {
            addCriterion("credit_Content <=", value, "creditContent");
            return (Criteria) this;
        }

        public Criteria andCreditContentLike(String value) {
            addCriterion("credit_Content like", value, "creditContent");
            return (Criteria) this;
        }

        public Criteria andCreditContentNotLike(String value) {
            addCriterion("credit_Content not like", value, "creditContent");
            return (Criteria) this;
        }

        public Criteria andCreditContentIn(List<String> values) {
            addCriterion("credit_Content in", values, "creditContent");
            return (Criteria) this;
        }

        public Criteria andCreditContentNotIn(List<String> values) {
            addCriterion("credit_Content not in", values, "creditContent");
            return (Criteria) this;
        }

        public Criteria andCreditContentBetween(String value1, String value2) {
            addCriterion("credit_Content between", value1, value2, "creditContent");
            return (Criteria) this;
        }

        public Criteria andCreditContentNotBetween(String value1, String value2) {
            addCriterion("credit_Content not between", value1, value2, "creditContent");
            return (Criteria) this;
        }

        public Criteria andCurrentuserIsNull() {
            addCriterion("currentUser is null");
            return (Criteria) this;
        }

        public Criteria andCurrentuserIsNotNull() {
            addCriterion("currentUser is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentuserEqualTo(String value) {
            addCriterion("currentUser =", value, "currentuser");
            return (Criteria) this;
        }

        public Criteria andCurrentuserNotEqualTo(String value) {
            addCriterion("currentUser <>", value, "currentuser");
            return (Criteria) this;
        }

        public Criteria andCurrentuserGreaterThan(String value) {
            addCriterion("currentUser >", value, "currentuser");
            return (Criteria) this;
        }

        public Criteria andCurrentuserGreaterThanOrEqualTo(String value) {
            addCriterion("currentUser >=", value, "currentuser");
            return (Criteria) this;
        }

        public Criteria andCurrentuserLessThan(String value) {
            addCriterion("currentUser <", value, "currentuser");
            return (Criteria) this;
        }

        public Criteria andCurrentuserLessThanOrEqualTo(String value) {
            addCriterion("currentUser <=", value, "currentuser");
            return (Criteria) this;
        }

        public Criteria andCurrentuserLike(String value) {
            addCriterion("currentUser like", value, "currentuser");
            return (Criteria) this;
        }

        public Criteria andCurrentuserNotLike(String value) {
            addCriterion("currentUser not like", value, "currentuser");
            return (Criteria) this;
        }

        public Criteria andCurrentuserIn(List<String> values) {
            addCriterion("currentUser in", values, "currentuser");
            return (Criteria) this;
        }

        public Criteria andCurrentuserNotIn(List<String> values) {
            addCriterion("currentUser not in", values, "currentuser");
            return (Criteria) this;
        }

        public Criteria andCurrentuserBetween(String value1, String value2) {
            addCriterion("currentUser between", value1, value2, "currentuser");
            return (Criteria) this;
        }

        public Criteria andCurrentuserNotBetween(String value1, String value2) {
            addCriterion("currentUser not between", value1, value2, "currentuser");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andUploadDeptIsNull() {
            addCriterion("Upload_Dept is null");
            return (Criteria) this;
        }

        public Criteria andUploadDeptIsNotNull() {
            addCriterion("Upload_Dept is not null");
            return (Criteria) this;
        }

        public Criteria andUploadDeptEqualTo(String value) {
            addCriterion("Upload_Dept =", value, "uploadDept");
            return (Criteria) this;
        }

        public Criteria andUploadDeptNotEqualTo(String value) {
            addCriterion("Upload_Dept <>", value, "uploadDept");
            return (Criteria) this;
        }

        public Criteria andUploadDeptGreaterThan(String value) {
            addCriterion("Upload_Dept >", value, "uploadDept");
            return (Criteria) this;
        }

        public Criteria andUploadDeptGreaterThanOrEqualTo(String value) {
            addCriterion("Upload_Dept >=", value, "uploadDept");
            return (Criteria) this;
        }

        public Criteria andUploadDeptLessThan(String value) {
            addCriterion("Upload_Dept <", value, "uploadDept");
            return (Criteria) this;
        }

        public Criteria andUploadDeptLessThanOrEqualTo(String value) {
            addCriterion("Upload_Dept <=", value, "uploadDept");
            return (Criteria) this;
        }

        public Criteria andUploadDeptLike(String value) {
            addCriterion("Upload_Dept like", value, "uploadDept");
            return (Criteria) this;
        }

        public Criteria andUploadDeptNotLike(String value) {
            addCriterion("Upload_Dept not like", value, "uploadDept");
            return (Criteria) this;
        }

        public Criteria andUploadDeptIn(List<String> values) {
            addCriterion("Upload_Dept in", values, "uploadDept");
            return (Criteria) this;
        }

        public Criteria andUploadDeptNotIn(List<String> values) {
            addCriterion("Upload_Dept not in", values, "uploadDept");
            return (Criteria) this;
        }

        public Criteria andUploadDeptBetween(String value1, String value2) {
            addCriterion("Upload_Dept between", value1, value2, "uploadDept");
            return (Criteria) this;
        }

        public Criteria andUploadDeptNotBetween(String value1, String value2) {
            addCriterion("Upload_Dept not between", value1, value2, "uploadDept");
            return (Criteria) this;
        }

        public Criteria andUploadUserIsNull() {
            addCriterion("Upload_User is null");
            return (Criteria) this;
        }

        public Criteria andUploadUserIsNotNull() {
            addCriterion("Upload_User is not null");
            return (Criteria) this;
        }

        public Criteria andUploadUserEqualTo(String value) {
            addCriterion("Upload_User =", value, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserNotEqualTo(String value) {
            addCriterion("Upload_User <>", value, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserGreaterThan(String value) {
            addCriterion("Upload_User >", value, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserGreaterThanOrEqualTo(String value) {
            addCriterion("Upload_User >=", value, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserLessThan(String value) {
            addCriterion("Upload_User <", value, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserLessThanOrEqualTo(String value) {
            addCriterion("Upload_User <=", value, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserLike(String value) {
            addCriterion("Upload_User like", value, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserNotLike(String value) {
            addCriterion("Upload_User not like", value, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserIn(List<String> values) {
            addCriterion("Upload_User in", values, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserNotIn(List<String> values) {
            addCriterion("Upload_User not in", values, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserBetween(String value1, String value2) {
            addCriterion("Upload_User between", value1, value2, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andUploadUserNotBetween(String value1, String value2) {
            addCriterion("Upload_User not between", value1, value2, "uploadUser");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_Time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_Time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_Time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_Time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_Time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_Time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_Time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_Time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_Time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_Time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_Time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_Time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andOptTimeIsNull() {
            addCriterion("opt_Time is null");
            return (Criteria) this;
        }

        public Criteria andOptTimeIsNotNull() {
            addCriterion("opt_Time is not null");
            return (Criteria) this;
        }

        public Criteria andOptTimeEqualTo(Date value) {
            addCriterion("opt_Time =", value, "optTime");
            return (Criteria) this;
        }

        public Criteria andOptTimeNotEqualTo(Date value) {
            addCriterion("opt_Time <>", value, "optTime");
            return (Criteria) this;
        }

        public Criteria andOptTimeGreaterThan(Date value) {
            addCriterion("opt_Time >", value, "optTime");
            return (Criteria) this;
        }

        public Criteria andOptTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("opt_Time >=", value, "optTime");
            return (Criteria) this;
        }

        public Criteria andOptTimeLessThan(Date value) {
            addCriterion("opt_Time <", value, "optTime");
            return (Criteria) this;
        }

        public Criteria andOptTimeLessThanOrEqualTo(Date value) {
            addCriterion("opt_Time <=", value, "optTime");
            return (Criteria) this;
        }

        public Criteria andOptTimeIn(List<Date> values) {
            addCriterion("opt_Time in", values, "optTime");
            return (Criteria) this;
        }

        public Criteria andOptTimeNotIn(List<Date> values) {
            addCriterion("opt_Time not in", values, "optTime");
            return (Criteria) this;
        }

        public Criteria andOptTimeBetween(Date value1, Date value2) {
            addCriterion("opt_Time between", value1, value2, "optTime");
            return (Criteria) this;
        }

        public Criteria andOptTimeNotBetween(Date value1, Date value2) {
            addCriterion("opt_Time not between", value1, value2, "optTime");
            return (Criteria) this;
        }

        public Criteria andColumn1IsNull() {
            addCriterion("column1 is null");
            return (Criteria) this;
        }

        public Criteria andColumn1IsNotNull() {
            addCriterion("column1 is not null");
            return (Criteria) this;
        }

        public Criteria andColumn1EqualTo(String value) {
            addCriterion("column1 =", value, "column1");
            return (Criteria) this;
        }

        public Criteria andColumn1NotEqualTo(String value) {
            addCriterion("column1 <>", value, "column1");
            return (Criteria) this;
        }

        public Criteria andColumn1GreaterThan(String value) {
            addCriterion("column1 >", value, "column1");
            return (Criteria) this;
        }

        public Criteria andColumn1GreaterThanOrEqualTo(String value) {
            addCriterion("column1 >=", value, "column1");
            return (Criteria) this;
        }

        public Criteria andColumn1LessThan(String value) {
            addCriterion("column1 <", value, "column1");
            return (Criteria) this;
        }

        public Criteria andColumn1LessThanOrEqualTo(String value) {
            addCriterion("column1 <=", value, "column1");
            return (Criteria) this;
        }

        public Criteria andColumn1Like(String value) {
            addCriterion("column1 like", value, "column1");
            return (Criteria) this;
        }

        public Criteria andColumn1NotLike(String value) {
            addCriterion("column1 not like", value, "column1");
            return (Criteria) this;
        }

        public Criteria andColumn1In(List<String> values) {
            addCriterion("column1 in", values, "column1");
            return (Criteria) this;
        }

        public Criteria andColumn1NotIn(List<String> values) {
            addCriterion("column1 not in", values, "column1");
            return (Criteria) this;
        }

        public Criteria andColumn1Between(String value1, String value2) {
            addCriterion("column1 between", value1, value2, "column1");
            return (Criteria) this;
        }

        public Criteria andColumn1NotBetween(String value1, String value2) {
            addCriterion("column1 not between", value1, value2, "column1");
            return (Criteria) this;
        }

        public Criteria andColumn2IsNull() {
            addCriterion("column2 is null");
            return (Criteria) this;
        }

        public Criteria andColumn2IsNotNull() {
            addCriterion("column2 is not null");
            return (Criteria) this;
        }

        public Criteria andColumn2EqualTo(String value) {
            addCriterion("column2 =", value, "column2");
            return (Criteria) this;
        }

        public Criteria andColumn2NotEqualTo(String value) {
            addCriterion("column2 <>", value, "column2");
            return (Criteria) this;
        }

        public Criteria andColumn2GreaterThan(String value) {
            addCriterion("column2 >", value, "column2");
            return (Criteria) this;
        }

        public Criteria andColumn2GreaterThanOrEqualTo(String value) {
            addCriterion("column2 >=", value, "column2");
            return (Criteria) this;
        }

        public Criteria andColumn2LessThan(String value) {
            addCriterion("column2 <", value, "column2");
            return (Criteria) this;
        }

        public Criteria andColumn2LessThanOrEqualTo(String value) {
            addCriterion("column2 <=", value, "column2");
            return (Criteria) this;
        }

        public Criteria andColumn2Like(String value) {
            addCriterion("column2 like", value, "column2");
            return (Criteria) this;
        }

        public Criteria andColumn2NotLike(String value) {
            addCriterion("column2 not like", value, "column2");
            return (Criteria) this;
        }

        public Criteria andColumn2In(List<String> values) {
            addCriterion("column2 in", values, "column2");
            return (Criteria) this;
        }

        public Criteria andColumn2NotIn(List<String> values) {
            addCriterion("column2 not in", values, "column2");
            return (Criteria) this;
        }

        public Criteria andColumn2Between(String value1, String value2) {
            addCriterion("column2 between", value1, value2, "column2");
            return (Criteria) this;
        }

        public Criteria andColumn2NotBetween(String value1, String value2) {
            addCriterion("column2 not between", value1, value2, "column2");
            return (Criteria) this;
        }

        public Criteria andColumn3IsNull() {
            addCriterion("column3 is null");
            return (Criteria) this;
        }

        public Criteria andColumn3IsNotNull() {
            addCriterion("column3 is not null");
            return (Criteria) this;
        }

        public Criteria andColumn3EqualTo(Date value) {
            addCriterion("column3 =", value, "column3");
            return (Criteria) this;
        }

        public Criteria andColumn3NotEqualTo(Date value) {
            addCriterion("column3 <>", value, "column3");
            return (Criteria) this;
        }

        public Criteria andColumn3GreaterThan(Date value) {
            addCriterion("column3 >", value, "column3");
            return (Criteria) this;
        }

        public Criteria andColumn3GreaterThanOrEqualTo(Date value) {
            addCriterion("column3 >=", value, "column3");
            return (Criteria) this;
        }

        public Criteria andColumn3LessThan(Date value) {
            addCriterion("column3 <", value, "column3");
            return (Criteria) this;
        }

        public Criteria andColumn3LessThanOrEqualTo(Date value) {
            addCriterion("column3 <=", value, "column3");
            return (Criteria) this;
        }

        public Criteria andColumn3In(List<Date> values) {
            addCriterion("column3 in", values, "column3");
            return (Criteria) this;
        }

        public Criteria andColumn3NotIn(List<Date> values) {
            addCriterion("column3 not in", values, "column3");
            return (Criteria) this;
        }

        public Criteria andColumn3Between(Date value1, Date value2) {
            addCriterion("column3 between", value1, value2, "column3");
            return (Criteria) this;
        }

        public Criteria andColumn3NotBetween(Date value1, Date value2) {
            addCriterion("column3 not between", value1, value2, "column3");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}