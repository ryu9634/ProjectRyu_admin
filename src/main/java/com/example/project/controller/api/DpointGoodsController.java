package com.example.project.controller.api;

import com.example.project.controller.CrudController;
import com.example.project.model.DTO.NoticeDTO;
import com.example.project.model.entity.DpointGoods;
import com.example.project.model.network.Header;
import com.example.project.model.network.request.DpointGoodsApiRequest;
import com.example.project.model.network.response.DpointGoodsApiResponse;
import com.example.project.service.DpointGoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dpointGoods")
public class DpointGoodsController extends CrudController<DpointGoodsApiRequest, DpointGoodsApiResponse, DpointGoods> {

    private final DpointGoodsService dpointGoodsService;

    @Override
    @PostMapping("/new")
    public Header<DpointGoodsApiResponse> create(@RequestBody Header<DpointGoodsApiRequest> request) {
        return dpointGoodsService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<DpointGoodsApiResponse> read(@PathVariable(name="id") Long id) {
        return dpointGoodsService.read(id);
    }

    @Override
    public Header<DpointGoodsApiResponse> update(Header<DpointGoodsApiRequest> request) {
        return null;
    }

    @Override
    public Header<DpointGoodsApiResponse> delete(Long id) {
        return null;
    }

    @GetMapping("") // http://localhost:8080/goods
    public Header<List<DpointGoodsApiResponse>> search(@PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable){
        return dpointGoodsService.search(pageable);
    }

    @GetMapping("/list") // http://localhost:8080/goods/list
    public Header<List<DpointGoodsApiResponse>> list(){
        return dpointGoodsService.list();
    }

    @GetMapping("/list/category") // http://localhost:8080/goods/list/category?gdCategory=*
    public Header<List<DpointGoodsApiResponse>> listCategory(String dpgCategory){
        return dpointGoodsService.listCategory(dpgCategory);
    }


}
