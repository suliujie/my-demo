package slj;

import java.util.List;

public interface UserService {


    List<UserDTO> queryUserInfo();

    void update(UserDTO dto);
    List<TenantDTO> list();
}
