package org.tensorflow.lite.examples.detection;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

public class Image extends View{
    public Image(Context context){
        super(context);
    }
    public void onDraw(Canvas canvas){

        Bitmap my_img = BitmapFactory.decodeResource
                (getResources(), R.drawable.ic_launcher);

        canvas.drawBitmap(my_img, 0, 0, null);
    }
}
