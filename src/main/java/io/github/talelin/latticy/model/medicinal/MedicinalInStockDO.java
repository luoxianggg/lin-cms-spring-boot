package io.github.talelin.latticy.model.medicinal;

import java.math.BigDecimal;
import io.github.talelin.latticy.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;

import io.github.talelin.latticy.model.BaseModelNoDelete;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author generator@JesonLuo
 * @since 2020-10-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("fun_medicinal_instock")
public class MedicinalInStockDO extends BaseModelNoDelete {


    /**
     * 药品id
     */
    private Integer funMediId;

    /**
     * 药品批号
     */
    private String batchId;

    /**
     * 批准文号
     */
    private String approveBatchId;

    /**
     * 生产厂家
     */
    private String factory;

    /**
     * 入库数量
     */
    private Integer amount;

    /**
     * 进货单价
     */
    private BigDecimal price;

    /**
     * 药品生产日期
     */
    private LocalDateTime produceDate;

    /**
     * 药品过期日期
     */
    private LocalDateTime invalidDate;

    /**
     * 备注
     */
    private String description;

    /*
    * 进货来源
    * */
   private String sourceFrom;

   /*
   * 药店id
   * */
  private Integer medicinalStoreId;

  /*
  * 入库编号
  * */
  private String instockNumber;

    /*
    * 入库编号流水
    * */
    private Long flowNumber;
}
