package com.example.project.controller.api;

import com.example.project.controller.CrudController;
import com.example.project.model.DTO.NoticeDTO;
import com.example.project.model.entity.Goods;
import com.example.project.model.network.Header;
import com.example.project.model.network.request.GoodsApiRequest;
import com.example.project.model.network.response.GoodsApiResponse;
import com.example.project.service.GoodsApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController //json으로 주고 받는 컨트롤러
@RequestMapping("/goods")
@RequiredArgsConstructor
public class GoodsController extends CrudController<GoodsApiRequest, GoodsApiResponse, Goods> {
    /*
    {
"transaction_time":"2022-03-04",
            "resultCode":"ok",
            "description":"ok",
            "data":{
                "gdName":"안좋은의자",
                "gdPrice":1,
                "gdCount":1,
                "gdBrand":"duoback",
                "gdSaleprice":1,
                "gdCategory":"의자",
                "gdSalepercent":1,
                "gdHit":1,
                "gdImg":"z",
                "gdOption":"파란색",
                "gdContent":"1",
                "gdDetailimg":"zz"
    }
    }

     */

    private final GoodsApiLogicService goodsApiLogicService;

    @Override
    @PostMapping("/new")
    public Header<GoodsApiResponse> create(@RequestBody Header<GoodsApiRequest> request){
        return goodsApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<GoodsApiResponse> read(@PathVariable(name="id") Long id) {
        return goodsApiLogicService.read(id);
    }

    @Override
    @PutMapping("/update")
    public Header<GoodsApiResponse> update(@RequestBody Header<GoodsApiRequest> request) {
        return goodsApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}")
    public Header<GoodsApiResponse> delete(@PathVariable(name="id") Long id) {
        return goodsApiLogicService.delete(id);
    }

    @GetMapping("") // http://localhost:8080/goods
    public Header<List<GoodsApiResponse>> search(@PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable){
        return goodsApiLogicService.search(pageable);
    }

    @GetMapping("/list") // http://localhost:8080/goods/list
    public Header<List<GoodsApiResponse>> list(){
        return goodsApiLogicService.list();
    }

//   @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
//   public void goodslist(HttpServletRequest request, HttpServletResponse response) {
//
//   }

    @GetMapping("/list/category") // http://localhost:8080/goods/list/category?gdCategory=*
    public Header<List<GoodsApiResponse>> listCategory(String gdCategory){
        return goodsApiLogicService.listCategory(gdCategory);
    }

    @GetMapping("/list/brand") // http://localhost:8080/goods/list/brand?gdBrand=*
    public Header<List<GoodsApiResponse>> listBrand(String gdBrand){
        return goodsApiLogicService.listBrand(gdBrand);
    }



    @PostMapping("/update2")
    public Header<GoodsApiResponse> update2(@RequestBody Header<GoodsApiRequest> request) {
        return goodsApiLogicService.update(request);
    }

    @PostMapping("/delete2/{id}")
    public Header<GoodsApiResponse> delete2(@PathVariable(name="id") Long id) {
        return goodsApiLogicService.delete(id);
    }
}
