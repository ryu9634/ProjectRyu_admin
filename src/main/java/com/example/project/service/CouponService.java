package com.example.project.service;

import com.example.project.model.DTO.CouponDTO;
import com.example.project.model.DTO.DpointDTO;
import com.example.project.model.entity.Coupon;
import com.example.project.model.entity.Dpoint;
import com.example.project.model.enumclass.CouponStatus;
import com.example.project.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;
    @Transactional
    public Coupon create(Long userIdx, CouponDTO couponDTO){
        Coupon coupon = Coupon.builder()
                .userIdx(userIdx)
                .cpDiscount(couponDTO.getCpDiscount().setScale(0, RoundingMode.FLOOR))
                .cpName(couponDTO.getCpName())
                .cpRegdate(LocalDateTime.now())
                .cpStartdate(LocalDateTime.now())
                .cpEnddate(LocalDateTime.now().plusMonths(1))
                .cpLimitprice(couponDTO.getCpLimitprice())
                .cpStatus(CouponStatus.REGISTERED)
                .build();
        Coupon newCoupon = couponRepository.save(coupon);
        return newCoupon;
    }

    @Transactional
    public List<CouponDTO> getCouponList(Long userIDx){
        List<Coupon> couponList = couponRepository.findAllByUserIdx(userIDx);
        List<CouponDTO> couponDTOList = new ArrayList<>();

        for(Coupon coupon : couponList){
            CouponDTO couponDTO = CouponDTO.builder()
                    .cpIdx(coupon.getCpIdx())
                    .cpDiscount(coupon.getCpDiscount())
                    .cpName(coupon.getCpName())
                    .cpRegdate(coupon.getCpRegdate())
                    .cpStartdate(coupon.getCpStartdate())
                    .cpEnddate(coupon.getCpEnddate())
                    .cpLimitprice(coupon.getCpLimitprice())
                    .cpStatus(coupon.getCpStatus())
                    .build();
            couponDTOList.add(couponDTO);

        }
        return couponDTOList;
    }

    @Transactional
    public void update(CouponDTO couponDTO){
        Optional<Coupon> coupon = couponRepository.findById(couponDTO.getCpIdx());

        coupon.ifPresent(select -> {
            select.setCpStatus(couponDTO.getCpStatus());
            select.setCpDiscount(couponDTO.getCpDiscount().setScale(0, RoundingMode.FLOOR));
            select.setCpName(couponDTO.getCpName());
            select.setCpRegdate(couponDTO.getCpRegdate());
            select.setCpEnddate(couponDTO.getCpEnddate());
            select.setCpStartdate(couponDTO.getCpStartdate());
            select.setCpLimitprice(couponDTO.getCpLimitprice());
            select.setUserIdx(couponDTO.getUserIdx());
        });
    }

    @Transactional
    public void delete(Long id){
        couponRepository.deleteById(id);
    }


    @Transactional
    public List<CouponDTO> list(){
        List<Coupon> couponList = couponRepository.findAll();
        List<CouponDTO> couponDTOList = new ArrayList<>();

        for(Coupon coupon : couponList){
            CouponDTO couponDTO = CouponDTO.builder()
                    .cpIdx(coupon.getCpIdx())
                    .cpDiscount(coupon.getCpDiscount())
                    .cpName(coupon.getCpName())
                    .cpRegdate(coupon.getCpRegdate())
                    .cpStartdate(coupon.getCpStartdate())
                    .cpEnddate(coupon.getCpEnddate())
                    .cpLimitprice(coupon.getCpLimitprice())
                    .cpStatus(coupon.getCpStatus())
                    .userIdx(coupon.getUserIdx())
                    .build();
            couponDTOList.add(couponDTO);

        }
        return couponDTOList;
    }

    @Transactional
    public CouponDTO read(Long id){
        Optional<Coupon> couponOptional = couponRepository.findById(id);
        Coupon coupon = couponOptional.get();
        CouponDTO couponDTO = CouponDTO.builder()
                .cpIdx(coupon.getCpIdx())
                .cpDiscount(coupon.getCpDiscount())
                .cpName(coupon.getCpName())
                .cpRegdate(coupon.getCpRegdate())
                .cpStartdate(coupon.getCpStartdate())
                .cpEnddate(coupon.getCpEnddate())
                .cpLimitprice(coupon.getCpLimitprice())
                .cpStatus(coupon.getCpStatus())
                .userIdx(coupon.getUserIdx())
                .build();
        return couponDTO;
    }
}
