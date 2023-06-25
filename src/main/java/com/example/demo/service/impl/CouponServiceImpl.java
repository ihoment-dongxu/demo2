package com.example.demo.service.impl;

import com.example.demo.assembler.coupon.CouponConvert;
import com.example.demo.mapper.CouponMapper;
import com.example.demo.pojo.modle.Coupon;
import com.example.demo.pojo.request.coupon.CouponSaveRequest;
import com.example.demo.pojo.vo.coupon.CouponVO;
import com.example.demo.service.coupon.CouponService;
import com.example.demo.utils.CouponCodeUtils;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author dongxu
 * @create 2023-05-08 下午9:51
 */
@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Resource
    private CouponMapper couponMapper;

    @Resource(name = "couponThreadPoolExecutor")
    private ThreadPoolExecutor couponThreadPoolExecutor;

    @Override
    public Boolean insertCoupon(CouponSaveRequest request) {
        couponThreadPoolExecutor.execute(() -> {
            request.setCouponCode(CouponCodeUtils.generateCouponCode());
            Coupon coupon = CouponConvert.INSTANCE.convert(request);
            couponMapper.insert(coupon);
        });
        return Boolean.TRUE;
    }

    @Override
    public List<CouponVO> listCoupon() {
        List<Coupon> coupons = couponMapper.listCoupon();
        return CouponConvert.INSTANCE.convertList(coupons);
    }

    /**
     * SpringBoot+Mybatis 如何实现流式查询
     * https://mp.weixin.qq.com/s?__biz=MzUzMTA2NTU2Ng==&mid=2247573978&idx=1&sn=85deba768abbed1de16e8d7cd4947a8e&chksm=fa4bde6bcd3c577dbaab1c42dabb6f03ce66ec38bf39843599a46456b5675ff9f39ffbea16a6&scene=126&sessionid=1686537140#rd
     *
     * @return 优惠券列表
     * @tips 需要使用@Transactional
     */
    @Override
    @Transactional
    public List<CouponVO> selectByCursor() {
        Cursor<Coupon> couponCursor = couponMapper.selectByCursor();

        // 查询已读取数据在全部数据里的索引位置
        int currentIndex = couponCursor.getCurrentIndex();
        // 判断cursor是否正处于打开状态;
        boolean open = couponCursor.isOpen();
        // 判断查询结果是否全部读取完
        boolean consumed = couponCursor.isConsumed();

        List<Coupon> coupons = new ArrayList<>();
        for (Coupon coupon : couponCursor) {
            coupons.add(coupon);
        }

        try {
            couponCursor.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CouponConvert.INSTANCE.convertList(coupons);
    }

}
