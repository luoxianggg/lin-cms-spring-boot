package io.github.talelin.latticy.model.medicinal;

import io.github.talelin.latticy.model.BaseModelNoDelete;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MedicinalStockListDo extends BaseModelNoDelete {
    private String medicinalName;
    private String pinyinma;
    private BigDecimal stockNum;
}
