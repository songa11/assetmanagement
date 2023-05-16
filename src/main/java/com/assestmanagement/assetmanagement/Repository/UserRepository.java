package com.assestmanagement.assetmanagement.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import com.assestmanagement.assetmanagement.Model.User;
@Repository
public interface UserRepository  extends JpaRepository<User,Integer>{
    @Query("select b from User b where b.userName=?1 and b.password=?2")
    User login(String username,String password);
    User findById(int id);
}
