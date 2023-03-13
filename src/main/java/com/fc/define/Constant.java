package com.fc.define;

/**
 * 常量
 *
 * @author czx
 * @email object_czx@163.com
 * @date 2016年11月15日 下午1:23:52
 */
public class Constant {

    public static final String NUMBER_CODE_KEY = "demo:number:code:";
    public static final String MOBILE_CODE_KEY = "demo:mobile:code:";
    public static final String AUTHENTICATION_TOKEN = "demo:token:";

    public static final String TOKEN = "token";
    public static final String TOKEN_ENTRY_POINT_URL = "/auth/login";
    public static final String TOKEN_LOGOUT_URL = "/auth/logout";
    public static final int TOKEN_EXPIRE = 60 * 60 * 24 * 7;

	/** 超级管理员ID */
	public static final String SUPER_ADMIN = "0";

	public static final int CODE_SIZE = 4;

	/**
	 * 菜单类型
	 *
	 * @author czx
	 * @email object_czx@163.com
	 * @date 2016年11月15日 下午1:24:29
	 */
    public enum MenuType {
        /**
         * 目录
         */
    	CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 云服务商
     */
    public enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1),
        /**
         * 阿里云
         */
        ALIYUN(2),
        /**
         * 腾讯云
         */
        QCLOUD(3),
        /**
         * minio
         */
        MINIO(4);

        private int value;

        CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
