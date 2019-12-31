package com.daxue.studyandroid.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.daxue.studyandroid.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * error
 * android 7.0版本以前
 * //加载路径
 * Uri uri = Uri.fromFile(new File(mFilePath));
 * file文件直接转成url
 * android 7.0 以后
 * Android不再允许在app中把file文件直接转成url
 *
 *
 * 安卓7 调用相机出现错误解决方式
 * StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
 *         StrictMode.setVmPolicy(builder.build());
 *         builder.detectFileUriExposure();
 *
 * 相关参考链接 https://blog.csdn.net/mt11111111/article/details/80280193
 */
public class CamaraActivity extends AppCompatActivity {

    public static final String TAG = "CamaraActivity";
    public static final int TAKE_PHOTO = 1;
    public static final int CROP_PHOTO = 2;

    private Button takePhoto, chooseFromAlbum;
    private ImageView picture;

    private Uri imageUri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();


        initElements();
    }

    private void initElements() {
        takePhoto = findViewById(R.id.take_photo);
        picture = findViewById(R.id.picture);
        takePhoto.setOnClickListener(v -> {
            //创建File对象，用于存储拍照后的图片
            File outputStream = new File(Environment.getExternalStorageDirectory(), "tempImage");
//                String mFilePath = Environment.getExternalStorageDirectory().getPath() + "/" + String.valueOf(System.currentTimeMillis()) + "tempImage.jpg";

            try {
                if (outputStream.exists()) {
                    outputStream.delete();
                }
                outputStream.createNewFile();
            }catch (IOException e) {
                Log.e(TAG, e.getMessage());
            }

            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            imageUri = Uri.fromFile(outputStream);

            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
            startActivityForResult(intent, TAKE_PHOTO);


        });

        chooseFromAlbum = findViewById(R.id.choose_from_album);
        chooseFromAlbum.setOnClickListener(v -> {
            //创建File对象用于存储选择的图片

            //从相册选择图片的一些操作

        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageUri, "image/*");
                    intent.putExtra("scale", true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, CROP_PHOTO);
                    /**
                     * 对图片进行剪切，但是还是没有返回一个图片资源
                     */
                }
                break;
            case CROP_PHOTO:
                if (requestCode == 2) {
                    // requestCode 运行时是2， 预期为-1
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        picture.setImageBitmap(bitmap);
                        /**
                         * 活动页面还是没有显示这个图片，
                         * 可能是页面的路径存在问题，（无任何报错）
                         */

                    } catch (FileNotFoundException e) {
                        Log.e(TAG, e.getMessage());
                    }
                }
            default:
                break;
        }


        super.onActivityResult(requestCode, resultCode, data);
    }
}
