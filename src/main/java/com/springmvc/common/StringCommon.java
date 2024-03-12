package com.springmvc.common;

import java.text.DecimalFormat;

public class StringCommon {
	public static String formatCurrencyString(double number) {
		return new DecimalFormat("###,###").format(number);
	}

	public static String convertDoubleToString(double number) {
		return new DecimalFormat("#").format(number);
	}
}
