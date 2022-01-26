package com.hbicc.cloud.service.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
// import java.time.LocalDateTime;
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("`order`")
public class DefaultOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableField("`collect_client_id`")
    @JsonProperty("collect_client_id")
    private String collectClientId;
    @TableField("`created_at`")
    @JsonProperty("created_at")
    private String createdAt;
    @TableField("`deleted_at`")
    @JsonProperty("deleted_at")
    private String deletedAt;
    @TableField("`fee_money`")
    @JsonProperty("fee_money")
    private String feeMoney;
    @TableField("`fee_type`")
    @JsonProperty("fee_type")
    private String feeType;
    // @TableId(value = "id", type = IdType.ID_WORKER_STR)
    @TableId(value = "id", type = IdType.NONE)
    private String id;
    @TableField("`name`")
    @JsonProperty("name")
    private String name;
    @TableField("`pay_client_id`")
    @JsonProperty("pay_client_id")
    private String payClientId;
    @TableField("`remark`")
    @JsonProperty("remark")
    private String remark;
    @TableField("`updated_at`")
    @JsonProperty("updated_at")
    private String updatedAt;
}
