package org.kingfen.k_chat.sql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.kingfen.k_chat.sql.Bean.SmS;

@Mapper
public interface smsMap extends BaseMapper<SmS> {
}
