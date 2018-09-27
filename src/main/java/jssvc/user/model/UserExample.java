package jssvc.user.model;

import java.util.ArrayList;
import java.util.List;

public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserExample() {
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

        public Criteria andDahIsNull() {
            addCriterion("dah is null");
            return (Criteria) this;
        }

        public Criteria andDahIsNotNull() {
            addCriterion("dah is not null");
            return (Criteria) this;
        }

        public Criteria andDahEqualTo(String value) {
            addCriterion("dah =", value, "dah");
            return (Criteria) this;
        }

        public Criteria andDahNotEqualTo(String value) {
            addCriterion("dah <>", value, "dah");
            return (Criteria) this;
        }

        public Criteria andDahGreaterThan(String value) {
            addCriterion("dah >", value, "dah");
            return (Criteria) this;
        }

        public Criteria andDahGreaterThanOrEqualTo(String value) {
            addCriterion("dah >=", value, "dah");
            return (Criteria) this;
        }

        public Criteria andDahLessThan(String value) {
            addCriterion("dah <", value, "dah");
            return (Criteria) this;
        }

        public Criteria andDahLessThanOrEqualTo(String value) {
            addCriterion("dah <=", value, "dah");
            return (Criteria) this;
        }

        public Criteria andDahLike(String value) {
            addCriterion("dah like", value, "dah");
            return (Criteria) this;
        }

        public Criteria andDahNotLike(String value) {
            addCriterion("dah not like", value, "dah");
            return (Criteria) this;
        }

        public Criteria andDahIn(List<String> values) {
            addCriterion("dah in", values, "dah");
            return (Criteria) this;
        }

        public Criteria andDahNotIn(List<String> values) {
            addCriterion("dah not in", values, "dah");
            return (Criteria) this;
        }

        public Criteria andDahBetween(String value1, String value2) {
            addCriterion("dah between", value1, value2, "dah");
            return (Criteria) this;
        }

        public Criteria andDahNotBetween(String value1, String value2) {
            addCriterion("dah not between", value1, value2, "dah");
            return (Criteria) this;
        }

        public Criteria andYgxmIsNull() {
            addCriterion("ygxm is null");
            return (Criteria) this;
        }

        public Criteria andYgxmIsNotNull() {
            addCriterion("ygxm is not null");
            return (Criteria) this;
        }

        public Criteria andYgxmEqualTo(String value) {
            addCriterion("ygxm =", value, "ygxm");
            return (Criteria) this;
        }

        public Criteria andYgxmNotEqualTo(String value) {
            addCriterion("ygxm <>", value, "ygxm");
            return (Criteria) this;
        }

        public Criteria andYgxmGreaterThan(String value) {
            addCriterion("ygxm >", value, "ygxm");
            return (Criteria) this;
        }

        public Criteria andYgxmGreaterThanOrEqualTo(String value) {
            addCriterion("ygxm >=", value, "ygxm");
            return (Criteria) this;
        }

        public Criteria andYgxmLessThan(String value) {
            addCriterion("ygxm <", value, "ygxm");
            return (Criteria) this;
        }

        public Criteria andYgxmLessThanOrEqualTo(String value) {
            addCriterion("ygxm <=", value, "ygxm");
            return (Criteria) this;
        }

        public Criteria andYgxmLike(String value) {
            addCriterion("ygxm like", value, "ygxm");
            return (Criteria) this;
        }

        public Criteria andYgxmNotLike(String value) {
            addCriterion("ygxm not like", value, "ygxm");
            return (Criteria) this;
        }

        public Criteria andYgxmIn(List<String> values) {
            addCriterion("ygxm in", values, "ygxm");
            return (Criteria) this;
        }

        public Criteria andYgxmNotIn(List<String> values) {
            addCriterion("ygxm not in", values, "ygxm");
            return (Criteria) this;
        }

        public Criteria andYgxmBetween(String value1, String value2) {
            addCriterion("ygxm between", value1, value2, "ygxm");
            return (Criteria) this;
        }

        public Criteria andYgxmNotBetween(String value1, String value2) {
            addCriterion("ygxm not between", value1, value2, "ygxm");
            return (Criteria) this;
        }

        public Criteria andMjkkhIsNull() {
            addCriterion("mjkkh is null");
            return (Criteria) this;
        }

        public Criteria andMjkkhIsNotNull() {
            addCriterion("mjkkh is not null");
            return (Criteria) this;
        }

        public Criteria andMjkkhEqualTo(String value) {
            addCriterion("mjkkh =", value, "mjkkh");
            return (Criteria) this;
        }

        public Criteria andMjkkhNotEqualTo(String value) {
            addCriterion("mjkkh <>", value, "mjkkh");
            return (Criteria) this;
        }

        public Criteria andMjkkhGreaterThan(String value) {
            addCriterion("mjkkh >", value, "mjkkh");
            return (Criteria) this;
        }

        public Criteria andMjkkhGreaterThanOrEqualTo(String value) {
            addCriterion("mjkkh >=", value, "mjkkh");
            return (Criteria) this;
        }

        public Criteria andMjkkhLessThan(String value) {
            addCriterion("mjkkh <", value, "mjkkh");
            return (Criteria) this;
        }

        public Criteria andMjkkhLessThanOrEqualTo(String value) {
            addCriterion("mjkkh <=", value, "mjkkh");
            return (Criteria) this;
        }

        public Criteria andMjkkhLike(String value) {
            addCriterion("mjkkh like", value, "mjkkh");
            return (Criteria) this;
        }

        public Criteria andMjkkhNotLike(String value) {
            addCriterion("mjkkh not like", value, "mjkkh");
            return (Criteria) this;
        }

        public Criteria andMjkkhIn(List<String> values) {
            addCriterion("mjkkh in", values, "mjkkh");
            return (Criteria) this;
        }

        public Criteria andMjkkhNotIn(List<String> values) {
            addCriterion("mjkkh not in", values, "mjkkh");
            return (Criteria) this;
        }

        public Criteria andMjkkhBetween(String value1, String value2) {
            addCriterion("mjkkh between", value1, value2, "mjkkh");
            return (Criteria) this;
        }

        public Criteria andMjkkhNotBetween(String value1, String value2) {
            addCriterion("mjkkh not between", value1, value2, "mjkkh");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(String value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(String value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(String value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(String value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(String value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(String value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLike(String value) {
            addCriterion("sex like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotLike(String value) {
            addCriterion("sex not like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<String> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<String> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(String value1, String value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(String value1, String value2) {
            addCriterion("sex not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("mobile like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andSfzhIsNull() {
            addCriterion("sfzh is null");
            return (Criteria) this;
        }

        public Criteria andSfzhIsNotNull() {
            addCriterion("sfzh is not null");
            return (Criteria) this;
        }

        public Criteria andSfzhEqualTo(String value) {
            addCriterion("sfzh =", value, "sfzh");
            return (Criteria) this;
        }

        public Criteria andSfzhNotEqualTo(String value) {
            addCriterion("sfzh <>", value, "sfzh");
            return (Criteria) this;
        }

        public Criteria andSfzhGreaterThan(String value) {
            addCriterion("sfzh >", value, "sfzh");
            return (Criteria) this;
        }

        public Criteria andSfzhGreaterThanOrEqualTo(String value) {
            addCriterion("sfzh >=", value, "sfzh");
            return (Criteria) this;
        }

        public Criteria andSfzhLessThan(String value) {
            addCriterion("sfzh <", value, "sfzh");
            return (Criteria) this;
        }

        public Criteria andSfzhLessThanOrEqualTo(String value) {
            addCriterion("sfzh <=", value, "sfzh");
            return (Criteria) this;
        }

        public Criteria andSfzhLike(String value) {
            addCriterion("sfzh like", value, "sfzh");
            return (Criteria) this;
        }

        public Criteria andSfzhNotLike(String value) {
            addCriterion("sfzh not like", value, "sfzh");
            return (Criteria) this;
        }

        public Criteria andSfzhIn(List<String> values) {
            addCriterion("sfzh in", values, "sfzh");
            return (Criteria) this;
        }

        public Criteria andSfzhNotIn(List<String> values) {
            addCriterion("sfzh not in", values, "sfzh");
            return (Criteria) this;
        }

        public Criteria andSfzhBetween(String value1, String value2) {
            addCriterion("sfzh between", value1, value2, "sfzh");
            return (Criteria) this;
        }

        public Criteria andSfzhNotBetween(String value1, String value2) {
            addCriterion("sfzh not between", value1, value2, "sfzh");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andGwdjIsNull() {
            addCriterion("gwdj is null");
            return (Criteria) this;
        }

        public Criteria andGwdjIsNotNull() {
            addCriterion("gwdj is not null");
            return (Criteria) this;
        }

        public Criteria andGwdjEqualTo(String value) {
            addCriterion("gwdj =", value, "gwdj");
            return (Criteria) this;
        }

        public Criteria andGwdjNotEqualTo(String value) {
            addCriterion("gwdj <>", value, "gwdj");
            return (Criteria) this;
        }

        public Criteria andGwdjGreaterThan(String value) {
            addCriterion("gwdj >", value, "gwdj");
            return (Criteria) this;
        }

        public Criteria andGwdjGreaterThanOrEqualTo(String value) {
            addCriterion("gwdj >=", value, "gwdj");
            return (Criteria) this;
        }

        public Criteria andGwdjLessThan(String value) {
            addCriterion("gwdj <", value, "gwdj");
            return (Criteria) this;
        }

        public Criteria andGwdjLessThanOrEqualTo(String value) {
            addCriterion("gwdj <=", value, "gwdj");
            return (Criteria) this;
        }

        public Criteria andGwdjLike(String value) {
            addCriterion("gwdj like", value, "gwdj");
            return (Criteria) this;
        }

        public Criteria andGwdjNotLike(String value) {
            addCriterion("gwdj not like", value, "gwdj");
            return (Criteria) this;
        }

        public Criteria andGwdjIn(List<String> values) {
            addCriterion("gwdj in", values, "gwdj");
            return (Criteria) this;
        }

        public Criteria andGwdjNotIn(List<String> values) {
            addCriterion("gwdj not in", values, "gwdj");
            return (Criteria) this;
        }

        public Criteria andGwdjBetween(String value1, String value2) {
            addCriterion("gwdj between", value1, value2, "gwdj");
            return (Criteria) this;
        }

        public Criteria andGwdjNotBetween(String value1, String value2) {
            addCriterion("gwdj not between", value1, value2, "gwdj");
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

        public Criteria andBzIsNull() {
            addCriterion("bz is null");
            return (Criteria) this;
        }

        public Criteria andBzIsNotNull() {
            addCriterion("bz is not null");
            return (Criteria) this;
        }

        public Criteria andBzEqualTo(String value) {
            addCriterion("bz =", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzNotEqualTo(String value) {
            addCriterion("bz <>", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzGreaterThan(String value) {
            addCriterion("bz >", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzGreaterThanOrEqualTo(String value) {
            addCriterion("bz >=", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzLessThan(String value) {
            addCriterion("bz <", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzLessThanOrEqualTo(String value) {
            addCriterion("bz <=", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzLike(String value) {
            addCriterion("bz like", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzNotLike(String value) {
            addCriterion("bz not like", value, "bz");
            return (Criteria) this;
        }

        public Criteria andBzIn(List<String> values) {
            addCriterion("bz in", values, "bz");
            return (Criteria) this;
        }

        public Criteria andBzNotIn(List<String> values) {
            addCriterion("bz not in", values, "bz");
            return (Criteria) this;
        }

        public Criteria andBzBetween(String value1, String value2) {
            addCriterion("bz between", value1, value2, "bz");
            return (Criteria) this;
        }

        public Criteria andBzNotBetween(String value1, String value2) {
            addCriterion("bz not between", value1, value2, "bz");
            return (Criteria) this;
        }

        public Criteria andPysxIsNull() {
            addCriterion("pysx is null");
            return (Criteria) this;
        }

        public Criteria andPysxIsNotNull() {
            addCriterion("pysx is not null");
            return (Criteria) this;
        }

        public Criteria andPysxEqualTo(String value) {
            addCriterion("pysx =", value, "pysx");
            return (Criteria) this;
        }

        public Criteria andPysxNotEqualTo(String value) {
            addCriterion("pysx <>", value, "pysx");
            return (Criteria) this;
        }

        public Criteria andPysxGreaterThan(String value) {
            addCriterion("pysx >", value, "pysx");
            return (Criteria) this;
        }

        public Criteria andPysxGreaterThanOrEqualTo(String value) {
            addCriterion("pysx >=", value, "pysx");
            return (Criteria) this;
        }

        public Criteria andPysxLessThan(String value) {
            addCriterion("pysx <", value, "pysx");
            return (Criteria) this;
        }

        public Criteria andPysxLessThanOrEqualTo(String value) {
            addCriterion("pysx <=", value, "pysx");
            return (Criteria) this;
        }

        public Criteria andPysxLike(String value) {
            addCriterion("pysx like", value, "pysx");
            return (Criteria) this;
        }

        public Criteria andPysxNotLike(String value) {
            addCriterion("pysx not like", value, "pysx");
            return (Criteria) this;
        }

        public Criteria andPysxIn(List<String> values) {
            addCriterion("pysx in", values, "pysx");
            return (Criteria) this;
        }

        public Criteria andPysxNotIn(List<String> values) {
            addCriterion("pysx not in", values, "pysx");
            return (Criteria) this;
        }

        public Criteria andPysxBetween(String value1, String value2) {
            addCriterion("pysx between", value1, value2, "pysx");
            return (Criteria) this;
        }

        public Criteria andPysxNotBetween(String value1, String value2) {
            addCriterion("pysx not between", value1, value2, "pysx");
            return (Criteria) this;
        }

        public Criteria andPyqpIsNull() {
            addCriterion("pyqp is null");
            return (Criteria) this;
        }

        public Criteria andPyqpIsNotNull() {
            addCriterion("pyqp is not null");
            return (Criteria) this;
        }

        public Criteria andPyqpEqualTo(String value) {
            addCriterion("pyqp =", value, "pyqp");
            return (Criteria) this;
        }

        public Criteria andPyqpNotEqualTo(String value) {
            addCriterion("pyqp <>", value, "pyqp");
            return (Criteria) this;
        }

        public Criteria andPyqpGreaterThan(String value) {
            addCriterion("pyqp >", value, "pyqp");
            return (Criteria) this;
        }

        public Criteria andPyqpGreaterThanOrEqualTo(String value) {
            addCriterion("pyqp >=", value, "pyqp");
            return (Criteria) this;
        }

        public Criteria andPyqpLessThan(String value) {
            addCriterion("pyqp <", value, "pyqp");
            return (Criteria) this;
        }

        public Criteria andPyqpLessThanOrEqualTo(String value) {
            addCriterion("pyqp <=", value, "pyqp");
            return (Criteria) this;
        }

        public Criteria andPyqpLike(String value) {
            addCriterion("pyqp like", value, "pyqp");
            return (Criteria) this;
        }

        public Criteria andPyqpNotLike(String value) {
            addCriterion("pyqp not like", value, "pyqp");
            return (Criteria) this;
        }

        public Criteria andPyqpIn(List<String> values) {
            addCriterion("pyqp in", values, "pyqp");
            return (Criteria) this;
        }

        public Criteria andPyqpNotIn(List<String> values) {
            addCriterion("pyqp not in", values, "pyqp");
            return (Criteria) this;
        }

        public Criteria andPyqpBetween(String value1, String value2) {
            addCriterion("pyqp between", value1, value2, "pyqp");
            return (Criteria) this;
        }

        public Criteria andPyqpNotBetween(String value1, String value2) {
            addCriterion("pyqp not between", value1, value2, "pyqp");
            return (Criteria) this;
        }

        public Criteria andPxIsNull() {
            addCriterion("px is null");
            return (Criteria) this;
        }

        public Criteria andPxIsNotNull() {
            addCriterion("px is not null");
            return (Criteria) this;
        }

        public Criteria andPxEqualTo(String value) {
            addCriterion("px =", value, "px");
            return (Criteria) this;
        }

        public Criteria andPxNotEqualTo(String value) {
            addCriterion("px <>", value, "px");
            return (Criteria) this;
        }

        public Criteria andPxGreaterThan(String value) {
            addCriterion("px >", value, "px");
            return (Criteria) this;
        }

        public Criteria andPxGreaterThanOrEqualTo(String value) {
            addCriterion("px >=", value, "px");
            return (Criteria) this;
        }

        public Criteria andPxLessThan(String value) {
            addCriterion("px <", value, "px");
            return (Criteria) this;
        }

        public Criteria andPxLessThanOrEqualTo(String value) {
            addCriterion("px <=", value, "px");
            return (Criteria) this;
        }

        public Criteria andPxLike(String value) {
            addCriterion("px like", value, "px");
            return (Criteria) this;
        }

        public Criteria andPxNotLike(String value) {
            addCriterion("px not like", value, "px");
            return (Criteria) this;
        }

        public Criteria andPxIn(List<String> values) {
            addCriterion("px in", values, "px");
            return (Criteria) this;
        }

        public Criteria andPxNotIn(List<String> values) {
            addCriterion("px not in", values, "px");
            return (Criteria) this;
        }

        public Criteria andPxBetween(String value1, String value2) {
            addCriterion("px between", value1, value2, "px");
            return (Criteria) this;
        }

        public Criteria andPxNotBetween(String value1, String value2) {
            addCriterion("px not between", value1, value2, "px");
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