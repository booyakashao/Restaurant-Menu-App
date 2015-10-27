package com.example.wen.foodmenuprinter;

import android.app.Activity;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.pdf.PrintedPdfDocument;

/**
 * Created by Wen on 10/26/2015.
 */
public class MyPrintDocumentAdapter extends PrintDocumentAdapter {

    private Activity currentActivity;

    public MyPrintDocumentAdapter(Activity targetActivity) {
        currentActivity = targetActivity;
    }

    @Override
    public void onWrite(PageRange[] pages, ParcelFileDescriptor destination, CancellationSignal cancellationSignal, WriteResultCallback callback) {

    }

    @Override
    public void onLayout(PrintAttributes oldAttributes, PrintAttributes newAttributes, CancellationSignal cancellationSignal, LayoutResultCallback callback, Bundle extras) {

    }

    private Activity getActivity() {
        return currentActivity;
    }
}
