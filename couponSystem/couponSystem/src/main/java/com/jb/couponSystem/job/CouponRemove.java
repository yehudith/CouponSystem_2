package com.jb.couponSystem.job;

import com.jb.couponSystem.beans.Coupon;
import com.jb.couponSystem.repoos.CouponRepository;
import com.jb.couponSystem.utils.PrintUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CouponRemove {

    private final CouponRepository couponRepository;
    private static final int DAY = 1000 * 60 * 60 * 24;
    private static final int Sec5 = 1000 * 5;

    @Scheduled(fixedRate = Sec5)
    @Transactional
    public void deleteCoupon() {

        System.out.println("===============JOB DELETE EXPIRY COUPON===============");

        List<Coupon> expiryCoupon = couponRepository.findByEndDateIsBefore(Date.valueOf(LocalDate.now()));

        if(expiryCoupon == null) {
            System.out.println("All Coupons are available");
            return;
        }

        System.out.println("There is " + expiryCoupon.size() + " that expiry dates");

        for (Coupon coupon: expiryCoupon) {
            System.out.println(coupon.getId() + " " +coupon.getEndDate());

            couponRepository.deleteCouponsPurchase(coupon.getId());
            couponRepository.deleteCoupon(coupon.getId());
        }

        PrintUtils.print(couponRepository.findAll());
    }
}
