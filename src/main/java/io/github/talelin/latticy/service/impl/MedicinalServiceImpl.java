package io.github.talelin.latticy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.talelin.latticy.common.mybatis.Page;
import io.github.talelin.latticy.mapper.MedicinalMapper;
import io.github.talelin.latticy.mapper.MedicinalStockMapper;
import io.github.talelin.latticy.model.MedicinalDO;
import io.github.talelin.latticy.model.MedicinalInStockDO;
import io.github.talelin.latticy.service.MedicinalService;
import io.github.talelin.latticy.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author generator@TaleLin
 * @since 2020-10-18
 */
@Service
public class MedicinalServiceImpl extends ServiceImpl<MedicinalMapper, MedicinalDO> implements MedicinalService {

    @Autowired
    private MedicinalMapper medicinalMapper;
    @Autowired
    private MedicinalStockMapper medicinalStockMapper;
    @Override
    public void insertMedicinal(Map<String, Object> map) {
        MedicinalDO medicinalDO = new MedicinalDO();
        System.out.println(map.get("medicinal_name").toString());
        medicinalDO.setMedicinalName(map.get("medicinal_name").toString());
        medicinalDO.setPinyinma(map.get("pinyinma").toString()+ "_1");
        medicinalDO.setSpec(map.get("spec").toString());
        medicinalDO.setUnit(map.get("unit").toString());
        medicinalDO.setDescription(map.get("description").toString());
        medicinalMapper.insertMedicinal(map);
        //medicinalMapper.insert(medicinalDO);
    }

    @Override
    public Page<MedicinalDO> queryMedicinalList(Map<String,Object> map){

            Page<MedicinalDO> page = new Page<MedicinalDO>(StringUtil.getStrToInt(map.get("pageNum").toString()) -1,
                    StringUtil.getStrToInt(map.get("pageSize").toString()));
            page.setRecords(medicinalMapper.queryMedicinals(page,map)) ;
        return page;
    }
    @Override
    public MedicinalDO getMedicinalDetails(Map<String,Object> map){
        return medicinalMapper.queryMedicinalDetailById(map);
    }

    @Override
    public void updateMedicinal(Map<String, Object> map) {
        map.put("pinyinma",map.get("pinyinma").toString().toUpperCase());
        medicinalMapper.updateMedicinal(map);
    }

    @Override
    public void deleteMedicinal(Map<String, Object> map) {
        medicinalMapper.deleteMedicinal(map);
    }

    @Override
    public String doMedicinalInstock(Map<String, Object> map) {
        //初始化入库单信息
        MedicinalInStockDO medicinalInStockDO = new MedicinalInStockDO();
        medicinalInStockDO.setApproveBatchId(map.get("approve_batch_id").toString());
        medicinalInStockDO.setBatchId(map.get("batch_id").toString());
        medicinalInStockDO.setDescription(map.get("description").toString());
        medicinalInStockDO.setFactory(map.get("factory").toString());
        medicinalInStockDO.setFunMediId(StringUtil.getStrToInt(map.get("fun_medi_id").toString()));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        medicinalInStockDO.setInvalidDade(LocalDateTime.parse(map.get("invalid_dade").toString(), dateTimeFormatter));
        medicinalInStockDO.setProduceDate(LocalDateTime.parse(map.get("produce_date").toString(), dateTimeFormatter));
        medicinalInStockDO.setMedicinalStoreId(StringUtil.getStrToInt(map.get("medicinal_store_id").toString()));
        medicinalInStockDO.setPrice(new BigDecimal(map.get("price").toString()));
        medicinalInStockDO.setAmount(StringUtil.getStrToInt(map.get("amount").toString()));
        String mediNumber = medicinalStockMapper.getMaxMedicinalNumbers(map);
        long flowNumber = (long) StringUtil.getStrToInt(mediNumber.substring(8));
        medicinalInStockDO.setFlowNumber(flowNumber);
        medicinalInStockDO.setInstockNumber(mediNumber);
        medicinalStockMapper.insert(medicinalInStockDO);

        //更新库存信息
        //1. 获取该药品最新库存信息
        System.out.println(medicinalStockMapper.getStockMedicinals(map));
        BigDecimal stockNum = medicinalStockMapper.getStockMedicinals(map);
        if (stockNum != null) {
        //2. 根据新入库信息更新库存数量
            medicinalStockMapper.updateMedicinalStocks(map);
        }else{
            //2.1 如果入库时未找到库存信息则新建库存信息，并初始化库存
            medicinalStockMapper.initMedicinalStock(map);
        }

        return mediNumber;
    }
}
