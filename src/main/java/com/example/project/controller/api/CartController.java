package com.example.project.controller.api;

import com.example.project.model.DTO.CartDTO;
import com.example.project.service.CartApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartApiLogicService cartApiLogicService;

    @PostMapping("/new/{userIdx}")
    public void create(@PathVariable Long userIdx, @RequestBody CartDTO cartDTO){
        cartApiLogicService.create(userIdx, cartDTO);
    }

    @GetMapping("{id}")
    public CartDTO read(@PathVariable Long id){
        return cartApiLogicService.read(id);
    }

    @GetMapping("/list/{userIdx}")
    public List<CartDTO> list(@PathVariable Long userIdx){
        List<CartDTO> cartDTOList = cartApiLogicService.getCartList(userIdx);
        return cartDTOList;
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        cartApiLogicService.delete(id);
    }

    @DeleteMapping("/deleteAll/{useridx}")
    public void deleteAll(@PathVariable Long userIdx){
        cartApiLogicService.deleteAll(userIdx);
    }
}
