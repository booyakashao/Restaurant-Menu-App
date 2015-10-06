package com.example.wen.foodmenuprinter;

import java.text.DecimalFormat;

/**
 * Created by Wen on 10/5/2015.
 */
public class CommonUtils {

    public static String convertDoubleToPrice(Double price) {

        return "$ " + new DecimalFormat("#0.00").format(price);
    }
}
