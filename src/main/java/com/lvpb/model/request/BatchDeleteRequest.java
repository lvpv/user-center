package com.lvpb.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;


@Data
public class BatchDeleteRequest {

    @Schema(description = "批量删除编号集合")
    @NotEmpty(message = "批量删除编号不能为空")
    private List<Long> ids;
}
