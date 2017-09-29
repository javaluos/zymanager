package com.zy.cms.service.manager.impl;

import com.zy.cms.controller.channel.SmsChannelMonitorAction;
import com.zy.cms.entity.SmsChannelMonitor;
import com.zy.cms.enums.*;
import com.zy.cms.mapper.manager.CdrMonitorChartMapper;
import com.zy.cms.mapper.manager.SmsChannelMonitorMapper;
import com.zy.cms.service.manager.SmsChannelMonitorService;
import com.zy.cms.util.DateUtil;
import com.zy.cms.util.StringUtil;
import com.zy.cms.vo.chart.*;
import com.zy.cms.vo.manager.CdrMonitorSetting;
import com.zy.cms.vo.manager.CdrMonitorStat;
import com.zy.cms.vo.query.ChartQuery;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("smsChannelMonitorService")
public class SmsChannelMonitorServiceImpl implements SmsChannelMonitorService {

    private static final Logger logger = Logger.getLogger(SmsChannelMonitorServiceImpl.class);

    @Autowired
    private SmsChannelMonitorMapper smsChannelMonitorMapperDao;

    @Autowired
    private CdrMonitorChartMapper cdrMonitorChartMapper;

    @Override
    public List<SmsChannelMonitor> querySmsChannelMonitorList(
            SmsChannelMonitor smsChannelMonitor) {
        return smsChannelMonitorMapperDao.querySmsChannelMonitorListByStartFlag(smsChannelMonitor);
    }

    @Override
    public SmsChannelMonitor findSmsChannelMonitor(
            SmsChannelMonitor smsChannelMonitor) {
        return smsChannelMonitorMapperDao.findSmsChannelMonitor(smsChannelMonitor);
    }

    @Override
    public void updateSmsChannelMonitor(SmsChannelMonitor smsChannelMonitor) {
        smsChannelMonitorMapperDao.updateByPrimaryKeySelective(smsChannelMonitor);
    }

    @Override
    public void insertSmsChannelMonitor(SmsChannelMonitor smsChannelMonitor) {
        smsChannelMonitorMapperDao.insert(smsChannelMonitor);
    }

    @Override
    public void delSmsChannelMonitor(SmsChannelMonitor smsChannelMonitor) {
        smsChannelMonitorMapperDao.updateByPrimaryKey(smsChannelMonitor);

    }

    @Override
    public SmsChannelMonitor findSmsChannelMonitorById(
            SmsChannelMonitor smsChannelMonitor) {
        return smsChannelMonitorMapperDao.selectByPrimaryKey(smsChannelMonitor.getId());
    }

    @Override
    public void monitorFlagChange(SmsChannelMonitor smsChannelMonitor) {
        smsChannelMonitorMapperDao.monitorFlagChange(smsChannelMonitor);
    }

    @Override
    public ChartUI queryMonitorChannelChartLine(ChartQuery chartQuery) throws Exception {
        ChartUI charData = null;
        charData = initChannelMonitorChart(chartQuery);
        if (charData == null) {
            charData = new ChartUI();
        }
        return charData;
    }

    private ChartUI initChannelMonitorChart(ChartQuery chartQuery) throws Exception {
        // 定义图表业务数据对象
        ChartUI charUI = new ChartUI();

        int monitorType = chartQuery.getMonitorType();// 查询子业务
        List<CdrMonitorStat> aStatList = new ArrayList<CdrMonitorStat>();
        if (StringUtils.isNotBlank(chartQuery.getChannelMainCode())) {
            aStatList = cdrMonitorChartMapper.queryMainMonitorStaForChannel(chartQuery);
        } else {
            aStatList = cdrMonitorChartMapper.queryMonitorStatForChannel(chartQuery);
        }
        Map<String, CdrMonitorStat> mapDatas = transformList2Map(aStatList);// 转换数据
        aStatList = null;

        int countLen = mapDatas.size() > 0 ? mapDatas.size() : 288; // (默认为5分钟间隔,长度为288)
        String[] setDataA = new String[countLen]; // A路统计数据
        String[] setDataB = new String[countLen]; // B路统计数据
        String yUnit = "";// y轴单位
        int countA = 0;
        int countB = 0;

        int index = 0;
        CdrMonitorStat stat = null;
        for (Map.Entry<String, CdrMonitorStat> data : mapDatas.entrySet()) {
            stat = data.getValue();
            if (monitorType == SmsMonitorTypeEnum.SMT_1.getType()) { // 并发
                setDataA[index] = stat.getTotalCallCount() + "";   //发送数
                setDataB[index] = (stat.getRingCounta() + stat.getRingCountb()) + ""; //状态报告数
                yUnit = UnitEnum.times;
                countA = countA + stat.getTotalCallCount();  //发送数总数
                countB = countB + (stat.getRingCounta() + stat.getRingCountb()); //状态报告总数
            } else if (monitorType == SmsMonitorTypeEnum.SMT_2.getType()) {// 成功率
                double successRatea = (double) stat.getSuccessRatea();
                setDataA[index] = successRatea / 100 + "";
                yUnit = UnitEnum.percent;
            } else if (monitorType == SmsMonitorTypeEnum.SMT_3.getType()) {// 失败率
                double successRateb = (double) stat.getSuccessRateb();
                setDataA[index] = successRateb / 100 + "";
                yUnit = UnitEnum.percent;
            } else if (monitorType == SmsMonitorTypeEnum.SMT_4.getType()) {// 未知率
                double responseRatea = (double) stat.getResponseRatea();
                setDataA[index] = responseRatea / 100 + "";
                yUnit = UnitEnum.percent;
            } else if (monitorType == SmsMonitorTypeEnum.SMT_5.getType()) {// 平均发送时长
                setDataA[index] = stat.getAverageTalkTimea() + "";
                yUnit = UnitEnum.second;
            } else if (monitorType == SmsMonitorTypeEnum.SMT_6.getType()) {// 平均状态报告时长
                setDataA[index] = stat.getAverageTalkTimeb() + "";
                yUnit = UnitEnum.second;
            }
            index++;
        }
        // 统计后处理
        settingUITags(chartQuery, charUI, setDataA, setDataB, countA, countB, yUnit);

        return charUI;
    }

    private void settingUITags(ChartQuery chartQuery, ChartUI charUI, String[] setDataA, String[] setDataB, int countA, int countB, String yUnit) {
        String businessId = chartQuery.getBusinessId();// 监控业务
        int monitorType = chartQuery.getMonitorType();// 查询子业务

        boolean existALin = false;// 存在A路
        String titleName = "";
        String tmpName = "";
        String titleSub = "——" + chartQuery.getMonitorDate();
        if (monitorType == SmsMonitorTypeEnum.SMT_1.getType()) { // 并发数
            titleName = SmsMonitorTypeEnum.SMT_1.getName();
            tmpName = UnitEnum.sms_send_prefix + titleName + "(" + countA + ")";
        } else if (monitorType == SmsMonitorTypeEnum.SMT_2.getType()) {// 成功率
            titleName = SmsMonitorTypeEnum.SMT_2.getName();
            tmpName = SmsMonitorTypeEnum.SMT_2.getName();
        } else if (monitorType == SmsMonitorTypeEnum.SMT_3.getType()) {// 失败率
            titleName = SmsMonitorTypeEnum.SMT_3.getName();
            tmpName = SmsMonitorTypeEnum.SMT_3.getName();
        } else if (monitorType == SmsMonitorTypeEnum.SMT_4.getType()) {// 未知率
            titleName = SmsMonitorTypeEnum.SMT_4.getName();
            tmpName = SmsMonitorTypeEnum.SMT_4.getName();
        } else if (monitorType == SmsMonitorTypeEnum.SMT_5.getType()) {// 平均发送时长
            titleName = SmsMonitorTypeEnum.SMT_5.getName();
            tmpName = SmsMonitorTypeEnum.SMT_5.getName();
        } else if (monitorType == SmsMonitorTypeEnum.SMT_6.getType()) {// 平均状态报告时长
            titleName = SmsMonitorTypeEnum.SMT_6.getName();
            tmpName = SmsMonitorTypeEnum.SMT_6.getName();
        }
        charUI.setTitle(new ChartTitle(titleName, titleSub));// 设置title
        // 设置 series
        ChartSeries seriesA = new ChartSeries();
        seriesA.setName(tmpName);
        seriesA.setyData(setDataA);
        charUI.getSeriesList().add(seriesA);
        // 设置 legend
        charUI.getLegends().getLegend().add(tmpName);
        if (monitorType == SmsMonitorTypeEnum.SMT_1.getType()) {
            ChartSeries seriesB = new ChartSeries();
            String tmpBName = UnitEnum.sms_report_prefix + titleName + "(" + countB + ")";
            seriesB.setName(tmpBName);
            seriesB.setyData(setDataB);
            charUI.getSeriesList().add(seriesB);
            // 设置 legend
            charUI.getLegends().getLegend().add(tmpBName);
        }

        // 设置左侧单位
        charUI.getAxis().setyAxisUnit(yUnit);
    }

    /**
     * 扩展Map数据 (保证287点)
     *
     * @param aStatList 查询的数据列表
     * @return
     */
    private Map<String, CdrMonitorStat> transformList2Map(List<CdrMonitorStat> aStatList) {
        Map<String, CdrMonitorStat> mapUtils = new ChartPonit().getDefaultMaps();
        if (aStatList != null && aStatList.size() > 0) {
            for (CdrMonitorStat stat : aStatList) {
                mapUtils.put(stat.getTimeString(), stat);
            }
        }
        return mapUtils;
    }

}
