package com.duiya.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

/**
 * 这是管理端的对订单的操作
 * 处理订单：收到订单通知，开始处理
 * 配送订单：开始配送，发短信通知客户
 * 订单送达：这里写一个定时器，15天后将订单状态改为已完成
 * @author duiya
 *
 */
@Controller
public class MOrderListController {
	private Logger logger = LoggerFactory.getLogger(MOrderListController.class);
}
