package io.github.talelin.latticy.service.medicinal;

import io.github.talelin.latticy.exception.BaseException;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface MedicinalSellService {
    public String prescribeCreate(Map<String,Object> map) throws BaseException;
}
