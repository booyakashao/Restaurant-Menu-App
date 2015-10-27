package com.example.wen.foodmenuprinter;

import android.app.Activity;
import android.content.Context;
import android.print.PrintAttributes;
import android.print.PrintManager;

import java.text.DecimalFormat;

/**
 * Created by Wen on 10/5/2015.
 */
public class CommonUtils {

    public static String convertDoubleToPrice(Double price) {

        return "$ " + new DecimalFormat("#0.00").format(price);
    }

    public static String convertToPercentage(Double percentage) {
        return new DecimalFormat("#0.00").format(percentage);
    }

    public void doPrint(Activity targetActivity) {
        PrintManager printManager = (PrintManager) targetActivity.getSystemService(Context.PRINT_SERVICE);

        // Set job name, which will be displayed in the print queue
        String jobName = targetActivity.getString(R.string.app_name) + " Receipt";

        // Start a print job, passing in a PrintDocumentAdapter implementation
        // to handle the generation of a print document
        printManager.print(jobName, new MyPrintDocumentAdapter(targetActivity), new PrintAttributes.Builder().build());
    }
}
