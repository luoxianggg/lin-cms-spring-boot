package io.github.talelin.latticy.service.impl.medicinal;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import io.github.talelin.latticy.exception.BaseException;
import io.github.talelin.latticy.mapper.SysInfoMapper;
import io.github.talelin.latticy.mapper.medicinal.MedicinalSellMapper;
import io.github.talelin.latticy.mapper.medicinal.MedicinalStockMapper;
import io.github.talelin.latticy.model.medicinal.CustomerDO;
import io.github.talelin.latticy.model.medicinal.MedicinalSellsDO;
import io.github.talelin.latticy.model.medicinal.PrescribeDO;
import io.github.talelin.latticy.service.SysInfoService;
import io.github.talelin.latticy.service.medicinal.MedicinalSellService;
import io.github.talelin.latticy.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MedicinalSellServiceImpl implements MedicinalSellService {

    @Autowired
    MedicinalSellMapper medicinalSellMapper;
    @Autowired
    SysInfoMapper sysInfoMapper;
    @Autowired
    MedicinalStockMapper medicinalStockMapper;
    @Autowired
    SysInfoService sysInfoService;
    /*
    * 问诊单新增
    * */
    @Override
    @Transactional
    public String prescribeCreate(Map<String, Object> map) throws BaseException {

        //1.新增问诊单
        //1.1如果单上人员为系统不存在则新增该顾客信息
        if(!StringUtil.hasKeyValue(map,"customerId")){
            if(StringUtil.hasKeyValue(map,"customerName") && StringUtil.hasKeyValue(map,"customerTell")){
                medicinalSellMapper.insertNewCustomer(map);
                List<CustomerDO> customerDOList = medicinalSellMapper.getCustomers(map);
                if(customerDOList.size() == 0){
                    new BaseException("获取顾客信息异常");
                }else{
                    map.put("customerId",customerDOList.get(0).getId());
                }
            }
            else{
                new BaseException("获取顾客信息异常");
            }
        }
        PrescribeDO prescribeDO = new PrescribeDO();
        String docNumber = sysInfoService.getDocNumbers("MEDI_SELL");
        prescribeDO.setPrescribeNumber(docNumber);
        prescribeDO.setCustomerId(new BigDecimal(map.get("customerId").toString()));
        prescribeDO.setDescription(map.get("description").toString());
        medicinalSellMapper.insert(prescribeDO);
        PrescribeDO prescribeDO1 = medicinalSellMapper.getPrescribesByDocNum(docNumber);
        map.put("funPrescribeId",prescribeDO1.getId());
        //2.添加用药信息
        String mediSellLines = JSON.toJSONString(map.get("sellLines"));
        List<MedicinalSellsDO> medicinalSellsDOList = JSONArray.parseArray(mediSellLines,MedicinalSellsDO.class);
        for (MedicinalSellsDO medicinalSellsDO :medicinalSellsDOList){
            //检验销售药品是否存在，是否有库存
            Map<String,Object> param1 = new HashMap<String, Object>();
            param1.put("funMediId",medicinalSellsDO.getFunMediId());
            param1.put("medicinalStoreId",map.get("medicinalStoreId").toString());
            BigDecimal stockNum = medicinalStockMapper.getStockMedicinals(param1);
           if(stockNum.longValue() <= 0){
               new BaseException("药品 "+ medicinalSellsDO.getFunMediName()+" 库存不足");
           }else{
               //扣减库存
               param1.put("newStock",stockNum.intValue() - medicinalSellsDO.getSellNum().intValue());
               medicinalStockMapper.updateMedicinalStocks(param1);
           }
        }
        map.put("sellLines",medicinalSellsDOList);
        medicinalSellMapper.saveMedicinalSells(map);
        return docNumber;
    }
}
