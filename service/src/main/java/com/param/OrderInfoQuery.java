package com.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author ruyuan
 * @Description
 */
@Data
public class OrderInfoQuery {

   /**
    * 名字
    */
   @NotNull(message = "orderId不能为空")
   private Integer orderId;

}
