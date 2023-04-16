package com.matrix.gulimall.product.exception;


import com.matrix.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.matrix.common.exception.BizCodeEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * 1错误码定义规则为5为数字
 * 2.前两位表示业务场景，最后三位表示错误码。例如:100001。10:通用 001:系统未知异常
 * 3.维护错误码后需要维护错误描述，将他们定义为枚举形式*错误码列表:
 * 10:通用
 * 001:参数格式校验
 * 11: 商品
 * 12: 订单
 * 13:购物车
 * 14: 物流
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.matrix.gulimall.product.controller")
public class MatrixExceptionAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handlerValidException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        Map map = new HashMap<String, String>();
        log.error("数据校验出现问题{},异常类型{}", e.getMessage(), e.getClass());
        if (result.hasErrors()) {
            result.getFieldErrors().forEach((item) -> {
                String message = item.getDefaultMessage();
                String field = item.getField();
                map.put(field, message);
            });
        }
        return R.error(BizCodeEnum.VAILD_EXCEPTION.getCode(), BizCodeEnum.VAILD_EXCEPTION.getMsg()).put("data", map);
    }

    @ExceptionHandler(value = Throwable.class)
    public R handlerException(Throwable throwable) {
        log.error("错误", throwable);
        return R.error(BizCodeEnum.UNKNOWN_EXCEPTION.getCode(), BizCodeEnum.UNKNOWN_EXCEPTION.getMsg());

    }
}
