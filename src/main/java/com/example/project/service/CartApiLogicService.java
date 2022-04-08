package com.example.project.service;

import com.example.project.model.DTO.CartDTO;
import com.example.project.model.entity.Cart;
import com.example.project.model.entity.Goods;
import com.example.project.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartApiLogicService  {

    @Autowired
    private CartRepository cartRepository;
    @Transactional
    public Cart create(Long userIdx, CartDTO cartDTO){
        Cart cart = Cart.builder()
                .userIdx(userIdx)
                .goods(Goods.builder()
                        .gdIdx(cartDTO.getGoods().getGdIdx())
                        .build())
                .build();
        Cart newCart = cartRepository.save(cart);
        return newCart;
    }
    @Transactional
    public CartDTO read(Long id){
        Optional<Cart> cartOptional = cartRepository.findById(id);
        Cart cart = cartOptional.get();
        CartDTO cartDTO = CartDTO.builder()
                .cartIdx(cart.getCartIdx())
                .userIdx(cart.getUserIdx())
                .goods(Goods.builder()
                        .gdIdx(cart.getGoods().getGdIdx())
                        .gdName(cart.getGoods().getGdName())
                        .gdBrand(cart.getGoods().getGdBrand())
                        .gdPrice(cart.getGoods().getGdPrice())
                        .gdOption(cart.getGoods().getGdOption())
                        .gdSaleprice(cart.getGoods().getGdSaleprice())
                        .gdSalepercent(cart.getGoods().getGdSalepercent())
                        .gdCount(cart.getGoods().getGdCount())
                        .gdCategory(cart.getGoods().getGdCategory())
                        .gdHit(cart.getGoods().getGdHit())
                        .gdImg(cart.getGoods().getGdImg())
                        .gdContent(cart.getGoods().getGdContent())
                        .gdDetailimg(cart.getGoods().getGdDetailimg())
                        .build())
                .build();
        return cartDTO;
    }
    @Transactional
    public List<CartDTO> getCartList(Long userIdx){
        List<Cart> cartList = cartRepository.findAllByUserIdx(userIdx);
        List<CartDTO> cartDTOList = new ArrayList<>();

        for(Cart cart : cartList){
            CartDTO cartDTO = CartDTO.builder()
                    .cartIdx(cart.getCartIdx())
                    .userIdx(cart.getUserIdx())
                    .goods(Goods.builder()
                            .gdIdx(cart.getGoods().getGdIdx())
                            .gdName(cart.getGoods().getGdName())
                            .gdBrand(cart.getGoods().getGdBrand())
                            .gdPrice(cart.getGoods().getGdPrice())
                            .gdOption(cart.getGoods().getGdOption())
                            .gdSaleprice(cart.getGoods().getGdSaleprice())
                            .gdSalepercent(cart.getGoods().getGdSalepercent())
                            .gdCount(cart.getGoods().getGdCount())
                            .gdCategory(cart.getGoods().getGdCategory())
                            .gdHit(cart.getGoods().getGdHit())
                            .gdImg(cart.getGoods().getGdImg())
                            .gdContent(cart.getGoods().getGdContent())
                            .gdDetailimg(cart.getGoods().getGdDetailimg())
                            .build())
                    .build();
            cartDTOList.add(cartDTO);
        }
        return cartDTOList;
    }
    @Transactional
    public void delete(Long id) {
        cartRepository.deleteById(id);
    }
    @Transactional
    public void deleteAll(Long userIdx){
        cartRepository.deleteAllByUserIdx(userIdx);
    }
}





