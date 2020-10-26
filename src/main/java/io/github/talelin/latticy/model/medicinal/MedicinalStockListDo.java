package io.github.talelin.latticy.model.medicinal;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MedicinalStockListDo {
    private String medicinalName;
    private String pinyinma;
    private BigDecimal stockNum;
}
