package com.corewell.study.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: wangzhen
 * @Date: 2022/12/09/11:58
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("modbus协议读写指令")
public class Modbus {

    /**
     * 从站地址 取值范围 1-255 必选参数
     */
    @ApiModelProperty(value = "从站地址 取值范围 1-255 必选参数", required = true, example = "1")
    private Long address;

    /**
     * 偏置 取值范围 1-65535
     */
    @ApiModelProperty(value = "偏置 取值范围 1-65535", required = true, example = "10")
    private Long bias;

    /**
     * 采集周期 单位秒
     */
    @ApiModelProperty(value = "采集周期 单位秒", required = true, example = "10")
    private Long cycle;

    /**
     * 数据位 数据类型为按位读写时必选，其他情况可选
     */
    @ApiModelProperty(value = "数据位 数据类型为按位读写时必选，其他情况可选", required = false, example = "")
    private Long data;

    /**
     * 数据类型 取值范围 1（16位有符号数） 2（16位无符号数）3 （32位有符号数） 4（32位无符号数）5（32位浮点型）6 （16位按位读写）7（64位浮点型） bit(01 02 只读）
     */
    @ApiModelProperty(value = "数据类型 取值范围 1（16位有符号数） 2（16位无符号数）3 （32位有符号数） 4（32位无符号数）5（32位浮点型）6 （16位按位读写）7（64位浮点型） bit(01 02 只读） ", required = true, example = "1")
    private String datatype;

    /**
     * 功能码 取值范围 1(01读写) 2（02只读） 3（03读写）4（04只读）
     */
    @ApiModelProperty(value = "功能码 取值范围 1(01读写) 2（02只读） 3（03读写）4（04只读）", required = true, example = "1")
    private Long funcode;

    /**
     * id
     */
    @ApiModelProperty(value = "id", required =false, example = "")
    private Long id;
    /**
     * 协议类型
     */
    @ApiModelProperty(value = "协议类型", required = false, example = "")
    private String linktype;

    /**
     * 字节顺序 数据类型为32位或64位时必选，其他情况可选,取值范围 32位 ABCD/CDAB/BADC/DCBA 64位 ABCDEFGH/GHEFCDAB/BADCFEGH/HGFEDCBA
     */
    @ApiModelProperty(value = "字节顺序 数据类型为32位或64位时必选，其他情况可选,取值范围 32位 ABCD/CDAB/BADC/DCBA 64位 ABCDEFGH/GHEFCDAB/BADCFEGH/HGFEDCBA", required = false, example = "")
    private String orderStr;

    /**
     * 传感器Id
     */
    @ApiModelProperty(value = "传感器Id", required = true, example = "")
    private Long sensorId;

    /**
     * 写入值
     */
    @ApiModelProperty(value = "写入值", required = false, example = "")
    private String symbol;

}
