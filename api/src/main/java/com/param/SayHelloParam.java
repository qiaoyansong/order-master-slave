package com.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2022/1/25 8:05 下午
 * description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SayHelloParam implements Serializable {

    private static final long serialVersionUID = -7963604075112264875L;

    /**
     * 名字
     */
    @NotNull(message = "用户不能为空")
    private Integer uid;

}
