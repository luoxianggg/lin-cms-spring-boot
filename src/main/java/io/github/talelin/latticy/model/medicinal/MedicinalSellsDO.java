package io.github.talelin.latticy.model.medicinal;

import com.baomidou.mybatisplus.annotation.TableName;
import io.github.talelin.latticy.model.BaseModelNoDelete;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @author generator@TaleLin
 * @since 2020-10-18
 */
@Data
public class MedicinalSellsDO extends BaseModelNoDelete {


    /**
     * 药品id
     */
    private BigDecimal funMediId;
    /**
     * 药品名
     */
    private String funMediName;
    /**
     * 问诊单id
     */
    private BigDecimal funPrescribeId;

    /**
     * 顾客id
     */
    private BigDecimal customerId;

    /**
     * 销售量
     */
    private BigDecimal sellNum;
    /**
            * 销售单价
     */
    private BigDecimal sellPrice;
    /**
            * 销售日期
     */
    private String sellDate;
    /**
            * 描述
     */
    private String description;
    private BigDecimal medicinalStoreId;

}
