package com.jb.couponSystem.excep;

public class CouponSystemException extends  Exception{

    public CouponSystemException(ErrorMessage errMsg){
        super(errMsg.getDesc());
    }

}
