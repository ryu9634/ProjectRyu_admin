package com.example.project.controller;

import com.example.project.model.DTO.CouponDTO;
import com.example.project.model.DTO.DpointDTO;
import com.example.project.model.DTO.QnaDTO;
import com.example.project.model.entity.User;
import com.example.project.model.network.Header;
import com.example.project.model.network.request.GoodsApiRequest;
import com.example.project.model.network.response.GoodsApiResponse;
import com.example.project.repository.UserRepository;
import com.example.project.service.CouponService;
import com.example.project.service.DpointService;
import com.example.project.service.GoodsApiLogicService;
import com.example.project.service.NoticeService;
import com.example.project.service.OrderService;
import com.example.project.service.QnaService;
import com.example.project.service.ReviewService;
import com.example.project.service.UserApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping({"/admin"})
public class PageController {
    private final GoodsApiLogicService goodsApiLogicService;
    private final UserApiLogicService userApiLogicService;
    private final CouponService couponService;
    private final DpointService dpointService;
    private final OrderService orderService;
    private final ReviewService reviewService;
    private final QnaService qnaService;
    private final NoticeService noticeService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HttpSession session;

    // 사용안함
    @GetMapping({"/dashboard"})
    public String dashboard() {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        return "adminpage/dashboard";
    }

    @GetMapping({"/analytics"})
    public String analytics() {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        return "adminpage/analytics";
    }

    @GetMapping({"/memberlist"})
    public String memberlist(Model model) {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        model.addAttribute("UserList", this.userApiLogicService.getUserList());
        return "adminpage/memberList";
    }

    @GetMapping({"/memberview/{userIdx}"})
    public String memberView(Model model, @PathVariable(name = "userIdx") Long userIdx) {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        model.addAttribute("view", this.userApiLogicService.read(userIdx));
        return "adminpage/memberView";
    }

    @GetMapping({"/memberjoin"})
    public String memberjoin() {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        return "adminpage/memberjoin";
    }

    @DeleteMapping({"/memberdelete/{userIdx}"})
    public String memberDelete(@PathVariable(name = "userIdx") Long userIdx) {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        this.userApiLogicService.delete(userIdx);
        return "redirect:/admin/memberlist";
    }

    @PostMapping({"/join.do"})
    public String create(User user) {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        this.userRepository.save(user);
        return "redirect:/admin/memberlist";
    }

    @GetMapping({"/membermail"})
    public String membermail() {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        return "adminpage/membermail";
    }
//800 510
    @GetMapping({"/answer"})
    public String shoppinganswer(Model model) {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        model.addAttribute("answerList", this.qnaService.list());
        return "adminpage/shoppinganswers";
    }

    @GetMapping({"/answerview/{qIdx}"})
    public String answerView(Model model, @PathVariable(name = "qIdx") Long qIdx) {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        model.addAttribute("view", this.qnaService.read(qIdx));
        return "adminpage/answerView";
    }

    @DeleteMapping({"/answerdelete/{qIdx}"})
    public String answerDelete(@PathVariable(name = "qIdx") Long qIdx) {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        this.qnaService.delete(qIdx);
        return "redirect:/admin/answer";
    }

    @GetMapping({"/cancel"})
    public String shoppingcancel(Model model) {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        model.addAttribute("cancelList", this.orderService.getCancelList());
        return "adminpage/shoppingcancel";
    }

    @GetMapping({"/cancelview/{orderIdx}"})
    public String cancelView(Model model, @PathVariable(name = "orderIdx") Long orderIdx) {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        model.addAttribute("view", this.orderService.read(orderIdx));
        return "adminpage/cancelView";
    }

    @GetMapping({"/coupon"})
    public String shoppingcoupon(Model model) {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        model.addAttribute("couponList", this.couponService.list());
        return "adminpage/shoppingcoupon";
    }

    @RequestMapping(
            value = {"/couponview/{cpIdx}"},
            method = {RequestMethod.GET, RequestMethod.PUT}
    )
    public String couponView(@PathVariable Long cpIdx, Model model) {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        model.addAttribute("view", this.couponService.read(cpIdx));
        return "adminpage/couponView";
    }

    @RequestMapping(
            value = {"/couponfix"},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    public String shoppingcouponfix() {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        return "adminpage/shoppingcouponfix";
    }

    @RequestMapping(
            value = {"/coupon/update/{userIdx}"},
            method = {RequestMethod.POST}
    )
    public String shoppingcouponfix(@PathVariable Long userIdx, @RequestBody CouponDTO couponDTO) {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        this.couponService.create(userIdx, couponDTO);
        return "redirect:/admin/coupon";
    }

    @GetMapping({"/dpoint"})
    public String shoppingdpoint(Model model) {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        model.addAttribute("dpointList", this.dpointService.list());
        return "adminpage/shoppingdpoint";
    }

    @PostMapping({"/dpoint"})
    public String shoppingdpoint2(Model model) {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        model.addAttribute("dpointList", this.dpointService.list());
        return "redirect:/admin/dpoint";
    }

    @PostMapping({"/dpoint.do/{userIdx}"})
    @RequestMapping(
            value = {"/dpoint.do/{userIdx}"},
            method = {RequestMethod.GET, RequestMethod.POST}
    )
    public String dpointDo(@PathVariable Long userIdx, @RequestBody DpointDTO dpointDTO) {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        this.dpointService.create(userIdx, dpointDTO);
        return "adminpage/shoppingdpoint";
    }

    @GetMapping("/dpointview/{dpIdx}")
    public String dpointView(Model model, @PathVariable Long dpIdx) {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        model.addAttribute("view", this.dpointService.read(dpIdx));
        return "adminpage/dpointView";
    }

    @DeleteMapping({"/dpointdelete/{dpIdx}"})
    public String dpointDelete(@PathVariable(name = "dpIdx") Long dpIdx) {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        this.dpointService.delete(dpIdx);
        return "redirect:/admin/dpoint";
    }

    @RequestMapping(
            value = {"/dpoint/update/{dpIdx}"},
            method = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.POST}
    )
    public String dpointUpdate(@PathVariable Long dpIdx, @RequestBody DpointDTO dpointDTO) throws Exception {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        this.dpointService.update(dpIdx, dpointDTO);
        return "redirect:/admin/dpoint";
    }

    @GetMapping({"/exchange"})
    public String shoppingexchange(Model model) {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        model.addAttribute("exchangeList", this.orderService.getExchangeList());
        return "adminpage/shoppingexchange";
    }

    @GetMapping({"/exchangeview/{orderIdx}"})
    public String exchangeView(Model model, @PathVariable(name = "orderIdx") Long orderIdx) {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        model.addAttribute("view", this.orderService.read(orderIdx));
        return "adminpage/exchangeView";
    }

    @GetMapping({"/management"})
    public String shoppingmanagement(Model model) {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        model.addAttribute("goodsList", this.goodsApiLogicService.getGoodsList());
        return "adminpage/shoppingmanagement";
    }

    @GetMapping({"/management_add"})
    public String shoppingmanagement_add() {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        return "adminpage/shoppingManagement_add";
    }

    @PostMapping({"/management_add"})
    public Header<GoodsApiResponse> create(@RequestBody Header<GoodsApiRequest> request) {

        return this.goodsApiLogicService.create(request);
    }

    @GetMapping({"/management_hide"})
    public String shoppingmanagement_hide() {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        return "adminpage/shoppingManagement_hide";
    }

    @GetMapping({"/management_sale"})
    public String shoppingmanagement_sale() {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        return "adminpage/shoppingManagement_sale";
    }

    @GetMapping({"/management_soldout"})
    public String shoppingmanagement_soldout() {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        return "adminpage/shoppingManagement_soldout";
    }

    @GetMapping({"/goodsview/{gdIdx}"})
    public String goodsView(Model model, @PathVariable(name = "gdIdx") Long gdIdx) {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        model.addAttribute("view", this.goodsApiLogicService.read(gdIdx));
        return "adminpage/goodsView";
    }

    @DeleteMapping({"/goodsdelete/{gdIdx}"})
    public String goodsDelete(@PathVariable(name = "gdIdx") Long gdIdx) {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        this.goodsApiLogicService.delete(gdIdx);
        return "redirect:/admin/management";
    }

    @GetMapping({"/order"})
    public String shoppingorder(Model model) {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        model.addAttribute("orderList", this.orderService.list());
        return "adminpage/shoppingOrder";
    }

    @GetMapping({"/return"})
    public String shoppingreturn(Model model) {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        model.addAttribute("returnList", this.orderService.getReturnList());
        return "adminpage/shoppingReturn";
    }

    @GetMapping({"/returnview/{orderIdx}"})
    public String returnView(Model model, @PathVariable(name = "orderIdx") Long orderIdx) {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        model.addAttribute("view", this.orderService.read(orderIdx));
        return "adminpage/returnView";
    }

    @GetMapping({"/review"})
    public String shoppingreview(Model model) {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        model.addAttribute("reviewList", this.reviewService.list());
        return "adminpage/shoppingReview";
    }

    @GetMapping({"/reviewview/{rvIdx}"})
    public String reviewView(Model model, @PathVariable(name = "rvIdx") Long rvIdx) {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        model.addAttribute("view", this.reviewService.read(rvIdx));
        return "adminpage/reviewView";
    }

    @DeleteMapping({"/reviewdelete/{rvIdx}"})
    public String reviewDelete(@PathVariable(name = "rvIdx") Long rvIdx) {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        this.reviewService.delete(rvIdx);
        return "redirect:/admin/review";
    }

    @GetMapping({"/visitor"})
    public String visitor() {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        return "adminpage/visitor";
    }

    @GetMapping({"/orderview/{orderIdx}"})
    public String orderView(Model model, @PathVariable(name = "orderIdx") Long orderIdx) {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        model.addAttribute("view", this.orderService.read(orderIdx));
        return "adminpage/orderView";
    }

    @DeleteMapping({"/orderdelete/{orderIdx}"})
    public String orderDelete(@PathVariable(name = "orderIdx") Long orderIdx) {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        this.orderService.delete(orderIdx);
        return "redirect:/admin/order";
    }

    @PostMapping({"/qna/new/{userIdx}"})
    public void qnaCreate(@PathVariable Long userIdx, @RequestBody QnaDTO qnaDTO) {

        this.qnaService.create(userIdx, qnaDTO);
    }

    @PutMapping({"/qna/update/{qIdx}"})
    public String update(@PathVariable Long qIdx, @RequestBody QnaDTO qnaDTO) {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        this.qnaService.update(qIdx, qnaDTO);
        return "redirect:/admin/answer";
    }

    @GetMapping({"/notice"})
    public String notice(Model model) {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        model.addAttribute("noticeList", this.noticeService.getBoardList());
        return "adminpage/noticeList";
    }

    @GetMapping({"/noticeview/{ntIdx}"})
    public String noticeView(Model model, @PathVariable(name = "ntIdx") Long ntIdx) {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        model.addAttribute("view", this.noticeService.read(ntIdx));
        return "adminpage/noticeView";
    }

    @DeleteMapping({"/noticedelete/{ntIdx}"})
    public String noticeDelete(@PathVariable(name = "ntIdx") Long ntIdx) {
        if(session.getAttribute("adminId")==null){
            return "index";
        }
        this.noticeService.delete(ntIdx);
        return "redirect:/admin/notice";
    }

    public PageController(final GoodsApiLogicService goodsApiLogicService, final UserApiLogicService userApiLogicService, final CouponService couponService, final DpointService dpointService, final OrderService orderService, final ReviewService reviewService, final QnaService qnaService, final NoticeService noticeService, final UserRepository userRepository) {
        this.goodsApiLogicService = goodsApiLogicService;
        this.userApiLogicService = userApiLogicService;
        this.couponService = couponService;
        this.dpointService = dpointService;
        this.orderService = orderService;
        this.reviewService = reviewService;
        this.qnaService = qnaService;
        this.noticeService = noticeService;
        this.userRepository = userRepository;
    }

    @GetMapping("/addnotice")
    public String addNotice(){
        return "/adminpage/addNotice";
    }
}
