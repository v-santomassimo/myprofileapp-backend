package com.vsan.myprofileapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vsan.myprofileapp.dao.Friendship;


@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Long>{

}
