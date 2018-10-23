package jssvc.credit.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreditAttachmentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CreditAttachmentExample() {
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

        public Criteria andUploaddahIsNull() {
            addCriterion("uploaddah is null");
            return (Criteria) this;
        }

        public Criteria andUploaddahIsNotNull() {
            addCriterion("uploaddah is not null");
            return (Criteria) this;
        }

        public Criteria andUploaddahEqualTo(String value) {
            addCriterion("uploaddah =", value, "uploaddah");
            return (Criteria) this;
        }

        public Criteria andUploaddahNotEqualTo(String value) {
            addCriterion("uploaddah <>", value, "uploaddah");
            return (Criteria) this;
        }

        public Criteria andUploaddahGreaterThan(String value) {
            addCriterion("uploaddah >", value, "uploaddah");
            return (Criteria) this;
        }

        public Criteria andUploaddahGreaterThanOrEqualTo(String value) {
            addCriterion("uploaddah >=", value, "uploaddah");
            return (Criteria) this;
        }

        public Criteria andUploaddahLessThan(String value) {
            addCriterion("uploaddah <", value, "uploaddah");
            return (Criteria) this;
        }

        public Criteria andUploaddahLessThanOrEqualTo(String value) {
            addCriterion("uploaddah <=", value, "uploaddah");
            return (Criteria) this;
        }

        public Criteria andUploaddahLike(String value) {
            addCriterion("uploaddah like", value, "uploaddah");
            return (Criteria) this;
        }

        public Criteria andUploaddahNotLike(String value) {
            addCriterion("uploaddah not like", value, "uploaddah");
            return (Criteria) this;
        }

        public Criteria andUploaddahIn(List<String> values) {
            addCriterion("uploaddah in", values, "uploaddah");
            return (Criteria) this;
        }

        public Criteria andUploaddahNotIn(List<String> values) {
            addCriterion("uploaddah not in", values, "uploaddah");
            return (Criteria) this;
        }

        public Criteria andUploaddahBetween(String value1, String value2) {
            addCriterion("uploaddah between", value1, value2, "uploaddah");
            return (Criteria) this;
        }

        public Criteria andUploaddahNotBetween(String value1, String value2) {
            addCriterion("uploaddah not between", value1, value2, "uploaddah");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andUploadtimeIsNull() {
            addCriterion("uploadtime is null");
            return (Criteria) this;
        }

        public Criteria andUploadtimeIsNotNull() {
            addCriterion("uploadtime is not null");
            return (Criteria) this;
        }

        public Criteria andUploadtimeEqualTo(Date value) {
            addCriterion("uploadtime =", value, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andUploadtimeNotEqualTo(Date value) {
            addCriterion("uploadtime <>", value, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andUploadtimeGreaterThan(Date value) {
            addCriterion("uploadtime >", value, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andUploadtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("uploadtime >=", value, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andUploadtimeLessThan(Date value) {
            addCriterion("uploadtime <", value, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andUploadtimeLessThanOrEqualTo(Date value) {
            addCriterion("uploadtime <=", value, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andUploadtimeIn(List<Date> values) {
            addCriterion("uploadtime in", values, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andUploadtimeNotIn(List<Date> values) {
            addCriterion("uploadtime not in", values, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andUploadtimeBetween(Date value1, Date value2) {
            addCriterion("uploadtime between", value1, value2, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andUploadtimeNotBetween(Date value1, Date value2) {
            addCriterion("uploadtime not between", value1, value2, "uploadtime");
            return (Criteria) this;
        }

        public Criteria andFilepathIsNull() {
            addCriterion("filepath is null");
            return (Criteria) this;
        }

        public Criteria andFilepathIsNotNull() {
            addCriterion("filepath is not null");
            return (Criteria) this;
        }

        public Criteria andFilepathEqualTo(String value) {
            addCriterion("filepath =", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathNotEqualTo(String value) {
            addCriterion("filepath <>", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathGreaterThan(String value) {
            addCriterion("filepath >", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathGreaterThanOrEqualTo(String value) {
            addCriterion("filepath >=", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathLessThan(String value) {
            addCriterion("filepath <", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathLessThanOrEqualTo(String value) {
            addCriterion("filepath <=", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathLike(String value) {
            addCriterion("filepath like", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathNotLike(String value) {
            addCriterion("filepath not like", value, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathIn(List<String> values) {
            addCriterion("filepath in", values, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathNotIn(List<String> values) {
            addCriterion("filepath not in", values, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathBetween(String value1, String value2) {
            addCriterion("filepath between", value1, value2, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilepathNotBetween(String value1, String value2) {
            addCriterion("filepath not between", value1, value2, "filepath");
            return (Criteria) this;
        }

        public Criteria andFilenameIsNull() {
            addCriterion("filename is null");
            return (Criteria) this;
        }

        public Criteria andFilenameIsNotNull() {
            addCriterion("filename is not null");
            return (Criteria) this;
        }

        public Criteria andFilenameEqualTo(String value) {
            addCriterion("filename =", value, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameNotEqualTo(String value) {
            addCriterion("filename <>", value, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameGreaterThan(String value) {
            addCriterion("filename >", value, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameGreaterThanOrEqualTo(String value) {
            addCriterion("filename >=", value, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameLessThan(String value) {
            addCriterion("filename <", value, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameLessThanOrEqualTo(String value) {
            addCriterion("filename <=", value, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameLike(String value) {
            addCriterion("filename like", value, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameNotLike(String value) {
            addCriterion("filename not like", value, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameIn(List<String> values) {
            addCriterion("filename in", values, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameNotIn(List<String> values) {
            addCriterion("filename not in", values, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameBetween(String value1, String value2) {
            addCriterion("filename between", value1, value2, "filename");
            return (Criteria) this;
        }

        public Criteria andFilenameNotBetween(String value1, String value2) {
            addCriterion("filename not between", value1, value2, "filename");
            return (Criteria) this;
        }

        public Criteria andWebfilenameIsNull() {
            addCriterion("webfilename is null");
            return (Criteria) this;
        }

        public Criteria andWebfilenameIsNotNull() {
            addCriterion("webfilename is not null");
            return (Criteria) this;
        }

        public Criteria andWebfilenameEqualTo(String value) {
            addCriterion("webfilename =", value, "webfilename");
            return (Criteria) this;
        }

        public Criteria andWebfilenameNotEqualTo(String value) {
            addCriterion("webfilename <>", value, "webfilename");
            return (Criteria) this;
        }

        public Criteria andWebfilenameGreaterThan(String value) {
            addCriterion("webfilename >", value, "webfilename");
            return (Criteria) this;
        }

        public Criteria andWebfilenameGreaterThanOrEqualTo(String value) {
            addCriterion("webfilename >=", value, "webfilename");
            return (Criteria) this;
        }

        public Criteria andWebfilenameLessThan(String value) {
            addCriterion("webfilename <", value, "webfilename");
            return (Criteria) this;
        }

        public Criteria andWebfilenameLessThanOrEqualTo(String value) {
            addCriterion("webfilename <=", value, "webfilename");
            return (Criteria) this;
        }

        public Criteria andWebfilenameLike(String value) {
            addCriterion("webfilename like", value, "webfilename");
            return (Criteria) this;
        }

        public Criteria andWebfilenameNotLike(String value) {
            addCriterion("webfilename not like", value, "webfilename");
            return (Criteria) this;
        }

        public Criteria andWebfilenameIn(List<String> values) {
            addCriterion("webfilename in", values, "webfilename");
            return (Criteria) this;
        }

        public Criteria andWebfilenameNotIn(List<String> values) {
            addCriterion("webfilename not in", values, "webfilename");
            return (Criteria) this;
        }

        public Criteria andWebfilenameBetween(String value1, String value2) {
            addCriterion("webfilename between", value1, value2, "webfilename");
            return (Criteria) this;
        }

        public Criteria andWebfilenameNotBetween(String value1, String value2) {
            addCriterion("webfilename not between", value1, value2, "webfilename");
            return (Criteria) this;
        }

        public Criteria andSuggestbhIsNull() {
            addCriterion("suggestbh is null");
            return (Criteria) this;
        }

        public Criteria andSuggestbhIsNotNull() {
            addCriterion("suggestbh is not null");
            return (Criteria) this;
        }

        public Criteria andSuggestbhEqualTo(String value) {
            addCriterion("suggestbh =", value, "suggestbh");
            return (Criteria) this;
        }

        public Criteria andSuggestbhNotEqualTo(String value) {
            addCriterion("suggestbh <>", value, "suggestbh");
            return (Criteria) this;
        }

        public Criteria andSuggestbhGreaterThan(String value) {
            addCriterion("suggestbh >", value, "suggestbh");
            return (Criteria) this;
        }

        public Criteria andSuggestbhGreaterThanOrEqualTo(String value) {
            addCriterion("suggestbh >=", value, "suggestbh");
            return (Criteria) this;
        }

        public Criteria andSuggestbhLessThan(String value) {
            addCriterion("suggestbh <", value, "suggestbh");
            return (Criteria) this;
        }

        public Criteria andSuggestbhLessThanOrEqualTo(String value) {
            addCriterion("suggestbh <=", value, "suggestbh");
            return (Criteria) this;
        }

        public Criteria andSuggestbhLike(String value) {
            addCriterion("suggestbh like", value, "suggestbh");
            return (Criteria) this;
        }

        public Criteria andSuggestbhNotLike(String value) {
            addCriterion("suggestbh not like", value, "suggestbh");
            return (Criteria) this;
        }

        public Criteria andSuggestbhIn(List<String> values) {
            addCriterion("suggestbh in", values, "suggestbh");
            return (Criteria) this;
        }

        public Criteria andSuggestbhNotIn(List<String> values) {
            addCriterion("suggestbh not in", values, "suggestbh");
            return (Criteria) this;
        }

        public Criteria andSuggestbhBetween(String value1, String value2) {
            addCriterion("suggestbh between", value1, value2, "suggestbh");
            return (Criteria) this;
        }

        public Criteria andSuggestbhNotBetween(String value1, String value2) {
            addCriterion("suggestbh not between", value1, value2, "suggestbh");
            return (Criteria) this;
        }

        public Criteria andUploadnameIsNull() {
            addCriterion("uploadname is null");
            return (Criteria) this;
        }

        public Criteria andUploadnameIsNotNull() {
            addCriterion("uploadname is not null");
            return (Criteria) this;
        }

        public Criteria andUploadnameEqualTo(String value) {
            addCriterion("uploadname =", value, "uploadname");
            return (Criteria) this;
        }

        public Criteria andUploadnameNotEqualTo(String value) {
            addCriterion("uploadname <>", value, "uploadname");
            return (Criteria) this;
        }

        public Criteria andUploadnameGreaterThan(String value) {
            addCriterion("uploadname >", value, "uploadname");
            return (Criteria) this;
        }

        public Criteria andUploadnameGreaterThanOrEqualTo(String value) {
            addCriterion("uploadname >=", value, "uploadname");
            return (Criteria) this;
        }

        public Criteria andUploadnameLessThan(String value) {
            addCriterion("uploadname <", value, "uploadname");
            return (Criteria) this;
        }

        public Criteria andUploadnameLessThanOrEqualTo(String value) {
            addCriterion("uploadname <=", value, "uploadname");
            return (Criteria) this;
        }

        public Criteria andUploadnameLike(String value) {
            addCriterion("uploadname like", value, "uploadname");
            return (Criteria) this;
        }

        public Criteria andUploadnameNotLike(String value) {
            addCriterion("uploadname not like", value, "uploadname");
            return (Criteria) this;
        }

        public Criteria andUploadnameIn(List<String> values) {
            addCriterion("uploadname in", values, "uploadname");
            return (Criteria) this;
        }

        public Criteria andUploadnameNotIn(List<String> values) {
            addCriterion("uploadname not in", values, "uploadname");
            return (Criteria) this;
        }

        public Criteria andUploadnameBetween(String value1, String value2) {
            addCriterion("uploadname between", value1, value2, "uploadname");
            return (Criteria) this;
        }

        public Criteria andUploadnameNotBetween(String value1, String value2) {
            addCriterion("uploadname not between", value1, value2, "uploadname");
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