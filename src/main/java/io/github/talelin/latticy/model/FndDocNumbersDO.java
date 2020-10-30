package io.github.talelin.latticy.model;

import lombok.Data;

@Data
public class FndDocNumbersDO extends BaseModelNoDelete {
    private String pre;
    private String dates;
    private String flowNumber;
    private String functionCode;
}
