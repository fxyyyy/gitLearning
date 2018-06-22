package com.fxy.bean;

import java.util.ArrayList;
import java.util.Date;
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
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

        public Criteria andArticlesIsNull() {
            addCriterion("articles is null");
            return (Criteria) this;
        }

        public Criteria andArticlesIsNotNull() {
            addCriterion("articles is not null");
            return (Criteria) this;
        }

        public Criteria andArticlesEqualTo(Integer value) {
            addCriterion("articles =", value, "articles");
            return (Criteria) this;
        }

        public Criteria andArticlesNotEqualTo(Integer value) {
            addCriterion("articles <>", value, "articles");
            return (Criteria) this;
        }

        public Criteria andArticlesGreaterThan(Integer value) {
            addCriterion("articles >", value, "articles");
            return (Criteria) this;
        }

        public Criteria andArticlesGreaterThanOrEqualTo(Integer value) {
            addCriterion("articles >=", value, "articles");
            return (Criteria) this;
        }

        public Criteria andArticlesLessThan(Integer value) {
            addCriterion("articles <", value, "articles");
            return (Criteria) this;
        }

        public Criteria andArticlesLessThanOrEqualTo(Integer value) {
            addCriterion("articles <=", value, "articles");
            return (Criteria) this;
        }

        public Criteria andArticlesIn(List<Integer> values) {
            addCriterion("articles in", values, "articles");
            return (Criteria) this;
        }

        public Criteria andArticlesNotIn(List<Integer> values) {
            addCriterion("articles not in", values, "articles");
            return (Criteria) this;
        }

        public Criteria andArticlesBetween(Integer value1, Integer value2) {
            addCriterion("articles between", value1, value2, "articles");
            return (Criteria) this;
        }

        public Criteria andArticlesNotBetween(Integer value1, Integer value2) {
            addCriterion("articles not between", value1, value2, "articles");
            return (Criteria) this;
        }

        public Criteria andVideosIsNull() {
            addCriterion("videos is null");
            return (Criteria) this;
        }

        public Criteria andVideosIsNotNull() {
            addCriterion("videos is not null");
            return (Criteria) this;
        }

        public Criteria andVideosEqualTo(Integer value) {
            addCriterion("videos =", value, "videos");
            return (Criteria) this;
        }

        public Criteria andVideosNotEqualTo(Integer value) {
            addCriterion("videos <>", value, "videos");
            return (Criteria) this;
        }

        public Criteria andVideosGreaterThan(Integer value) {
            addCriterion("videos >", value, "videos");
            return (Criteria) this;
        }

        public Criteria andVideosGreaterThanOrEqualTo(Integer value) {
            addCriterion("videos >=", value, "videos");
            return (Criteria) this;
        }

        public Criteria andVideosLessThan(Integer value) {
            addCriterion("videos <", value, "videos");
            return (Criteria) this;
        }

        public Criteria andVideosLessThanOrEqualTo(Integer value) {
            addCriterion("videos <=", value, "videos");
            return (Criteria) this;
        }

        public Criteria andVideosIn(List<Integer> values) {
            addCriterion("videos in", values, "videos");
            return (Criteria) this;
        }

        public Criteria andVideosNotIn(List<Integer> values) {
            addCriterion("videos not in", values, "videos");
            return (Criteria) this;
        }

        public Criteria andVideosBetween(Integer value1, Integer value2) {
            addCriterion("videos between", value1, value2, "videos");
            return (Criteria) this;
        }

        public Criteria andVideosNotBetween(Integer value1, Integer value2) {
            addCriterion("videos not between", value1, value2, "videos");
            return (Criteria) this;
        }

        public Criteria andInformsIsNull() {
            addCriterion("informs is null");
            return (Criteria) this;
        }

        public Criteria andInformsIsNotNull() {
            addCriterion("informs is not null");
            return (Criteria) this;
        }

        public Criteria andInformsEqualTo(Integer value) {
            addCriterion("informs =", value, "informs");
            return (Criteria) this;
        }

        public Criteria andInformsNotEqualTo(Integer value) {
            addCriterion("informs <>", value, "informs");
            return (Criteria) this;
        }

        public Criteria andInformsGreaterThan(Integer value) {
            addCriterion("informs >", value, "informs");
            return (Criteria) this;
        }

        public Criteria andInformsGreaterThanOrEqualTo(Integer value) {
            addCriterion("informs >=", value, "informs");
            return (Criteria) this;
        }

        public Criteria andInformsLessThan(Integer value) {
            addCriterion("informs <", value, "informs");
            return (Criteria) this;
        }

        public Criteria andInformsLessThanOrEqualTo(Integer value) {
            addCriterion("informs <=", value, "informs");
            return (Criteria) this;
        }

        public Criteria andInformsIn(List<Integer> values) {
            addCriterion("informs in", values, "informs");
            return (Criteria) this;
        }

        public Criteria andInformsNotIn(List<Integer> values) {
            addCriterion("informs not in", values, "informs");
            return (Criteria) this;
        }

        public Criteria andInformsBetween(Integer value1, Integer value2) {
            addCriterion("informs between", value1, value2, "informs");
            return (Criteria) this;
        }

        public Criteria andInformsNotBetween(Integer value1, Integer value2) {
            addCriterion("informs not between", value1, value2, "informs");
            return (Criteria) this;
        }

        public Criteria andPracticesIsNull() {
            addCriterion("practices is null");
            return (Criteria) this;
        }

        public Criteria andPracticesIsNotNull() {
            addCriterion("practices is not null");
            return (Criteria) this;
        }

        public Criteria andPracticesEqualTo(Integer value) {
            addCriterion("practices =", value, "practices");
            return (Criteria) this;
        }

        public Criteria andPracticesNotEqualTo(Integer value) {
            addCriterion("practices <>", value, "practices");
            return (Criteria) this;
        }

        public Criteria andPracticesGreaterThan(Integer value) {
            addCriterion("practices >", value, "practices");
            return (Criteria) this;
        }

        public Criteria andPracticesGreaterThanOrEqualTo(Integer value) {
            addCriterion("practices >=", value, "practices");
            return (Criteria) this;
        }

        public Criteria andPracticesLessThan(Integer value) {
            addCriterion("practices <", value, "practices");
            return (Criteria) this;
        }

        public Criteria andPracticesLessThanOrEqualTo(Integer value) {
            addCriterion("practices <=", value, "practices");
            return (Criteria) this;
        }

        public Criteria andPracticesIn(List<Integer> values) {
            addCriterion("practices in", values, "practices");
            return (Criteria) this;
        }

        public Criteria andPracticesNotIn(List<Integer> values) {
            addCriterion("practices not in", values, "practices");
            return (Criteria) this;
        }

        public Criteria andPracticesBetween(Integer value1, Integer value2) {
            addCriterion("practices between", value1, value2, "practices");
            return (Criteria) this;
        }

        public Criteria andPracticesNotBetween(Integer value1, Integer value2) {
            addCriterion("practices not between", value1, value2, "practices");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNull() {
            addCriterion("role_id is null");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNotNull() {
            addCriterion("role_id is not null");
            return (Criteria) this;
        }

        public Criteria andRoleIdEqualTo(Integer value) {
            addCriterion("role_id =", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotEqualTo(Integer value) {
            addCriterion("role_id <>", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThan(Integer value) {
            addCriterion("role_id >", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("role_id >=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThan(Integer value) {
            addCriterion("role_id <", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThanOrEqualTo(Integer value) {
            addCriterion("role_id <=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdIn(List<Integer> values) {
            addCriterion("role_id in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotIn(List<Integer> values) {
            addCriterion("role_id not in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdBetween(Integer value1, Integer value2) {
            addCriterion("role_id between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotBetween(Integer value1, Integer value2) {
            addCriterion("role_id not between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andMissNumberIsNull() {
            addCriterion("miss_number is null");
            return (Criteria) this;
        }

        public Criteria andMissNumberIsNotNull() {
            addCriterion("miss_number is not null");
            return (Criteria) this;
        }

        public Criteria andMissNumberEqualTo(Integer value) {
            addCriterion("miss_number =", value, "missNumber");
            return (Criteria) this;
        }

        public Criteria andMissNumberNotEqualTo(Integer value) {
            addCriterion("miss_number <>", value, "missNumber");
            return (Criteria) this;
        }

        public Criteria andMissNumberGreaterThan(Integer value) {
            addCriterion("miss_number >", value, "missNumber");
            return (Criteria) this;
        }

        public Criteria andMissNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("miss_number >=", value, "missNumber");
            return (Criteria) this;
        }

        public Criteria andMissNumberLessThan(Integer value) {
            addCriterion("miss_number <", value, "missNumber");
            return (Criteria) this;
        }

        public Criteria andMissNumberLessThanOrEqualTo(Integer value) {
            addCriterion("miss_number <=", value, "missNumber");
            return (Criteria) this;
        }

        public Criteria andMissNumberIn(List<Integer> values) {
            addCriterion("miss_number in", values, "missNumber");
            return (Criteria) this;
        }

        public Criteria andMissNumberNotIn(List<Integer> values) {
            addCriterion("miss_number not in", values, "missNumber");
            return (Criteria) this;
        }

        public Criteria andMissNumberBetween(Integer value1, Integer value2) {
            addCriterion("miss_number between", value1, value2, "missNumber");
            return (Criteria) this;
        }

        public Criteria andMissNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("miss_number not between", value1, value2, "missNumber");
            return (Criteria) this;
        }

        public Criteria andMissTimeIsNull() {
            addCriterion("miss_time is null");
            return (Criteria) this;
        }

        public Criteria andMissTimeIsNotNull() {
            addCriterion("miss_time is not null");
            return (Criteria) this;
        }

        public Criteria andMissTimeEqualTo(Date value) {
            addCriterion("miss_time =", value, "missTime");
            return (Criteria) this;
        }

        public Criteria andMissTimeNotEqualTo(Date value) {
            addCriterion("miss_time <>", value, "missTime");
            return (Criteria) this;
        }

        public Criteria andMissTimeGreaterThan(Date value) {
            addCriterion("miss_time >", value, "missTime");
            return (Criteria) this;
        }

        public Criteria andMissTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("miss_time >=", value, "missTime");
            return (Criteria) this;
        }

        public Criteria andMissTimeLessThan(Date value) {
            addCriterion("miss_time <", value, "missTime");
            return (Criteria) this;
        }

        public Criteria andMissTimeLessThanOrEqualTo(Date value) {
            addCriterion("miss_time <=", value, "missTime");
            return (Criteria) this;
        }

        public Criteria andMissTimeIn(List<Date> values) {
            addCriterion("miss_time in", values, "missTime");
            return (Criteria) this;
        }

        public Criteria andMissTimeNotIn(List<Date> values) {
            addCriterion("miss_time not in", values, "missTime");
            return (Criteria) this;
        }

        public Criteria andMissTimeBetween(Date value1, Date value2) {
            addCriterion("miss_time between", value1, value2, "missTime");
            return (Criteria) this;
        }

        public Criteria andMissTimeNotBetween(Date value1, Date value2) {
            addCriterion("miss_time not between", value1, value2, "missTime");
            return (Criteria) this;
        }

        public Criteria andAllowTimeIsNull() {
            addCriterion("allow_time is null");
            return (Criteria) this;
        }

        public Criteria andAllowTimeIsNotNull() {
            addCriterion("allow_time is not null");
            return (Criteria) this;
        }

        public Criteria andAllowTimeEqualTo(Date value) {
            addCriterion("allow_time =", value, "allowTime");
            return (Criteria) this;
        }

        public Criteria andAllowTimeNotEqualTo(Date value) {
            addCriterion("allow_time <>", value, "allowTime");
            return (Criteria) this;
        }

        public Criteria andAllowTimeGreaterThan(Date value) {
            addCriterion("allow_time >", value, "allowTime");
            return (Criteria) this;
        }

        public Criteria andAllowTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("allow_time >=", value, "allowTime");
            return (Criteria) this;
        }

        public Criteria andAllowTimeLessThan(Date value) {
            addCriterion("allow_time <", value, "allowTime");
            return (Criteria) this;
        }

        public Criteria andAllowTimeLessThanOrEqualTo(Date value) {
            addCriterion("allow_time <=", value, "allowTime");
            return (Criteria) this;
        }

        public Criteria andAllowTimeIn(List<Date> values) {
            addCriterion("allow_time in", values, "allowTime");
            return (Criteria) this;
        }

        public Criteria andAllowTimeNotIn(List<Date> values) {
            addCriterion("allow_time not in", values, "allowTime");
            return (Criteria) this;
        }

        public Criteria andAllowTimeBetween(Date value1, Date value2) {
            addCriterion("allow_time between", value1, value2, "allowTime");
            return (Criteria) this;
        }

        public Criteria andAllowTimeNotBetween(Date value1, Date value2) {
            addCriterion("allow_time not between", value1, value2, "allowTime");
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