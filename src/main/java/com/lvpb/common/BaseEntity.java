package com.lvpb.common;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class BaseEntity implements Serializable {


    /**
     * 删除标志(0:正常,1:删除)
     */
    @TableField(value = "deleted")
    @TableLogic
    @Schema(description="删除标志(0:正常,1:删除)")
    private Boolean deleted;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @Schema(description="创建时间")
    private Date createTime;

    /**
     * 创建人编号
     */
    @TableField(value = "create_id")
    @Schema(description="创建人编号")
    private Long createId;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    @Schema(description="更新时间")
    private Date updateTime;

    /**
     * 更新人编号
     */
    @TableField(value = "update_id")
    @Schema(description="更新人编号")
    private Long updateId;
}
