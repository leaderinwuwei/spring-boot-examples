package com.neo.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 测试CPU暴涨
 * </p>
 *
 * @author monta
 * @since 2021-04-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("table_name_demo")
public class TableNameDemo implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "column_1", type = IdType.AUTO)
    private Integer column1;

    @TableField("column_2")
    private Integer column2;

    @TableField("column_3")
    private Integer column3;

    @TableField("column_4")
    private Integer column4;

    @TableField("column_5")
    private Integer column5;

    @TableField("column_6")
    private Integer column6;

    @TableField("column_7")
    private Integer column7;

    @TableField("column_8")
    private Integer column8;

    @TableField("column_9")
    private Integer column9;

    @TableField("column_10")
    private Integer column10;

    @TableField("column_11")
    private Integer column11;


}
