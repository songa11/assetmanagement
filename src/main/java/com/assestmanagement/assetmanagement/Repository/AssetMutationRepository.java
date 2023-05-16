package com.assestmanagement.assetmanagement.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.assestmanagement.assetmanagement.Model.AssetMutation;
import com.assestmanagement.assetmanagement.Model.User;

public interface AssetMutationRepository extends JpaRepository<AssetMutation,Integer>   
{   @Query("select b from AssetMutation b where b.userList=?1")
    List<AssetMutation>findUserAsset(User user);
    AssetMutation findById(int id);
}
