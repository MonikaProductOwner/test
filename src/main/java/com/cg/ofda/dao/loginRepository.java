package com.cg.ofda.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.ofda.model.Login;

public interface loginRepository extends JpaRepository<Login,String>  {

}
