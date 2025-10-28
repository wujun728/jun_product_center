package com.ruoyi.plugin.tenant;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

/**
 * 切换租户执行工具
 * @author wangzongrun
 */
@Slf4j
@UtilityClass
public class TenantBroker {

	@FunctionalInterface
	public interface RunAs<T> {

		/**
		 * 执行业务逻辑
		 * @param tenantId
		 * @throws Exception
		 */
		void run(T tenantId) throws Exception;

	}

	@FunctionalInterface
	public interface ApplyAs<T, R> {

		/**
		 * 执行业务逻辑,返回一个值
		 * @param tenantId
		 * @return
		 * @throws Exception
		 */
		R apply(T tenantId) throws Exception;

	}

	/**
	 * 以某个租户的身份运行
	 * @param tenant 租户ID
	 * @param func
	 */
	public void runAs(Integer tenant, RunAs<Integer> func) {
		final Integer pre = TenantContextHolder.getTenantId();
		try {
			log.trace("TenantBroker 切换租户{} -> {}", pre, tenant);
			TenantContextHolder.setTenantId(tenant);
			func.run(tenant);
		}
		catch (Exception e) {
			throw new TenantBrokerExceptionWrapper(e.getMessage(), e);
		}
		finally {
			log.trace("TenantBroker 还原租户{} <- {}", pre, tenant);
			TenantContextHolder.setTenantId(pre);
		}
	}

	/**
	 * 以某个租户的身份运行
	 * @param tenant 租户ID
	 * @param func
	 * @param <T> 返回数据类型
	 * @return
	 */
	public <T> T applyAs(Integer tenant, ApplyAs<Integer, T> func) {
		final Integer pre = TenantContextHolder.getTenantId();
		try {
			log.trace("TenantBroker 切换租户{} -> {}", pre, tenant);
			TenantContextHolder.setTenantId(tenant);
			return func.apply(tenant);
		}
		catch (Exception e) {
			throw new TenantBrokerExceptionWrapper(e.getMessage(), e);
		}
		finally {
			log.trace("TenantBroker 还原租户{} <- {}", pre, tenant);
			TenantContextHolder.setTenantId(pre);
		}
	}

	/**
	 * 以某个租户的身份运行
	 * @param supplier
	 * @param func
	 */
	public void runAs(Supplier<Integer> supplier, RunAs<Integer> func) {
		runAs(supplier.get(), func);
	}

	/**
	 * 以某个租户的身份运行
	 * @param supplier
	 * @param func
	 * @param <T> 返回数据类型
	 * @return
	 */
	public <T> T applyAs(Supplier<Integer> supplier, ApplyAs<Integer, T> func) {
		return applyAs(supplier.get(), func);
	}

	public static class TenantBrokerExceptionWrapper extends RuntimeException {

		public TenantBrokerExceptionWrapper(String message, Throwable cause) {
			super(message, cause);
		}

		public TenantBrokerExceptionWrapper(Throwable cause) {
			super(cause);
		}

	}

}
