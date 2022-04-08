package com.example.project.controller.api;

import com.example.project.model.DTO.CouponDTO;
import com.example.project.model.DTO.DpointDTO;
import com.example.project.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coupon")
public class CouponController {

    private final CouponService couponService;

    @PostMapping("/new/{userIdx}")
    public void create(@PathVariable Long userIdx, @RequestBody CouponDTO couponDTO){
        couponService.create(userIdx, couponDTO);
    }

    @GetMapping("/list/{userIdx}")
    public List<CouponDTO> getCouponList(@PathVariable Long userIdx){
        List<CouponDTO> couponDTOList = couponService.getCouponList(userIdx);
        return couponDTOList;
    }


    @PostMapping("/update")
    public void update(@RequestBody CouponDTO couponDTO){
        couponService.update(couponDTO);
    }

    @PostMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        couponService.delete(id);
    }

}
