package org.kingfen.k_chat.sql;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.kingfen.k_chat.sql.Bean.Friend;
import org.kingfen.k_chat.sql.Bean.History;

@Mapper
@TableName("friends")
public interface FriendMap extends BaseMapper<Friend> {
}
