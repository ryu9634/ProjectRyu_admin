package com.example.project.service;

import com.example.project.model.entity.DpointGoods;
import com.example.project.model.network.Header;
import com.example.project.model.network.Pagination;
import com.example.project.model.network.request.DpointGoodsApiRequest;
import com.example.project.model.network.response.DpointGoodsApiResponse;
import com.example.project.repository.DpointGoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DpointGoodsService extends BaseService<DpointGoodsApiRequest, DpointGoodsApiResponse, DpointGoods> {

    @Autowired
    private DpointGoodsRepository dpointGoodsRepository;

    @Transactional
    @Override
    public Header<DpointGoodsApiResponse> create(Header<DpointGoodsApiRequest> request) {
        DpointGoodsApiRequest dpointGoodsRequest = request.getData();
        DpointGoods dpointGoods = DpointGoods.builder()
                .dpgName(dpointGoodsRequest.getDpgName())
                .dpgPrice(dpointGoodsRequest.getDpgPrice())
                .dpgCount(dpointGoodsRequest.getDpgCount())
                .dpgEnddate(LocalDateTime.now().plusYears(1))
                .dpgCategory(dpointGoodsRequest.getDpgCategory())
                .dpgHit(0)
                .dpgImg(dpointGoodsRequest.getDpgImg())
                .build();
        DpointGoods newDpointGoods = baseRepository.save(dpointGoods);
        return Header.OK(response(newDpointGoods));
    }

    @Override
    public Header<DpointGoodsApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(dpointGoods -> response(dpointGoods))
                .map(Header::OK)
                .orElseGet(
                        () -> Header.ERROR("데이터 없음")
                );
    }

    @Override
    public Header<DpointGoodsApiResponse> update(Header<DpointGoodsApiRequest> request) {
        return null;
    }

    @Override
    public Header<DpointGoodsApiResponse> delete(Long id) {
        return null;
    }

    public DpointGoodsApiResponse response(DpointGoods dpointGoods){
        DpointGoodsApiResponse dpointGoodsResponse = DpointGoodsApiResponse.builder()
                .dpgIdx(dpointGoods.getDpgIdx())
                .dpgName(dpointGoods.getDpgName())
                .dpgPrice(dpointGoods.getDpgPrice())
                .dpgCount(dpointGoods.getDpgCount())
                .dpgEnddate(dpointGoods.getDpgEnddate())
                .dpgCategory(dpointGoods.getDpgCategory())
                .dpgHit(dpointGoods.getDpgHit())
                .dpgImg(dpointGoods.getDpgImg())
                .build();
        return dpointGoodsResponse;
    }
    @Transactional
    public Header<List<DpointGoodsApiResponse>> search(Pageable pageable){
        Page<DpointGoods> dpointGoodsPage = baseRepository.findAll(pageable);
        List<DpointGoodsApiResponse> dpointGoodsResponseList = dpointGoodsPage.stream()
                .map(dpointGoods -> response(dpointGoods))
                .collect(Collectors.toList());

        Pagination pagination = Pagination.builder()
                .totalPages(dpointGoodsPage.getTotalPages())
                .totalElements(dpointGoodsPage.getTotalElements())
                .currentPage(dpointGoodsPage.getNumber())
                .currentElements(dpointGoodsPage.getNumberOfElements())
                .build();
        return Header.OK(dpointGoodsResponseList, pagination);
    }
    @Transactional
    public Header<List<DpointGoodsApiResponse>> list() {
        List<DpointGoods> dpointGoodsList = baseRepository.findAll();
        List<DpointGoodsApiResponse> dpointGoodsResponseList = dpointGoodsList.stream()
                .map(dpointGoods -> response(dpointGoods))
                .collect(Collectors.toList());
        return Header.OK(dpointGoodsResponseList);
    }
    @Transactional
    public Header<List<DpointGoodsApiResponse>> listCategory(String dpgCategory) {
        List<DpointGoods> dpointGoodsList = dpointGoodsRepository.findAllByDpgCategory(dpgCategory);
        List<DpointGoodsApiResponse> dpointGoodsResponseList = dpointGoodsList.stream()
                .map(dpointGoods -> response(dpointGoods))
                .collect(Collectors.toList());
        return Header.OK(dpointGoodsResponseList);
    }


}
