package com.example.project.service;

import com.example.project.model.entity.Goods;
import com.example.project.model.entity.User;
import com.example.project.model.enumclass.GoodsStatus;
import com.example.project.model.network.Header;
import com.example.project.model.network.Pagination;
import com.example.project.model.network.request.GoodsApiRequest;
import com.example.project.model.network.response.GoodsApiResponse;
import com.example.project.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GoodsApiLogicService extends BaseService<GoodsApiRequest, GoodsApiResponse, Goods> {

    @Autowired
    private GoodsRepository goodsRepository;

    @Transactional
    @Override
    public Header<GoodsApiResponse> create(Header<GoodsApiRequest> request) {
        GoodsApiRequest goodsApiRequest = request.getData();
        Goods goods = Goods.builder()
                .gdName(goodsApiRequest.getGdName())
                .gdPrice(goodsApiRequest.getGdPrice())
                .gdCount(goodsApiRequest.getGdCount())
                .gdBrand(goodsApiRequest.getGdBrand())
                .gdCategory(goodsApiRequest.getGdCategory())
                .gdSaleprice((goodsApiRequest.getGdPrice().subtract(goodsApiRequest.getGdSalepercent().multiply(BigDecimal.valueOf(0.01)).multiply(goodsApiRequest.getGdPrice()))).setScale(0, RoundingMode.FLOOR))
                .gdSalepercent(goodsApiRequest.getGdSalepercent())
                .gdHit(goodsApiRequest.getGdHit())
                .gdImg(goodsApiRequest.getGdImg())
                .gdOption(goodsApiRequest.getGdOption())
                .gdContent(goodsApiRequest.getGdContent())
                .gdDetailimg(goodsApiRequest.getGdDetailimg())
                .gdStatus(GoodsStatus.SELL)
                .build();
        Goods newGoods = baseRepository.save(goods);
        return Header.OK(response(newGoods));
    }


    public GoodsApiResponse response(Goods goods){
        GoodsApiResponse goodsApiResponse = GoodsApiResponse.builder()
                .gdIdx(goods.getGdIdx())
                .gdName(goods.getGdName())
                .gdPrice(goods.getGdPrice())
                .gdCount(goods.getGdCount())
                .gdBrand(goods.getGdBrand())
                .gdRegdate(goods.getGdRegdate())
                .gdCategory(goods.getGdCategory())
                .gdSaleprice(goods.getGdSaleprice())
                .gdSalepercent(goods.getGdSalepercent())
                .gdHit(goods.getGdHit())
                .gdImg(goods.getGdImg())
                .gdOption(goods.getGdOption())
                .gdContent(goods.getGdContent())
                .gdDetailimg(goods.getGdDetailimg())
                .gdStatus(goods.getGdStatus())
                .build();
        return goodsApiResponse;
    }
    @Transactional
    @Override
    public Header<GoodsApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(goods -> response(goods))
                .map(Header::OK)
                .orElseGet(
                        () -> Header.ERROR("데이터 없음")
                );
    }
    @Transactional
    @Override
    public Header<GoodsApiResponse> update(Header<GoodsApiRequest> request) {
        GoodsApiRequest goodsApiRequest = request.getData();
        Optional<Goods> goods = baseRepository.findById(goodsApiRequest.getGdIdx());
        return goods.map(goodss -> {
                    goodss.setGdName(goodsApiRequest.getGdName());
                    goodss.setGdPrice(goodsApiRequest.getGdPrice());
                    goodss.setGdCount(goodsApiRequest.getGdCount());
                    goodss.setGdBrand(goodsApiRequest.getGdBrand());
                    goodss.setGdCategory(goodsApiRequest.getGdCategory());
                    goodss.setGdSaleprice((goodsApiRequest.getGdPrice().subtract(goodsApiRequest.getGdSalepercent().multiply(BigDecimal.valueOf(0.01)).multiply(goodsApiRequest.getGdPrice()))).setScale(0, RoundingMode.FLOOR));
                    goodss.setGdSalepercent(goodsApiRequest.getGdSalepercent().multiply(BigDecimal.valueOf(0.01)));
                    goodss.setGdImg(goodsApiRequest.getGdImg());
                    goodss.setGdOption(goodsApiRequest.getGdOption());
                    goodss.setGdContent(goodsApiRequest.getGdContent());
                    goodss.setGdDetailimg(goodsApiRequest.getGdDetailimg());
                    goodss.setGdStatus(goodsApiRequest.getGdStatus());
                    return goodss;
                }).map(goodss -> baseRepository.save(goodss))
                .map(goodss -> response(goodss))
                .map(Header::OK)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }
    @Transactional
    @Override
    public Header delete(Long id) {
        Optional<Goods> goods = baseRepository.findById(id);

        return goods.map(goodss -> {
            baseRepository.delete(goodss);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }


    @javax.transaction.Transactional
    public Header<List<GoodsApiResponse>> search(Pageable pageable){
        Page<Goods> goodss = baseRepository.findAll(pageable);
        List<GoodsApiResponse> goodsApiResponseList = goodss.stream()
                .map(goods -> response(goods))
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(goodss.getTotalPages())
                .totalElements(goodss.getTotalElements())
                .currentPage(goodss.getNumber())
                .currentElements(goodss.getNumberOfElements())
                .build();
        return Header.OK(goodsApiResponseList, pagination);
    }
    @Transactional
    public Header<List<GoodsApiResponse>> list() {
        List<Goods> goodss = baseRepository.findAll();
        List<GoodsApiResponse> goodsApiResponseList = goodss.stream()
                .map(goods -> response(goods))
                .collect(Collectors.toList());
        return Header.OK(goodsApiResponseList);
    }
    @Transactional
    public Header<List<GoodsApiResponse>> listBrand(String gdBrand) {
        List<Goods> goodss2 = goodsRepository.findAllByGdBrand(gdBrand);
        List<GoodsApiResponse> goodsApiResponseList = goodss2.stream()
                .map(goods2 -> response(goods2))
                .collect(Collectors.toList());
        return Header.OK(goodsApiResponseList);
    }

    @Transactional
    public Header<List<GoodsApiResponse>> listCategory(String gdCategory) {
        List<Goods> goodss3 = goodsRepository.findAllByGdCategory(gdCategory);
        List<GoodsApiResponse> goodsApiResponseList = goodss3.stream()
                .map(goods3 -> response(goods3))
                .collect(Collectors.toList());
        return Header.OK(goodsApiResponseList);
    }

    public List<GoodsApiResponse> getGoodsList(){
        List<Goods> goodsList = goodsRepository.findAll();
        List<GoodsApiResponse> goodsApiResponseList = goodsList.stream()
                .map(goods -> response(goods))
                .collect(Collectors.toList());
        return goodsApiResponseList;
    }

    //    public Header<GoodsApiResponse> buy(Long id){
//        Optional<Goods> goods = baseRepository.findById(id);
//        return goods.map(goodss -> {
//                    goodss.setGdCount(goods.get().getGdCount() - 1);
//                    goodss.setGdHit(goods.get().getGdHit() + 1 ) ;
//                    if (goods.get().getGdCount() == 0){
//                        goods.get().setGdStatus(GoodsStatus.SOLDOUT);
//                    }
//                    return goodss;
//                }).map(goodss -> baseRepository.save(goodss))
//                .map(goodss -> response(goodss))
//                .map(Header::OK)
//                .orElseGet(() -> Header.ERROR("데이터 없음"));
//    }
    @Transactional
    public void buy(Long id){
        Optional<Goods> goods = baseRepository.findById(id);

        goods.ifPresent(select -> {
            select.setGdCount(goods.get().getGdCount() - 1);
            select.setGdHit(goods.get().getGdHit() + 1 ) ;
            if (goods.get().getGdCount() == 0){
                goods.get().setGdStatus(GoodsStatus.SOLDOUT);
            }
        });
    }
}
