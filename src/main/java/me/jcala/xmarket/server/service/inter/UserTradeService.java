package me.jcala.xmarket.server.service.inter;

import me.jcala.xmarket.server.entity.configuration.TradeType;
import me.jcala.xmarket.server.entity.document.Trade;
import org.springframework.http.ResponseEntity;

public interface UserTradeService {
    ResponseEntity<?> createTrade(String userId, Trade trade);
    ResponseEntity<?> getTrades(TradeType type,String userId);
}
