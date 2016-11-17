package com.zheng.springboot.service;

import com.zheng.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ZhangShuzheng on 2016/11/17.
 */
public interface UserRepository extends JpaRepository<User, Long> {

	User findByName(String name);

	User findByNameAndAge(String name, Integer age);

//	@Query("from user u where u.name=:name")
//	User findUser(@Param("name") String name);
}
