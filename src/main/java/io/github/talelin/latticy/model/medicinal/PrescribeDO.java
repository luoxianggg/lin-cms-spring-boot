package io.github.talelin.latticy.model.medicinal;

import com.baomidou.mybatisplus.annotation.TableName;
import io.github.talelin.latticy.model.BaseModelNoDelete;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("fun_prescribe")
public class PrescribeDO extends BaseModelNoDelete {
    private String prescribeNumber;
    private BigDecimal customerId;
    private String description;
    private BigDecimal medicinalStoreId;
}
