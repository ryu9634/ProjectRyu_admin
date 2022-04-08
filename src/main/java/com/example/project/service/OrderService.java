package com.example.project.service;

import com.example.project.model.DTO.NoticeDTO;
import com.example.project.model.DTO.OrderDTO;
import com.example.project.model.entity.Goods;
import com.example.project.model.entity.Notice;
import com.example.project.model.entity.Order;
import com.example.project.model.enumclass.CouponStatus;
import com.example.project.model.enumclass.OrderStatus;
import com.example.project.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    private static AtomicInteger count = new AtomicInteger(0);

    @Transactional
    public Order create(Long userIdx, OrderDTO orderDTO){

        LocalDateTime now = LocalDateTime.now();
        Order order = Order.builder()
                .userIdx(userIdx)
                .orderSeller("본사")
                .orderRegdate(now)
                .orderStatus(OrderStatus.PAY)
                .orderNum(now.format(DateTimeFormatter.ofPattern("yyyyMMdd"))+count.incrementAndGet())
                .goods(Goods.builder()
                        .gdIdx(orderDTO.getGoods().getGdIdx()).build())
                .build();
        Order newOrder = orderRepository.save(order);
        return newOrder;
    }
    @Transactional
    public OrderDTO read(Long id){
        Optional<Order> orderOptional = orderRepository.findById(id);
        Order order = orderOptional.get();
        OrderDTO orderDTO = OrderDTO.builder()
                .orderIdx(order.getOrderIdx())
                .orderNum(order.getOrderNum())
                .orderStatus(order.getOrderStatus())
                .orderSeller(order.getOrderSeller())
                .orderRegdate(order.getOrderRegdate())
                .userIdx(order.getUserIdx())
                .goods(Goods.builder()
                        .gdIdx(order.getGoods().getGdIdx())
                        .gdName(order.getGoods().getGdName())
                        .gdImg(order.getGoods().getGdImg())
                        .build())
                .build();
        return orderDTO;
    }

    @Transactional
    public List<OrderDTO> list(){
        List<Order> orderList = orderRepository.findAll();
        List<OrderDTO> orderDTOList = new ArrayList<>();

        for(Order order : orderList){
            OrderDTO orderDTO = OrderDTO.builder()
                    .orderIdx(order.getOrderIdx())
                    .orderSeller(order.getOrderSeller())
                    .orderNum(order.getOrderNum())
                    .orderStatus(order.getOrderStatus())
                    .orderRegdate(order.getOrderRegdate())
                    .userIdx(order.getUserIdx())
                    .goods(Goods.builder()
                            .gdIdx(order.getGoods().getGdIdx())
                            .gdName(order.getGoods().getGdName())
                            .gdImg(order.getGoods().getGdImg())
                            .build())
                    .build();
            orderDTOList.add(orderDTO);
        }
        return orderDTOList;
    }

    @Transactional
    public List<OrderDTO> getOrderList(Long userIdx){
        List<Order> orderList = orderRepository.findAllByUserIdx(userIdx);
        List<OrderDTO> orderDTOList = new ArrayList<>();

        for(Order order : orderList){
            OrderDTO orderDTO = OrderDTO.builder()
                    .orderIdx(order.getOrderIdx())
                    .orderSeller(order.getOrderSeller())
                    .orderNum(order.getOrderNum())
                    .orderStatus(order.getOrderStatus())
                    .orderRegdate(order.getOrderRegdate())
                    .goods(Goods.builder()
                            .gdIdx(order.getGoods().getGdIdx())
                            .gdName(order.getGoods().getGdName())
                            .gdImg(order.getGoods().getGdImg())
                            .build())
                    .build();
            orderDTOList.add(orderDTO);
        }
        return orderDTOList;
    }

    @Transactional
    public void updateReady(OrderDTO orderDTO){
        Optional<Order> order = orderRepository.findById(orderDTO.getOrderIdx());

        order.ifPresent(select -> {
            select.setOrderStatus(OrderStatus.READY);
        });
    }

    @Transactional
    public void updateSend(OrderDTO orderDTO){
        Optional<Order> order = orderRepository.findById(orderDTO.getOrderIdx());

        order.ifPresent(select -> {
            select.setOrderStatus(OrderStatus.SEND);
        });
    }

    @Transactional
    public void updateFinish(OrderDTO orderDTO){
        Optional<Order> order = orderRepository.findById(orderDTO.getOrderIdx());

        order.ifPresent(select -> {
            select.setOrderStatus(OrderStatus.FINISH);
        });
    }

    @Transactional
    public void updateCancel(OrderDTO orderDTO){
        Optional<Order> order = orderRepository.findById(orderDTO.getOrderIdx());

        order.ifPresent(select -> {
            select.setOrderStatus(OrderStatus.CANCELED);
        });
    }

    @Transactional
    public void updateReturning(OrderDTO orderDTO){
        Optional<Order> order = orderRepository.findById(orderDTO.getOrderIdx());

        order.ifPresent(select -> {
            select.setOrderStatus(OrderStatus.RETURNING);
        });
    }

    @Transactional
    public void updateReturned(OrderDTO orderDTO){
        Optional<Order> order = orderRepository.findById(orderDTO.getOrderIdx());

        order.ifPresent(select -> {
            select.setOrderStatus(OrderStatus.RETURNED);
        });
    }

    @Transactional
    public List<OrderDTO> getCancelList(){
        List<Order> orderList = orderRepository.findAllByOrderStatus(OrderStatus.CANCELED);
        List<OrderDTO> orderDTOList = new ArrayList<>();

        for(Order order : orderList){
            OrderDTO orderDTO = OrderDTO.builder()
                    .orderIdx(order.getOrderIdx())
                    .userIdx(order.getUserIdx())
                    .orderSeller(order.getOrderSeller())
                    .orderNum(order.getOrderNum())
                    .orderStatus(order.getOrderStatus())
                    .orderRegdate(order.getOrderRegdate())
                    .goods(Goods.builder()
                            .gdIdx(order.getGoods().getGdIdx())
                            .gdName(order.getGoods().getGdName())
                            .gdImg(order.getGoods().getGdImg())
                            .build())
                    .build();
            orderDTOList.add(orderDTO);
        }
        return orderDTOList;
    }

    @Transactional
    public List<OrderDTO> getReturnList(){
        List<Order> orderList = orderRepository.findAllByOrderStatus(OrderStatus.RETURNED);
        List<Order> orderList1 = orderRepository.findAllByOrderStatus(OrderStatus.RETURNING);
        List<OrderDTO> orderDTOList = new ArrayList<>();

        for(Order order : orderList){
            OrderDTO orderDTO = OrderDTO.builder()
                    .orderIdx(order.getOrderIdx())
                    .userIdx(order.getUserIdx())
                    .orderSeller(order.getOrderSeller())
                    .orderNum(order.getOrderNum())
                    .orderStatus(order.getOrderStatus())
                    .orderRegdate(order.getOrderRegdate())
                    .goods(Goods.builder()
                            .gdIdx(order.getGoods().getGdIdx())
                            .gdName(order.getGoods().getGdName())
                            .gdImg(order.getGoods().getGdImg())
                            .build())
                    .build();
            orderDTOList.add(orderDTO);
        }

        for(Order order : orderList1){
            OrderDTO orderDTO1 = OrderDTO.builder()
                    .orderIdx(order.getOrderIdx())
                    .userIdx(order.getUserIdx())
                    .orderSeller(order.getOrderSeller())
                    .orderNum(order.getOrderNum())
                    .orderStatus(order.getOrderStatus())
                    .orderRegdate(order.getOrderRegdate())
                    .goods(Goods.builder()
                            .gdIdx(order.getGoods().getGdIdx())
                            .gdName(order.getGoods().getGdName())
                            .gdImg(order.getGoods().getGdImg())
                            .build())
                    .build();
            orderDTOList.add(orderDTO1);
        }
        return orderDTOList;
    }

    @Transactional
    public List<OrderDTO> getExchangeList(){
        List<Order> orderList = orderRepository.findAllByOrderStatus(OrderStatus.EXCHANGING);
        List<Order> orderList1 = orderRepository.findAllByOrderStatus(OrderStatus.EXCHANGED);
        List<OrderDTO> orderDTOList = new ArrayList<>();

        for(Order order : orderList){
            OrderDTO orderDTO = OrderDTO.builder()
                    .orderIdx(order.getOrderIdx())
                    .userIdx(order.getUserIdx())
                    .orderSeller(order.getOrderSeller())
                    .orderNum(order.getOrderNum())
                    .orderStatus(order.getOrderStatus())
                    .orderRegdate(order.getOrderRegdate())
                    .goods(Goods.builder()
                            .gdIdx(order.getGoods().getGdIdx())
                            .gdName(order.getGoods().getGdName())
                            .gdImg(order.getGoods().getGdImg())
                            .build())
                    .build();
            orderDTOList.add(orderDTO);
        }

        for(Order order : orderList1){
            OrderDTO orderDTO1 = OrderDTO.builder()
                    .orderIdx(order.getOrderIdx())
                    .userIdx(order.getUserIdx())
                    .orderSeller(order.getOrderSeller())
                    .orderNum(order.getOrderNum())
                    .orderStatus(order.getOrderStatus())
                    .orderRegdate(order.getOrderRegdate())
                    .goods(Goods.builder()
                            .gdIdx(order.getGoods().getGdIdx())
                            .gdName(order.getGoods().getGdName())
                            .gdImg(order.getGoods().getGdImg())
                            .build())
                    .build();
            orderDTOList.add(orderDTO1);
        }
        return orderDTOList;
    }

    @Transactional
    public List<OrderDTO> List(){
        List<Order> orderList = orderRepository.findAll();
        List<OrderDTO> orderDTOList = new ArrayList<>();

        for(Order order : orderList){
            OrderDTO orderDTO = OrderDTO.builder()
                    .orderIdx(order.getOrderIdx())
                    .userIdx(order.getUserIdx())
                    .orderSeller(order.getOrderSeller())
                    .orderNum(order.getOrderNum())
                    .orderStatus(order.getOrderStatus())
                    .orderRegdate(order.getOrderRegdate())
                    .goods(Goods.builder()
                            .gdIdx(order.getGoods().getGdIdx())
                            .gdName(order.getGoods().getGdName())
                            .gdImg(order.getGoods().getGdImg())
                            .build())
                    .build();
            orderDTOList.add(orderDTO);
        }
        return orderDTOList;
    }

    @Transactional
    public void delete(Long id){
        orderRepository.deleteById(id);
    }


    @Transactional
    public void update(OrderDTO orderDTO){
        Optional<Order> order = orderRepository.findById(orderDTO.getOrderIdx());

        order.ifPresent(select -> select.setOrderStatus(orderDTO.getOrderStatus()));
    }
}
