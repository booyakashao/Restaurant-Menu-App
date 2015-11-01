package com.example.wen.foodmenuprinter;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.pdf.PrintedPdfDocument;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Wen on 10/26/2015.
 */
public class MyPrintDocumentAdapter extends PrintDocumentAdapter {

    private Activity currentActivity;
    private List<String> documentContents;
    private int pageHeight;
    private int pageWidth;
    public PdfDocument mPdfDocument;

    public MyPrintDocumentAdapter(Activity targetActivity, List<String> documentContents) {
        currentActivity = targetActivity;
        this.documentContents = documentContents;
    }

    private void drawPage(PdfDocument.Page page, int pageNumber) {
        Canvas canvas = page.getCanvas();

        int titleBaseLine = 30;
        int leftMargin = 1;

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(9);

        for(String currentTextString : documentContents) {
            canvas.drawText(
                    currentTextString,
                    leftMargin,
                    titleBaseLine,
                    paint);
            titleBaseLine += 12;
        }
    }

    @Override
    public void onWrite(PageRange[] pageRanges, ParcelFileDescriptor destination, CancellationSignal cancellationSignal, WriteResultCallback callback) {
        PdfDocument.PageInfo newPage = new PdfDocument.PageInfo.Builder(203, pageHeight, 1).create();

        PdfDocument.Page page = mPdfDocument.startPage(newPage);

        if (cancellationSignal.isCanceled()) {
            callback.onWriteCancelled();
            mPdfDocument.close();
            mPdfDocument = null;
            return;
        }

        drawPage(page, 1);
        mPdfDocument.finishPage(page);

        try {
            mPdfDocument.writeTo(new FileOutputStream(destination.getFileDescriptor()));
        } catch (IOException e) {
            callback.onWriteFailed(e.toString());
            return;
        } finally {
            mPdfDocument.close();
            mPdfDocument = null;
        }

        callback.onWriteFinished(pageRanges);
    }

    @Override
    public void onLayout(PrintAttributes oldAttributes, PrintAttributes newAttributes, CancellationSignal cancellationSignal, LayoutResultCallback callback, Bundle extras) {
        mPdfDocument = new PrintedPdfDocument(currentActivity.getApplicationContext(), newAttributes);

        pageHeight = newAttributes.getMediaSize().getHeightMils()/1000 * 72;
        //pageWidth = newAttributes.getMediaSize().getWidthMils()/1000 * 72;
        pageWidth = 203;

        if (cancellationSignal.isCanceled()) {
            callback.onLayoutCancelled();
            return;
        }


        PrintDocumentInfo.Builder builder = new PrintDocumentInfo
                .Builder("order_receipt.pdf")
                .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
                .setPageCount(1);
        PrintDocumentInfo info = builder.build();
        callback.onLayoutFinished(info, true);

    }

    private Activity getActivity() {
        return currentActivity;
    }
}
