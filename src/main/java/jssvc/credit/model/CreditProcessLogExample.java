package jssvc.credit.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreditProcessLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CreditProcessLogExample() {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andProcessidIsNull() {
            addCriterion("processid is null");
            return (Criteria) this;
        }

        public Criteria andProcessidIsNotNull() {
            addCriterion("processid is not null");
            return (Criteria) this;
        }

        public Criteria andProcessidEqualTo(String value) {
            addCriterion("processid =", value, "processid");
            return (Criteria) this;
        }

        public Criteria andProcessidNotEqualTo(String value) {
            addCriterion("processid <>", value, "processid");
            return (Criteria) this;
        }

        public Criteria andProcessidGreaterThan(String value) {
            addCriterion("processid >", value, "processid");
            return (Criteria) this;
        }

        public Criteria andProcessidGreaterThanOrEqualTo(String value) {
            addCriterion("processid >=", value, "processid");
            return (Criteria) this;
        }

        public Criteria andProcessidLessThan(String value) {
            addCriterion("processid <", value, "processid");
            return (Criteria) this;
        }

        public Criteria andProcessidLessThanOrEqualTo(String value) {
            addCriterion("processid <=", value, "processid");
            return (Criteria) this;
        }

        public Criteria andProcessidLike(String value) {
            addCriterion("processid like", value, "processid");
            return (Criteria) this;
        }

        public Criteria andProcessidNotLike(String value) {
            addCriterion("processid not like", value, "processid");
            return (Criteria) this;
        }

        public Criteria andProcessidIn(List<String> values) {
            addCriterion("processid in", values, "processid");
            return (Criteria) this;
        }

        public Criteria andProcessidNotIn(List<String> values) {
            addCriterion("processid not in", values, "processid");
            return (Criteria) this;
        }

        public Criteria andProcessidBetween(String value1, String value2) {
            addCriterion("processid between", value1, value2, "processid");
            return (Criteria) this;
        }

        public Criteria andProcessidNotBetween(String value1, String value2) {
            addCriterion("processid not between", value1, value2, "processid");
            return (Criteria) this;
        }

        public Criteria andProcessingUserIsNull() {
            addCriterion("processing_user is null");
            return (Criteria) this;
        }

        public Criteria andProcessingUserIsNotNull() {
            addCriterion("processing_user is not null");
            return (Criteria) this;
        }

        public Criteria andProcessingUserEqualTo(String value) {
            addCriterion("processing_user =", value, "processingUser");
            return (Criteria) this;
        }

        public Criteria andProcessingUserNotEqualTo(String value) {
            addCriterion("processing_user <>", value, "processingUser");
            return (Criteria) this;
        }

        public Criteria andProcessingUserGreaterThan(String value) {
            addCriterion("processing_user >", value, "processingUser");
            return (Criteria) this;
        }

        public Criteria andProcessingUserGreaterThanOrEqualTo(String value) {
            addCriterion("processing_user >=", value, "processingUser");
            return (Criteria) this;
        }

        public Criteria andProcessingUserLessThan(String value) {
            addCriterion("processing_user <", value, "processingUser");
            return (Criteria) this;
        }

        public Criteria andProcessingUserLessThanOrEqualTo(String value) {
            addCriterion("processing_user <=", value, "processingUser");
            return (Criteria) this;
        }

        public Criteria andProcessingUserLike(String value) {
            addCriterion("processing_user like", value, "processingUser");
            return (Criteria) this;
        }

        public Criteria andProcessingUserNotLike(String value) {
            addCriterion("processing_user not like", value, "processingUser");
            return (Criteria) this;
        }

        public Criteria andProcessingUserIn(List<String> values) {
            addCriterion("processing_user in", values, "processingUser");
            return (Criteria) this;
        }

        public Criteria andProcessingUserNotIn(List<String> values) {
            addCriterion("processing_user not in", values, "processingUser");
            return (Criteria) this;
        }

        public Criteria andProcessingUserBetween(String value1, String value2) {
            addCriterion("processing_user between", value1, value2, "processingUser");
            return (Criteria) this;
        }

        public Criteria andProcessingUserNotBetween(String value1, String value2) {
            addCriterion("processing_user not between", value1, value2, "processingUser");
            return (Criteria) this;
        }

        public Criteria andNextUserIsNull() {
            addCriterion("next_user is null");
            return (Criteria) this;
        }

        public Criteria andNextUserIsNotNull() {
            addCriterion("next_user is not null");
            return (Criteria) this;
        }

        public Criteria andNextUserEqualTo(String value) {
            addCriterion("next_user =", value, "nextUser");
            return (Criteria) this;
        }

        public Criteria andNextUserNotEqualTo(String value) {
            addCriterion("next_user <>", value, "nextUser");
            return (Criteria) this;
        }

        public Criteria andNextUserGreaterThan(String value) {
            addCriterion("next_user >", value, "nextUser");
            return (Criteria) this;
        }

        public Criteria andNextUserGreaterThanOrEqualTo(String value) {
            addCriterion("next_user >=", value, "nextUser");
            return (Criteria) this;
        }

        public Criteria andNextUserLessThan(String value) {
            addCriterion("next_user <", value, "nextUser");
            return (Criteria) this;
        }

        public Criteria andNextUserLessThanOrEqualTo(String value) {
            addCriterion("next_user <=", value, "nextUser");
            return (Criteria) this;
        }

        public Criteria andNextUserLike(String value) {
            addCriterion("next_user like", value, "nextUser");
            return (Criteria) this;
        }

        public Criteria andNextUserNotLike(String value) {
            addCriterion("next_user not like", value, "nextUser");
            return (Criteria) this;
        }

        public Criteria andNextUserIn(List<String> values) {
            addCriterion("next_user in", values, "nextUser");
            return (Criteria) this;
        }

        public Criteria andNextUserNotIn(List<String> values) {
            addCriterion("next_user not in", values, "nextUser");
            return (Criteria) this;
        }

        public Criteria andNextUserBetween(String value1, String value2) {
            addCriterion("next_user between", value1, value2, "nextUser");
            return (Criteria) this;
        }

        public Criteria andNextUserNotBetween(String value1, String value2) {
            addCriterion("next_user not between", value1, value2, "nextUser");
            return (Criteria) this;
        }

        public Criteria andHandleTimeIsNull() {
            addCriterion("handle_time is null");
            return (Criteria) this;
        }

        public Criteria andHandleTimeIsNotNull() {
            addCriterion("handle_time is not null");
            return (Criteria) this;
        }

        public Criteria andHandleTimeEqualTo(Date value) {
            addCriterion("handle_time =", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeNotEqualTo(Date value) {
            addCriterion("handle_time <>", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeGreaterThan(Date value) {
            addCriterion("handle_time >", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("handle_time >=", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeLessThan(Date value) {
            addCriterion("handle_time <", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeLessThanOrEqualTo(Date value) {
            addCriterion("handle_time <=", value, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeIn(List<Date> values) {
            addCriterion("handle_time in", values, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeNotIn(List<Date> values) {
            addCriterion("handle_time not in", values, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeBetween(Date value1, Date value2) {
            addCriterion("handle_time between", value1, value2, "handleTime");
            return (Criteria) this;
        }

        public Criteria andHandleTimeNotBetween(Date value1, Date value2) {
            addCriterion("handle_time not between", value1, value2, "handleTime");
            return (Criteria) this;
        }

        public Criteria andApproveStatusIsNull() {
            addCriterion("approve_status is null");
            return (Criteria) this;
        }

        public Criteria andApproveStatusIsNotNull() {
            addCriterion("approve_status is not null");
            return (Criteria) this;
        }

        public Criteria andApproveStatusEqualTo(String value) {
            addCriterion("approve_status =", value, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusNotEqualTo(String value) {
            addCriterion("approve_status <>", value, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusGreaterThan(String value) {
            addCriterion("approve_status >", value, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusGreaterThanOrEqualTo(String value) {
            addCriterion("approve_status >=", value, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusLessThan(String value) {
            addCriterion("approve_status <", value, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusLessThanOrEqualTo(String value) {
            addCriterion("approve_status <=", value, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusLike(String value) {
            addCriterion("approve_status like", value, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusNotLike(String value) {
            addCriterion("approve_status not like", value, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusIn(List<String> values) {
            addCriterion("approve_status in", values, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusNotIn(List<String> values) {
            addCriterion("approve_status not in", values, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusBetween(String value1, String value2) {
            addCriterion("approve_status between", value1, value2, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andApproveStatusNotBetween(String value1, String value2) {
            addCriterion("approve_status not between", value1, value2, "approveStatus");
            return (Criteria) this;
        }

        public Criteria andOprationDetailIsNull() {
            addCriterion("opration_detail is null");
            return (Criteria) this;
        }

        public Criteria andOprationDetailIsNotNull() {
            addCriterion("opration_detail is not null");
            return (Criteria) this;
        }

        public Criteria andOprationDetailEqualTo(String value) {
            addCriterion("opration_detail =", value, "oprationDetail");
            return (Criteria) this;
        }

        public Criteria andOprationDetailNotEqualTo(String value) {
            addCriterion("opration_detail <>", value, "oprationDetail");
            return (Criteria) this;
        }

        public Criteria andOprationDetailGreaterThan(String value) {
            addCriterion("opration_detail >", value, "oprationDetail");
            return (Criteria) this;
        }

        public Criteria andOprationDetailGreaterThanOrEqualTo(String value) {
            addCriterion("opration_detail >=", value, "oprationDetail");
            return (Criteria) this;
        }

        public Criteria andOprationDetailLessThan(String value) {
            addCriterion("opration_detail <", value, "oprationDetail");
            return (Criteria) this;
        }

        public Criteria andOprationDetailLessThanOrEqualTo(String value) {
            addCriterion("opration_detail <=", value, "oprationDetail");
            return (Criteria) this;
        }

        public Criteria andOprationDetailLike(String value) {
            addCriterion("opration_detail like", value, "oprationDetail");
            return (Criteria) this;
        }

        public Criteria andOprationDetailNotLike(String value) {
            addCriterion("opration_detail not like", value, "oprationDetail");
            return (Criteria) this;
        }

        public Criteria andOprationDetailIn(List<String> values) {
            addCriterion("opration_detail in", values, "oprationDetail");
            return (Criteria) this;
        }

        public Criteria andOprationDetailNotIn(List<String> values) {
            addCriterion("opration_detail not in", values, "oprationDetail");
            return (Criteria) this;
        }

        public Criteria andOprationDetailBetween(String value1, String value2) {
            addCriterion("opration_detail between", value1, value2, "oprationDetail");
            return (Criteria) this;
        }

        public Criteria andOprationDetailNotBetween(String value1, String value2) {
            addCriterion("opration_detail not between", value1, value2, "oprationDetail");
            return (Criteria) this;
        }

        public Criteria andOprationResultIsNull() {
            addCriterion("opration_result is null");
            return (Criteria) this;
        }

        public Criteria andOprationResultIsNotNull() {
            addCriterion("opration_result is not null");
            return (Criteria) this;
        }

        public Criteria andOprationResultEqualTo(String value) {
            addCriterion("opration_result =", value, "oprationResult");
            return (Criteria) this;
        }

        public Criteria andOprationResultNotEqualTo(String value) {
            addCriterion("opration_result <>", value, "oprationResult");
            return (Criteria) this;
        }

        public Criteria andOprationResultGreaterThan(String value) {
            addCriterion("opration_result >", value, "oprationResult");
            return (Criteria) this;
        }

        public Criteria andOprationResultGreaterThanOrEqualTo(String value) {
            addCriterion("opration_result >=", value, "oprationResult");
            return (Criteria) this;
        }

        public Criteria andOprationResultLessThan(String value) {
            addCriterion("opration_result <", value, "oprationResult");
            return (Criteria) this;
        }

        public Criteria andOprationResultLessThanOrEqualTo(String value) {
            addCriterion("opration_result <=", value, "oprationResult");
            return (Criteria) this;
        }

        public Criteria andOprationResultLike(String value) {
            addCriterion("opration_result like", value, "oprationResult");
            return (Criteria) this;
        }

        public Criteria andOprationResultNotLike(String value) {
            addCriterion("opration_result not like", value, "oprationResult");
            return (Criteria) this;
        }

        public Criteria andOprationResultIn(List<String> values) {
            addCriterion("opration_result in", values, "oprationResult");
            return (Criteria) this;
        }

        public Criteria andOprationResultNotIn(List<String> values) {
            addCriterion("opration_result not in", values, "oprationResult");
            return (Criteria) this;
        }

        public Criteria andOprationResultBetween(String value1, String value2) {
            addCriterion("opration_result between", value1, value2, "oprationResult");
            return (Criteria) this;
        }

        public Criteria andOprationResultNotBetween(String value1, String value2) {
            addCriterion("opration_result not between", value1, value2, "oprationResult");
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