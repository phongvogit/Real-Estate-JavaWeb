package com.laptrinhjavaweb.constant;

public class SystemConstant {
    public static final int ACTIVE_STATUS = 1;
    public static final int INACTIVE_STATUS = 0;

    //public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 3600000;//1h
    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 30000;
    public static final String SIGNING_KEY = "laptrinhjavaweb";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTHORITIES_KEY = "scopes";

    /*Spring security 4: ROLE_ADMIN, Spring security 3 not required*/
    public static final String ADMIN_ROLE = "ROLE_ADMIN";
    /*Spring security 4: ROLE_ADMIN, Spring security 3 not required*/
    public static final String USER_ROLE = "ROLE_USER";
    /*Spring security 4: ROLE_ADMIN, Spring security 3 not required*/
    public static final String MANAGER_ROLE = "ROLE_MANAGER";
    /*Spring security 4: ROLE_ADMIN, Spring security 3 not required*/
    public static final String STAFF_ROLE = "ROLE_STAFF";
    public static final String HOME = "/trang-chu";
    public static final String ADMIN_HOME = "/admin/home";
    public static final String MODEL = "model";
    public static final String INSERT_SUCCESS = "insert_success";
    public static final String UPDATE_SUCCESS = "update_success";
    public static final String DELETE_SUCCESS = "delete_success";
    public static final String ERROR_SYSTEM = "error_system";
    public static final String ALERT = "alert";
    public static final String MESSAGE_RESPONSE = "messageResponse";
    public static final String POST_IMAGE = "/post";

    //host post
    public static final String HOT_POST = "hotposts";
    public static final String HOT_POST_YES = "YES";

    public static final String JAVA_WEB_BASIC_ADVANTAGE = "khoa-hoc-java-web-co-ban-den-nang-cao";
    public static final String JAVA_WEB_BASIC = "khoa-hoc-java-web-co-ban-jsp-servlet-jdbc-va-mysql";
    public static final String HOME_PAGE = "trang-chu";
    public static final String PAYMENT = "thanh-toan";
    public static final String CONTACT = "lien-he";
    public static final String SPRING_MVC = "springmvc";
    public static final String SPRING_BOOT = "springboot";

    public static final String ADMIN_POST_LIST = "admin-post-list";
    public static final String BLOG = "blog";

    public static final String TALK_SEO_URL = "tam-su";
    public static final String CATEGORIES = "categories";

    //Dítrict
    public static final String districtName = "Quận 1,Quận 2,Quận Thủ Đức,Quận Bình Thạnh,Quận Tân Bình";
    public static final String districtCode = "district-1,district-2,thu-duc,binh-thanh,tan-binh";
    public static final String buildingTypeName = "Ground Floor,Detached House,Furnished House";
    public static final String buildingTypeCode = "ground-floor,detached-house,furnished-house";
}
