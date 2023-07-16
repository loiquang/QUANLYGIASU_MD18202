package com.example.quanlygiasu_md18202_duan1.AdminActivity.AdminActivity;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;




public class SignatureView extends View {
    private Path signaturePath;
    private Paint signaturePaint;

    //khởi tạo các thuộc tính ban đầu, gọi phương thức init()
    public SignatureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    //khởi tạo, lưu dạng chữ ký
    private void init() {
        signaturePath = new Path();//lưu dạng chữ ký
        signaturePaint = new Paint(Paint.ANTI_ALIAS_FLAG);//định dạng chữ ký
        signaturePaint.setColor(Color.BLACK);
        signaturePaint.setStyle(Paint.Style.STROKE);
        signaturePaint.setStrokeWidth(5);
    }

    //ghi đè để vẽ chữ ký
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(signaturePath, signaturePaint);
    }

    // Xử lý sự kiện vẽ chữ ký
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                signaturePath.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                signaturePath.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                // Khi ngón tay được nhấc lên, kết thúc vẽ chữ ký
                break;
        }

        invalidate();
        return true;
    }

    // Xóa chữ ký
    public void clearSignature() {
        signaturePath.reset();
        invalidate();
    }

    // Lấy chữ ký dưới dạng Bitmap
    public Bitmap getSignatureBitmap() {
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        draw(canvas);
        return bitmap;
    }

}


