package jssvc.user.model;

import java.util.ArrayList;
import java.util.List;

public class InstitutionInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InstitutionInfoExample() {
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

        public Criteria andJghIsNull() {
            addCriterion("jgh is null");
            return (Criteria) this;
        }

        public Criteria andJghIsNotNull() {
            addCriterion("jgh is not null");
            return (Criteria) this;
        }

        public Criteria andJghEqualTo(String value) {
            addCriterion("jgh =", value, "jgh");
            return (Criteria) this;
        }

        public Criteria andJghNotEqualTo(String value) {
            addCriterion("jgh <>", value, "jgh");
            return (Criteria) this;
        }

        public Criteria andJghGreaterThan(String value) {
            addCriterion("jgh >", value, "jgh");
            return (Criteria) this;
        }

        public Criteria andJghGreaterThanOrEqualTo(String value) {
            addCriterion("jgh >=", value, "jgh");
            return (Criteria) this;
        }

        public Criteria andJghLessThan(String value) {
            addCriterion("jgh <", value, "jgh");
            return (Criteria) this;
        }

        public Criteria andJghLessThanOrEqualTo(String value) {
            addCriterion("jgh <=", value, "jgh");
            return (Criteria) this;
        }

        public Criteria andJghLike(String value) {
            addCriterion("jgh like", value, "jgh");
            return (Criteria) this;
        }

        public Criteria andJghNotLike(String value) {
            addCriterion("jgh not like", value, "jgh");
            return (Criteria) this;
        }

        public Criteria andJghIn(List<String> values) {
            addCriterion("jgh in", values, "jgh");
            return (Criteria) this;
        }

        public Criteria andJghNotIn(List<String> values) {
            addCriterion("jgh not in", values, "jgh");
            return (Criteria) this;
        }

        public Criteria andJghBetween(String value1, String value2) {
            addCriterion("jgh between", value1, value2, "jgh");
            return (Criteria) this;
        }

        public Criteria andJghNotBetween(String value1, String value2) {
            addCriterion("jgh not between", value1, value2, "jgh");
            return (Criteria) this;
        }

        public Criteria andJgmcIsNull() {
            addCriterion("jgmc is null");
            return (Criteria) this;
        }

        public Criteria andJgmcIsNotNull() {
            addCriterion("jgmc is not null");
            return (Criteria) this;
        }

        public Criteria andJgmcEqualTo(String value) {
            addCriterion("jgmc =", value, "jgmc");
            return (Criteria) this;
        }

        public Criteria andJgmcNotEqualTo(String value) {
            addCriterion("jgmc <>", value, "jgmc");
            return (Criteria) this;
        }

        public Criteria andJgmcGreaterThan(String value) {
            addCriterion("jgmc >", value, "jgmc");
            return (Criteria) this;
        }

        public Criteria andJgmcGreaterThanOrEqualTo(String value) {
            addCriterion("jgmc >=", value, "jgmc");
            return (Criteria) this;
        }

        public Criteria andJgmcLessThan(String value) {
            addCriterion("jgmc <", value, "jgmc");
            return (Criteria) this;
        }

        public Criteria andJgmcLessThanOrEqualTo(String value) {
            addCriterion("jgmc <=", value, "jgmc");
            return (Criteria) this;
        }

        public Criteria andJgmcLike(String value) {
            addCriterion("jgmc like", value, "jgmc");
            return (Criteria) this;
        }

        public Criteria andJgmcNotLike(String value) {
            addCriterion("jgmc not like", value, "jgmc");
            return (Criteria) this;
        }

        public Criteria andJgmcIn(List<String> values) {
            addCriterion("jgmc in", values, "jgmc");
            return (Criteria) this;
        }

        public Criteria andJgmcNotIn(List<String> values) {
            addCriterion("jgmc not in", values, "jgmc");
            return (Criteria) this;
        }

        public Criteria andJgmcBetween(String value1, String value2) {
            addCriterion("jgmc between", value1, value2, "jgmc");
            return (Criteria) this;
        }

        public Criteria andJgmcNotBetween(String value1, String value2) {
            addCriterion("jgmc not between", value1, value2, "jgmc");
            return (Criteria) this;
        }

        public Criteria andSjjgIsNull() {
            addCriterion("sjjg is null");
            return (Criteria) this;
        }

        public Criteria andSjjgIsNotNull() {
            addCriterion("sjjg is not null");
            return (Criteria) this;
        }

        public Criteria andSjjgEqualTo(String value) {
            addCriterion("sjjg =", value, "sjjg");
            return (Criteria) this;
        }

        public Criteria andSjjgNotEqualTo(String value) {
            addCriterion("sjjg <>", value, "sjjg");
            return (Criteria) this;
        }

        public Criteria andSjjgGreaterThan(String value) {
            addCriterion("sjjg >", value, "sjjg");
            return (Criteria) this;
        }

        public Criteria andSjjgGreaterThanOrEqualTo(String value) {
            addCriterion("sjjg >=", value, "sjjg");
            return (Criteria) this;
        }

        public Criteria andSjjgLessThan(String value) {
            addCriterion("sjjg <", value, "sjjg");
            return (Criteria) this;
        }

        public Criteria andSjjgLessThanOrEqualTo(String value) {
            addCriterion("sjjg <=", value, "sjjg");
            return (Criteria) this;
        }

        public Criteria andSjjgLike(String value) {
            addCriterion("sjjg like", value, "sjjg");
            return (Criteria) this;
        }

        public Criteria andSjjgNotLike(String value) {
            addCriterion("sjjg not like", value, "sjjg");
            return (Criteria) this;
        }

        public Criteria andSjjgIn(List<String> values) {
            addCriterion("sjjg in", values, "sjjg");
            return (Criteria) this;
        }

        public Criteria andSjjgNotIn(List<String> values) {
            addCriterion("sjjg not in", values, "sjjg");
            return (Criteria) this;
        }

        public Criteria andSjjgBetween(String value1, String value2) {
            addCriterion("sjjg between", value1, value2, "sjjg");
            return (Criteria) this;
        }

        public Criteria andSjjgNotBetween(String value1, String value2) {
            addCriterion("sjjg not between", value1, value2, "sjjg");
            return (Criteria) this;
        }

        public Criteria andSfydzhIsNull() {
            addCriterion("sfydzh is null");
            return (Criteria) this;
        }

        public Criteria andSfydzhIsNotNull() {
            addCriterion("sfydzh is not null");
            return (Criteria) this;
        }

        public Criteria andSfydzhEqualTo(String value) {
            addCriterion("sfydzh =", value, "sfydzh");
            return (Criteria) this;
        }

        public Criteria andSfydzhNotEqualTo(String value) {
            addCriterion("sfydzh <>", value, "sfydzh");
            return (Criteria) this;
        }

        public Criteria andSfydzhGreaterThan(String value) {
            addCriterion("sfydzh >", value, "sfydzh");
            return (Criteria) this;
        }

        public Criteria andSfydzhGreaterThanOrEqualTo(String value) {
            addCriterion("sfydzh >=", value, "sfydzh");
            return (Criteria) this;
        }

        public Criteria andSfydzhLessThan(String value) {
            addCriterion("sfydzh <", value, "sfydzh");
            return (Criteria) this;
        }

        public Criteria andSfydzhLessThanOrEqualTo(String value) {
            addCriterion("sfydzh <=", value, "sfydzh");
            return (Criteria) this;
        }

        public Criteria andSfydzhLike(String value) {
            addCriterion("sfydzh like", value, "sfydzh");
            return (Criteria) this;
        }

        public Criteria andSfydzhNotLike(String value) {
            addCriterion("sfydzh not like", value, "sfydzh");
            return (Criteria) this;
        }

        public Criteria andSfydzhIn(List<String> values) {
            addCriterion("sfydzh in", values, "sfydzh");
            return (Criteria) this;
        }

        public Criteria andSfydzhNotIn(List<String> values) {
            addCriterion("sfydzh not in", values, "sfydzh");
            return (Criteria) this;
        }

        public Criteria andSfydzhBetween(String value1, String value2) {
            addCriterion("sfydzh between", value1, value2, "sfydzh");
            return (Criteria) this;
        }

        public Criteria andSfydzhNotBetween(String value1, String value2) {
            addCriterion("sfydzh not between", value1, value2, "sfydzh");
            return (Criteria) this;
        }

        public Criteria andFlagIsNull() {
            addCriterion("flag is null");
            return (Criteria) this;
        }

        public Criteria andFlagIsNotNull() {
            addCriterion("flag is not null");
            return (Criteria) this;
        }

        public Criteria andFlagEqualTo(String value) {
            addCriterion("flag =", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotEqualTo(String value) {
            addCriterion("flag <>", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThan(String value) {
            addCriterion("flag >", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThanOrEqualTo(String value) {
            addCriterion("flag >=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThan(String value) {
            addCriterion("flag <", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThanOrEqualTo(String value) {
            addCriterion("flag <=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLike(String value) {
            addCriterion("flag like", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotLike(String value) {
            addCriterion("flag not like", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagIn(List<String> values) {
            addCriterion("flag in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotIn(List<String> values) {
            addCriterion("flag not in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagBetween(String value1, String value2) {
            addCriterion("flag between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotBetween(String value1, String value2) {
            addCriterion("flag not between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andNumIsNull() {
            addCriterion("num is null");
            return (Criteria) this;
        }

        public Criteria andNumIsNotNull() {
            addCriterion("num is not null");
            return (Criteria) this;
        }

        public Criteria andNumEqualTo(Integer value) {
            addCriterion("num =", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotEqualTo(Integer value) {
            addCriterion("num <>", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThan(Integer value) {
            addCriterion("num >", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("num >=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThan(Integer value) {
            addCriterion("num <", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumLessThanOrEqualTo(Integer value) {
            addCriterion("num <=", value, "num");
            return (Criteria) this;
        }

        public Criteria andNumIn(List<Integer> values) {
            addCriterion("num in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotIn(List<Integer> values) {
            addCriterion("num not in", values, "num");
            return (Criteria) this;
        }

        public Criteria andNumBetween(Integer value1, Integer value2) {
            addCriterion("num between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andNumNotBetween(Integer value1, Integer value2) {
            addCriterion("num not between", value1, value2, "num");
            return (Criteria) this;
        }

        public Criteria andJgjpIsNull() {
            addCriterion("jgjp is null");
            return (Criteria) this;
        }

        public Criteria andJgjpIsNotNull() {
            addCriterion("jgjp is not null");
            return (Criteria) this;
        }

        public Criteria andJgjpEqualTo(String value) {
            addCriterion("jgjp =", value, "jgjp");
            return (Criteria) this;
        }

        public Criteria andJgjpNotEqualTo(String value) {
            addCriterion("jgjp <>", value, "jgjp");
            return (Criteria) this;
        }

        public Criteria andJgjpGreaterThan(String value) {
            addCriterion("jgjp >", value, "jgjp");
            return (Criteria) this;
        }

        public Criteria andJgjpGreaterThanOrEqualTo(String value) {
            addCriterion("jgjp >=", value, "jgjp");
            return (Criteria) this;
        }

        public Criteria andJgjpLessThan(String value) {
            addCriterion("jgjp <", value, "jgjp");
            return (Criteria) this;
        }

        public Criteria andJgjpLessThanOrEqualTo(String value) {
            addCriterion("jgjp <=", value, "jgjp");
            return (Criteria) this;
        }

        public Criteria andJgjpLike(String value) {
            addCriterion("jgjp like", value, "jgjp");
            return (Criteria) this;
        }

        public Criteria andJgjpNotLike(String value) {
            addCriterion("jgjp not like", value, "jgjp");
            return (Criteria) this;
        }

        public Criteria andJgjpIn(List<String> values) {
            addCriterion("jgjp in", values, "jgjp");
            return (Criteria) this;
        }

        public Criteria andJgjpNotIn(List<String> values) {
            addCriterion("jgjp not in", values, "jgjp");
            return (Criteria) this;
        }

        public Criteria andJgjpBetween(String value1, String value2) {
            addCriterion("jgjp between", value1, value2, "jgjp");
            return (Criteria) this;
        }

        public Criteria andJgjpNotBetween(String value1, String value2) {
            addCriterion("jgjp not between", value1, value2, "jgjp");
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