package me.jcala.xmarket.server.admin.service.inter;

import me.jcala.xmarket.server.admin.entity.TradeTag;
import org.springframework.http.ResponseEntity;

public interface TradeService {
    ResponseEntity<?> addTradeTag(TradeTag tag);
}
