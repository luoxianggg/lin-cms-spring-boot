package io.github.talelin.latticy.service.impl;

import io.github.talelin.latticy.mapper.SysInfoMapper;
import io.github.talelin.latticy.model.FndDocNumbersDO;
import io.github.talelin.latticy.service.SysInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class SysInfoServiceImpl implements SysInfoService {
   @Autowired
    SysInfoMapper sysInfoMapper;
    @Override
    public String getDocNumbers(String functioCode) {
        String docNumber = "";
        String nextFlowNumber = "";
        Map<String,String> paraMap = new HashMap<>();
        FndDocNumbersDO docNumbersDO = sysInfoMapper.getFunDocNumberRules(functioCode);
        LocalDate localDate = LocalDate.now();
        String yearMonth = "" +localDate.getYear()  + localDate.getMonthValue();
        if (yearMonth.equals(docNumbersDO.getDates())){
            //获取最新单据号
            docNumber = docNumbersDO.getPre() + docNumbersDO.getDates() + String.format("%0" + 4 + "d", Integer.parseInt(docNumbersDO.getFlowNumber()));
            nextFlowNumber = String.format("%0" + 4 + "d", Integer.parseInt(docNumbersDO.getFlowNumber()) + 1);
            paraMap.put("dates",docNumbersDO.getDates());
            paraMap.put("flow_number",nextFlowNumber);
            paraMap.put("function_code",functioCode);
            sysInfoMapper.updateFunDocNumberRules(paraMap);
        }else{
            //更新规则流水号置为0001，dates 置为 yearMonth

            paraMap.put("dates",yearMonth);
            paraMap.put("flow_number","0001");
            sysInfoMapper.updateFunDocNumberRules(paraMap);
            //获取最新单据号
            docNumber = docNumbersDO.getPre() + yearMonth + "0001";
        }
        return docNumber;
    }
}
