package org.kingfen.k_chat.sql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.kingfen.k_chat.sql.Bean.User;
import org.springframework.stereotype.Service;

@Service
public interface UserMap extends BaseMapper<User> {
    @Select("select * from users.users where (username=#{username}||uid=#{username}||mail=#{username})&&password=#{password};")
    User login(String username,String password);
    @Select("SELECT COUNT(*) FROM users.users;")
    Integer getNum();
    default Integer getID(){
        return getNum()+10000001;
    }

}
