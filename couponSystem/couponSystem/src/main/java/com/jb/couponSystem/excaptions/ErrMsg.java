package com.jb.couponSystem.excaptions;

public enum ErrMsg {
    COMPANY_NAME_EXIST("cannot add company with exiting company name"),
    COMPANY_EMAIL_EXIST("cannot add company with exiting company email"),
    COMPANY_ID_NOT_EXIST("cannot update company with exiting non exist id"),
    COMPANY_NAME_IS_NOT_VALID("cannot update company, this company id has other name"),
    COMPANY_IS_NOT_EXIST("cannot find this company"),
    COMPANY_WITH_SAME_TITLE("cannot inset coupon with this exiting title"),
    COUPON_OF_OTHER_COMPANY("cannot do operation on coupon of other company"),
    CUSTOMER_IS_NOT_EXIST("cannot find this customer"),
    CUSTOMER_CAN_BUY_ONLY_ONE_COUPON("Each customer can buy only one coupon..."),
    COUPON_OUT_OF_THE_STOCK("Sorry, The coupons are out of stock"),
    COUPON_EXPIRY_DATE("Sorry, The coupon`s date is expired"),
    CUSTOMER_EXIST("cannot insert customer with same email");

    private String desc;

    ErrMsg(String desc){
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
