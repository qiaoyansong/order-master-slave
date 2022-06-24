package com.controller;

import com.enums.ErrorEnum;
import com.exception.BizException;
import com.param.OrderInfoQuery;
import com.result.RpcResult;
import com.service.UserOrderInfoService;
import com.utils.RpcResultUtil;
import com.vo.UserOrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 6/23/22 7:41 下午
 * description：
 */
@Slf4j
@RestController
public class UserOrderController {

    @Autowired
    private UserOrderInfoService userOrderInfoService;

    /**
     * 查询订单列表
     *
     * @param userOrderInfoQuery 入参
     * @return PageResponse 出参
     */
    @PostMapping("/queryUserOrderInfoList")
    public RpcResult<UserOrderVO> queryUserOrderInfoList(@RequestBody OrderInfoQuery userOrderInfoQuery) {
        try {
            StopWatch watch = new StopWatch("UserOrderController#queryUserOrderInfoList");
            watch.start();
            UserOrderVO userOrderVOPage = userOrderInfoService.queryUserOrderInfo(userOrderInfoQuery);
            watch.stop();
            log.info("查询用户订单耗时：{}", watch.getTotalTimeMillis());
            return RpcResultUtil.success(userOrderVOPage);
        } catch (BizException e) {
            return RpcResultUtil.fail(null, e.getCode(), e.getMsg());
        } catch (Exception e) {
            log.error("queryOrderInfoList error: {}, {}", e.getMessage(), e);
            return RpcResultUtil.fail(null, ErrorEnum.INNER_ERROR.getCode(), ErrorEnum.INNER_ERROR.getMsg());
        }


    }

}
