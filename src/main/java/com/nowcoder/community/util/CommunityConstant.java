package com.nowcoder.community.util;

public interface CommunityConstant {

    //激活成果
    int ACTIVATION_SUCCESS =0;

    //重复激活
    int ACTIVATION_REPEAT=1;

    //激活失败
    int ACTIVATION_FAILURE=2;

    //默认状态 登录状态超时时间(s)
    int DEFAULT_EXPIRED_SECONDS=12* 3600;

    //Rememberme状态，登录状态超时时间（s）
    int REMEMBER_EXPIRED_SECONDS=60*60*24*100;

}
