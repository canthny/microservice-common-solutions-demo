package com.canthny.mysql.data.init.dao;

import com.canthny.mysql.data.init.domain.PtOrderInfo;
import com.canthny.mysql.data.init.domain.PtOrderInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PtOrderInfoMapper {
    int insert(PtOrderInfo record);

    int insertSelective(PtOrderInfo record);

    List<PtOrderInfo> selectByExample(PtOrderInfoExample example);

    PtOrderInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PtOrderInfo record, @Param("example") PtOrderInfoExample example);

    int updateByExample(@Param("record") PtOrderInfo record, @Param("example") PtOrderInfoExample example);

    int updateByPrimaryKeySelective(PtOrderInfo record);

    int updateByPrimaryKey(PtOrderInfo record);
}