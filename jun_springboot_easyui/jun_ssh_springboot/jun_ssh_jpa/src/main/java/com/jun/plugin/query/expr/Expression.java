package com.jun.plugin.query.expr;

public abstract class Expression {
	protected Operator op;

	protected boolean ignoreCase = DEFAULT_IGNORE_CASE;
	protected boolean ignoreEmpty = DEFAULT_IGNORE_EMPTY;
	
	/** 默认是否忽略大小写 */
	public static final boolean DEFAULT_IGNORE_CASE = false;
	public static final boolean DEFAULT_IGNORE_EMPTY = false;

	public abstract Expression igCase();

	public abstract Expression igEmpty();
	
	public boolean isIgnoreCase() {
		return ignoreCase;
	}

	public void setIgnoreCase(boolean ignoreCase) {
		this.ignoreCase = ignoreCase;
	}

	public boolean isIgnoreEmpty() {
		return ignoreEmpty;
	}

	public void setIgnoreEmpty(boolean ignoreEmpty) {
		this.ignoreEmpty = ignoreEmpty;
	}
	
	
	public Operator getOp(){
		return op;
	}
	public abstract boolean hasEmptyValue();

	/**
	 * 运算符
	 */
	public enum Operator {

		/** 等于 */
		EQ {
			@Override
			public String toString() {
				return "=";
			}
		},

		/** 不等于 */
		NE {
			@Override
			public String toString() {
				return "<>";
			}
		},

		/** 大于 */
		GT {
			@Override
			public String toString() {
				return ">";
			}
		},

		/** 小于 */
		LT {
			@Override
			public String toString() {
				return "<";
			}
		},

		/** 大于等于 */
		GTE {
			@Override
			public String toString() {
				return ">=";
			}
		},

		/** 小于等于 */
		LTE {
			@Override
			public String toString() {
				return "<=";
			}
		},

		/** 相似 */
		LIKE {
			@Override
			public String toString() {
				return "LIKE";
			}
		},
		CONTAIN{
			@Override
			public String toString() {
				return "CONTAIN";
			}
		},
		/** 包含 */
		IN {
			@Override
			public String toString() {
				return "IN";
			}
		},

		/** 之间 */
		between,

		/** 为Null */
		isNull{
			@Override
			public String toString() {
				return "IS NULL";
			}
		},

		/** 不为Null */
		isNotNull{
			@Override
			public String toString() {
				return "IS NOT NULL";
			}
		}
	}
}
