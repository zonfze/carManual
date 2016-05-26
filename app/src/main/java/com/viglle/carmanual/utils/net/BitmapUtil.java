package com.viglle.carmanual.utils.net;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * Created by Administrator on 2016/5/24.
 */
public class BitmapUtil {
    /**
     * 根据指定的高宽缩放图片
     * @param bitmap
     * @param newWidth
     * @param newHeight
     * @return
     */
    public static Bitmap zoomImage(Bitmap bitmap,int newWidth,int newHeight){
        int width=bitmap.getWidth();
        int height=bitmap.getHeight();

        float scaleWidth=newWidth/width;
        float scaleHeight=newHeight/height;

        Matrix matrix=new Matrix();
        matrix.postScale(scaleWidth,scaleHeight);

        Bitmap bit = Bitmap.createBitmap(bitmap,0,0,width,height,matrix,true);

        return bit;
    }
}
