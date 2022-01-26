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
@TableName("`pay_rule`")
public class DefaultPayRule implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableField("`pallet_client_id`")
    @JsonProperty("pallet_client_id")
    private String palletClientId;
    @TableField("`created_at`")
    @JsonProperty("created_at")
    private String createdAt;
    @TableField("`deleted_at`")
    @JsonProperty("deleted_at")
    private String deletedAt;
    @TableField("`fee_type`")
    @JsonProperty("fee_type")
    private String feeType;
    @TableId(value = "id", type = IdType.AUTO)
    private String id;
    @TableField("`pay_client_id`")
    @JsonProperty("pay_client_id")
    private String payClientId;
    @TableField("`updated_at`")
    @JsonProperty("updated_at")
    private String updatedAt;
    @TableField("`pay_money`")
    @JsonProperty("pay_money")
    private String payMoney;
    @TableField("`from_client_id`")
    @JsonProperty("from_client_id")
    private String fromClientId;
    @TableField("`to_client_id`")
    @JsonProperty("to_client_id")
    private String toClientId;
}
