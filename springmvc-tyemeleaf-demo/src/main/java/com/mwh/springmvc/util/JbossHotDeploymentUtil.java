/**
 * 此工具类，用于控制jboss的热部署功能
 */
package com.mwh.springmvc.util;

import java.lang.management.ManagementFactory;

import javax.management.Attribute;
import javax.management.ObjectName;

import org.apache.log4j.Logger;

public class JbossHotDeploymentUtil {
	private static Logger log = Logger.getLogger(JbossHotDeploymentUtil.class);
	private static ObjectName OBJNAME = null;
	private static boolean SUCCESS = true;
	private static boolean FAIL = false;
	private static String OBJECTNAMESTR = "jboss.as.expr:subsystem=deployment-scanner,scanner=default";

	/**
	 * 查看jboss 热部署状态
	 * 
	 * @return
	 */
	public static Result getStatus() {
		Result result = isRegister();
		if (result.status && result.value) {
			try {
				boolean value = Boolean.valueOf(ManagementFactory
						.getPlatformMBeanServer()
						.getAttribute(OBJNAME, "scan-enabled").toString());
				return new Result(SUCCESS, value, null);
			} catch (Exception e) {
				return new Result(FAIL, false, e);
			}
		} else {
			return result;
		}
	}

	/**
	 * 更新jboss 热部署状态
	 * 
	 * @param status
	 * @return
	 */
	public static Result updateStatus(boolean status) {
		Result result = isRegister();
		if (result.status && result.value) {
			try {
				ManagementFactory.getPlatformMBeanServer().setAttribute(
						OBJNAME,
						new Attribute("scan-enabled", String.valueOf(status)));
				return new Result(SUCCESS, status, null);
			} catch (Exception e) {
				return new Result(FAIL, false, e);
			}
		} else {
			return result;
		}
	}

	/**
	 * 查看Mbean是否注册
	 * 
	 * @param on
	 * @return
	 */
	public static Result isRegister() {
		if (OBJNAME == null) {
			try {
				ObjectName	on = new ObjectName(OBJECTNAMESTR);
				
				boolean isRegistered = ManagementFactory
						.getPlatformMBeanServer().isRegistered(on);
				if (isRegistered) {
					OBJNAME = on;
					return new Result(SUCCESS, true, null);
				} else {
					return new Result(FAIL, false, new Exception("'"
							+ OBJECTNAMESTR + "' was not Registed "));
				}
			} catch (Exception e) {
				return new Result(FAIL, false, e);
			}
		} else {
			return new Result(SUCCESS, true, null);
		}
	}

	/**
	 * 持续查看Mbean已注册
	 * 
	 * @param on
	 * @return
	 */
	public static boolean isRegisterContinue() {
		Result result = isRegister();
		if (result.status && result.value) {
			log.info(" jboss hot deployment closed success");
			return true;
		} else {
			try {
				Thread.sleep(5000l);
			} catch (InterruptedException e) {
				log.error("an error occurred", e);;
			}
			return isRegisterContinue();
		}

	}

	public static class Result {
		private boolean status;
		private boolean value;
		private Exception e;

		public Result(boolean status, Boolean value, Exception e) {
			this.status = status;
			this.value = value;
			this.e = e;
		}

		public boolean isStatus() {
			return status;
		}

		public boolean isValue() {
			return value;
		}

		public Exception getE() {
			return e;
		}

	}
}
