package com.ultra.common;

public class ResultBuilder {

	private ResultBuilder() {
	}

	public static Result ok() {
		return new Result(ResultType.SUCCESS.getCode());
	}

	public static Result ok(Object result) {
		return new Result(ResultType.SUCCESS.getCode(), result);
	}

	public static Result bad() {
		return new Result(ResultType.FIELD_ILLEAGAL.getCode(), ResultType.FIELD_ILLEAGAL.getMessage());
	}

	public static Result bad(String message) {
		return new Result(ResultType.FIELD_ILLEAGAL.getCode(), message);
	}

	public static Result error() {
		return new Result(ResultType.SERVER_ERROR.getCode(), ResultType.SERVER_ERROR.getMessage());
	}

	public static Result error(String message) {
		return new Result(ResultType.SERVER_ERROR.getCode(), message);
	}

	public static Result build(int code, String message, Object result) {
		return new Result(code, message, result);
	}

}
