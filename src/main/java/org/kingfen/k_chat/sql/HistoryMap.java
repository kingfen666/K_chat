package org.kingfen.k_chat.sql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.kingfen.k_chat.sql.Bean.History;

@Mapper
public interface HistoryMap extends BaseMapper<History> {
}
