package com.ultra.repository;

import java.io.Serializable;

import org.springframework.data.repository.Repository;

public interface IRepository<T> extends  Repository<T, Serializable>{
	

}
