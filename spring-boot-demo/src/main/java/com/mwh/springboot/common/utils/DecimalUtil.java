package com.mwh.springboot.common.utils;

import java.text.DecimalFormat;

public class DecimalUtil {
	public static double decimalTwo(double num) {
		return Double.parseDouble(new DecimalFormat("#.00").format(num));
	}
}
