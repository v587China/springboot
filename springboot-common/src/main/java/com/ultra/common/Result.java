package com.ultra.common;

import lombok.Data;

@Data
public class Result {

	private int code;
	private String message;
	private Object result;

	public Result(int code) {
		super();
		this.code = code;
	}

	public Result(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public Result(int code, Object result) {
		super();
		this.code = code;
		this.result = result;
	}

	public Result(int code, String message, Object result) {
		super();
		this.code = code;
		this.message = message;
		this.result = result;
	}

}
