package com.mwh.springboot.common.utils;

public class ConstantUtils {

	public static enum TRANSFER_TYPE {
		FILE("1"), // 文件
		DIRECTORY("2"); // 目录

		private String value;

		TRANSFER_TYPE(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	public static enum MEMBER_TYPE {
		CENTER("A"), // 总公司
		BEUREAU("B"), // 路局
		STATION("C1"), // 车站
		EMU("C2"), // 动车所
		TRAIN("D"); // 列车
		private String value;

		MEMBER_TYPE(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	public static enum SERVICE_TYPE {
		TRANSFER("1"), // 传输
		OTHER("2"); // 待定

		private String value;

		SERVICE_TYPE(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	public static enum SERVICE_STATUS {
		ON(1), // 正常
		OFF(0); // 无效

		private Integer value;

		SERVICE_STATUS(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return this.value;
		}
	}

	public static enum TRANSSERVICE {
		TRANSFER("/doTransfer"), DOWNLOAD("/doDownload");
		private String name;

		private TRANSSERVICE(String name) {
			this.name = name;
		}

		public String value() {
			return name;
		}
	}

	public static enum OPTYPE {
		TRANSFERDATA((byte) (0)), DOWNLOADMETADATA((byte) (1)), DOWNLOADDATA((byte) (2));
		private byte value;

		private OPTYPE(byte value) {
			this.value = value;
		}

		public byte value() {
			return value;
		}
	}

	public static enum SYNC_STATUS {
		// 数据同步状态：已同步、未同步

		WAITING(0), // 待启动
		FINISHED(1); // 结束

		private Integer value;

		SYNC_STATUS(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return this.value;
		}
	}

	public static enum REPORT_STATUS {
		// 报告状态：已同步、未同步

		NO_REPORT(0), // 不可报告
		CAN_REPORT(1), // 可报告
		FINISHED(2); // 报告完成

		private Integer value;

		REPORT_STATUS(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return this.value;
		}
	}
	public static enum TASK_STATUS {
		// 任务状态：待启动、启动、取消,结束

		WAITING(0), // 待启动
		RUNNING(1), // 启动
		CANCEL(2), // 取消
		RESTART(3), // 重启
		PAUSE(4), // 暂停
		INVALID(5), // 失效
		FAIL(8), // 失败
		END(9); // 结束

		private Integer value;

		TASK_STATUS(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return this.value;
		}
	}
	/**
	 * WAITTRANSFER  等待传输
	 * TRANSFERRING  传输中
	 * FAIL 传输失败，允许尝试重新发送
	 * INVALID 传输失效，不允许尝试重新发送
	 * @author alei
	 *
	 */
	public static enum TRANSFERBLOCKSTATUS {
		WAITTRANSFER(0), TRANSFERRING(1), SUCCESS(2), FAIL(3), INVALID(4);
		private int value;

		TRANSFERBLOCKSTATUS(int value) {
			this.value = value;
		}

		public int value() {
			return this.value;
		}
	}


	public static enum TRANSRESPONSECODE {
		SUCCESS(0), FAIL(1);
		private int value;

		TRANSRESPONSECODE(int value) {
			this.value = value;
		}

		public int value() {
			return this.value;
		}
	}

	public static enum TRANSFERSERVERSERVICE {
		DOLOADDATA("transfer/loadData.do"), DOLOADMETADATA("transfer/loadMetaData.do"), DORECEIVE(
				"transfer/receiveData.do");
		private String name;

		private TRANSFERSERVERSERVICE(String name) {
			this.name = name;
		}

		public String value() {
			return name;
		}
	}
}
