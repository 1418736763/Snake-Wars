package snakeWars.mapper;

import snakeWars.domain.User;

/**
 * @author liuxiaoyu
 * @version 1.0
 */

public interface UserMapper {
    User selectUserByLoginAct(String loginAct);
    int insertUser(User user);
}
