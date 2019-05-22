package com.wjx.travelwithm_master.ui.api;



import com.wjx.travelwithm_master.ui.bean.CircuitBean;
import com.wjx.travelwithm_master.ui.bean.LoginBean;
import com.wjx.travelwithm_master.ui.bean.MiListBean;

import io.reactivex.Observer;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    String banmiUrl="http://api.banmi.com/";

    /**
     * 微博登录
     * 参数： oAuthToken:”uid”,微博的uid
     * */
    @FormUrlEncoded
    @POST("api/3.0/account/login/oauth")
    @Headers("oAuthToken")
    Observer<LoginBean> getLoginBean();

    /**
     * 首页线路列表
     * 参数：banmi-app-token:登录后的token(请求头)
     * 例如：JVy0IvZamK7f7FBZLKFtoniiixKMlnnJ6dWZ6NlsY4HGsxcAA9qvFo8yacHCKHE8YAcd0UF9L59nEm7zk9AUixee0Hl8EeWA880c0ikZBW0KEYuxQy5Z9NP3BNoBi3o3Q0g
     */
    @GET("api/3.0/content/routesbundles")
    @Headers("banmi-app-token")
    Observer<CircuitBean> getCircuitData(@Query("page") int page);

    /**
     * 伴米列表
     * 参数：page,页面,从1开始
     *      banmi-app-token:登录后的token(请求头)
     */
    @GET("api/3.0/banmi")
    @Headers("banmi-app-token")
    Observer<MiListBean> getMiListData(@Query("page") int page);



}
