package com.ultra.web;

import org.springframework.http.ResponseEntity;

import com.ultra.common.Result;

public abstract class BaseController<BaseService<?extends Iservice>,T> {

	public ResponseEntity<Result> insert(){
		
	}
}
