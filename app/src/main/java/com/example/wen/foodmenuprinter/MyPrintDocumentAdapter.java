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

/**
 * Created by Wen on 10/26/2015.
 */
public class MyPrintDocumentAdapter extends PrintDocumentAdapter {

    private Activity currentActivity;
    private int pageHeight;
    private int pageWidth;
    public PdfDocument mPdfDocument;
    public int totalPages = 4;

    public MyPrintDocumentAdapter(Activity targetActivity) {
        currentActivity = targetActivity;
    }

    private void drawPage(PdfDocument.Page page, int pageNumber) {
        Canvas canvas = page.getCanvas();

        pageNumber++;

        int titleBaseLine = 72;
        int leftMargin = 54;

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(40);
        canvas.drawText(
                "Test Print Document Page " + pageNumber,
                leftMargin,
                titleBaseLine,
                paint);

        paint.setTextSize(14);
        canvas.drawText("This is some test content to verify that custom document printing works", leftMargin, titleBaseLine + 35, paint);

        if (pageNumber % 2 == 0) {
            paint.setColor(Color.RED);
        } else {
            paint.setColor(Color.GREEN);
        }

        PdfDocument.PageInfo pageInfo = page.getInfo();

        canvas.drawCircle(pageInfo.getPageWidth() / 2,
                        pageInfo.getPageHeight() / 2,
                        150,
                        paint);
    }

    private boolean pageInRange(PageRange[] pageRanges, int page) {
        for(int i = 0; i < pageRanges.length; i++) {
            if ((page >= pageRanges[i].getStart()) && (page <= pageRanges[i].getEnd())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onWrite(PageRange[] pageRanges, ParcelFileDescriptor destination, CancellationSignal cancellationSignal, WriteResultCallback callback) {
        for(int i = 0; i < totalPages; i++) {
            if(pageInRange(pageRanges, i)) {
                PdfDocument.PageInfo newPage = new PdfDocument.PageInfo.Builder(pageWidth,
                        pageHeight, i).create();

                PdfDocument.Page page = mPdfDocument.startPage(newPage);

                if (cancellationSignal.isCanceled()) {
                    callback.onWriteCancelled();
                    mPdfDocument.close();
                    mPdfDocument = null;
                    return;
                }
                drawPage(page, i);
                mPdfDocument.finishPage(page);
            }
        }

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
        pageWidth = newAttributes.getMediaSize().getWidthMils()/1000 * 72;

        if (cancellationSignal.isCanceled()) {
            callback.onLayoutCancelled();
            return;
        }

        if (totalPages > 0 ) {
            PrintDocumentInfo.Builder builder = new PrintDocumentInfo
                    .Builder("print_output.pdf")
                    .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
                    .setPageCount(totalPages);
            PrintDocumentInfo info = builder.build();
            callback.onLayoutFinished(info, true);
        } else {
            callback.onLayoutFailed("Page count is zero.");
        }
    }

    private Activity getActivity() {
        return currentActivity;
    }
}
