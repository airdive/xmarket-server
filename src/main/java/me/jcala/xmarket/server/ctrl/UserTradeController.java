package me.jcala.xmarket.server.ctrl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.jcala.xmarket.server.entity.configuration.TradeType;
import me.jcala.xmarket.server.entity.document.Trade;
import me.jcala.xmarket.server.entity.dto.Result;
import me.jcala.xmarket.server.service.inter.UserTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api("跟用户交易有关的api")
@RestController
@RequestMapping("/api/v1/users/")
public class UserTradeController {

    private UserTradeService userTradeService;

    @Autowired
    public UserTradeController(UserTradeService userTradeService) {
        this.userTradeService = userTradeService;
    }

    @ApiOperation(value = "发布商品",response = Result.class,produces = "application/json;charset=UTF-8")
    @PostMapping(value = "/{userId}/trades/create",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> create(@PathVariable("userId") String userId, Trade trade){
        return userTradeService.createTrade(userId,trade);
    }

    @ApiOperation(value = "获取捐赠商品列表",response = Result.class,produces = "application/json;charset=UTF-8")
    @GetMapping(value = "/{userId}/donate/get",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> donates(@PathVariable("userId") String userId){
        return userTradeService.getTrades(TradeType.DONATE,userId);
    }

    @ApiOperation(value = "获取卖出商品列表",response = Result.class,produces = "application/json;charset=UTF-8")
    @GetMapping(value = "/{userId}/sold/get",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> sold(@PathVariable("userId") String userId){
        return userTradeService.getTrades(TradeType.SOLD,userId);
    }

    @ApiOperation(value = "获取买到商品列表",response = Result.class,produces = "application/json;charset=UTF-8")
    @GetMapping(value = "/{userId}/bought/get",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> bought(@PathVariable("userId") String userId){
        return userTradeService.getTrades(TradeType.BOUGHT,userId);
    }

    @ApiOperation(value = "获取待售商品列表",response = Result.class,produces = "application/json;charset=UTF-8")
    @GetMapping(value = "/{userId}/for_sale/get",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> forSale(@PathVariable("userId") String userId){
        return userTradeService.getTrades(TradeType.SELL,userId);
    }

    @ApiOperation(value = "获取待确认商品列表",response = Result.class,produces = "application/json;charset=UTF-8")
    @GetMapping(value = "/{userId}/toBeConfirmed/get",produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> toBeConfirm(@PathVariable("userId") String userId) {
        return userTradeService.getTrades(TradeType.TOBECONFIRM, userId);
    }
}
