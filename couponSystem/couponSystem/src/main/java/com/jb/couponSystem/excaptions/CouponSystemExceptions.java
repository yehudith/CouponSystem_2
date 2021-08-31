package com.jb.couponSystem.excaptions;

public class CouponSystemExceptions extends  Exception{

    public CouponSystemExceptions(ErrMsg errMsg){
        super(errMsg.getDesc());
    }

}
