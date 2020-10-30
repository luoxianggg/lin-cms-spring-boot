package io.github.talelin.latticy.mapper;

import io.github.talelin.latticy.model.FndDocNumbersDO;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface SysInfoMapper {
    public FndDocNumbersDO getFunDocNumberRules(String funCode);
    public void updateFunDocNumberRules(Map<String,String> map);
}
