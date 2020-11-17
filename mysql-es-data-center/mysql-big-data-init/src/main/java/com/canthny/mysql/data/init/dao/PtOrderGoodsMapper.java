package com.canthny.mysql.data.init.dao;

import com.canthny.mysql.data.init.domain.PtOrderGoods;
import com.canthny.mysql.data.init.domain.PtOrderGoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PtOrderGoodsMapper {
    int insert(PtOrderGoods record);

    int insertSelective(PtOrderGoods record);

    List<PtOrderGoods> selectByExample(PtOrderGoodsExample example);

    PtOrderGoods selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PtOrderGoods record, @Param("example") PtOrderGoodsExample example);

    int updateByExample(@Param("record") PtOrderGoods record, @Param("example") PtOrderGoodsExample example);

    int updateByPrimaryKeySelective(PtOrderGoods record);

    int updateByPrimaryKey(PtOrderGoods record);
}