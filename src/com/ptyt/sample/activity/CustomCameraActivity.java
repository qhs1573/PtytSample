package com.ptyt.sample.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.ErrorCallback;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.media.ExifInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ptyt.sample.annotation.ViewInject;
import com.ptyt.sample.widget.Preview;

public class CustomCameraActivity extends BaseActivity implements OnClickListener{
	@ViewInject(id=R.id.preview)
	private FrameLayout mFrameLayout;
	@ViewInject(id=R.id.sv_surfaceview)
	private SurfaceView mSurfaceView;
	@ViewInject(id=R.id.rel_layout)
	private RelativeLayout mRelativeLayout;
	@ViewInject(id=R.id.textViewReferan)
	private TextView textViewReferan;
	@ViewInject(id=R.id.button_exit)
	private Button buttonExit;
	@ViewInject(id=R.id.progress_layout)
	private LinearLayout progressLayout;
	@ViewInject(id=R.id.progressBar1)
	private ProgressBar mProgressBar;
	@ViewInject(id=R.id.islem_value_textView)
	private TextView valueTextView;
	@ViewInject(id=R.id.RelativeLayout1)
	private RelativeLayout RelativeLayout1;
	@ViewInject(id=R.id.imageView_foto)
	private ImageView imageView_foto;
	@ViewInject(id=R.id.imageView_photo)
	private ImageView imageView_photo;
	private Preview preview;
	private Camera camera;
	private String path = "/sdcard/KutCamera/cache/images/";
	Activity context;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setShowActionBar(false);
		setContentView(R.layout.custom_camera);
		context=this;
		preview = new Preview(CustomCameraActivity.this,mSurfaceView);
		mFrameLayout.addView(preview);
		preview.setKeepScreenOn(true);
		imageView_foto.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		try {
			takeFocusedPicture();
		} catch (Exception e) {

		}
		buttonExit.setClickable(false);
		imageView_foto.setClickable(false);
		progressLayout.setVisibility(View.VISIBLE);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		// TODO Auto-generated method stub
		if(camera==null){
		camera = Camera.open();
		camera.startPreview();
		camera.setErrorCallback(new ErrorCallback() {
			public void onError(int error, Camera mcamera) {

				camera.release();
				camera = Camera.open();
				Log.d("Camera died", "error camera");

			}
		});
		}
		if (camera != null) {
			if (Build.VERSION.SDK_INT >= 14)
				setCameraDisplayOrientation(context,CameraInfo.CAMERA_FACING_BACK, camera);
			preview.setCamera(camera);
		}
	}
	
	private void setCameraDisplayOrientation(Activity activity, int cameraId,
			android.hardware.Camera camera) {
		android.hardware.Camera.CameraInfo info = new android.hardware.Camera.CameraInfo();
		android.hardware.Camera.getCameraInfo(cameraId, info);
		int rotation = activity.getWindowManager().getDefaultDisplay()
				.getRotation();
		int degrees = 0;
		switch (rotation) {
		case Surface.ROTATION_0:
			degrees = 0;
			break;
		case Surface.ROTATION_90:
			degrees = 90;
			break;
		case Surface.ROTATION_180:
			degrees = 180;
			break;
		case Surface.ROTATION_270:
			degrees = 270;
			break;
		}

		int result;
		if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
			result = (info.orientation + degrees) % 360;
			result = (360 - result) % 360; // compensate the mirror
		} else { // back-facing
			result = (info.orientation - degrees + 360) % 360;
		}
		camera.setDisplayOrientation(result);
	}
	

	public void takeFocusedPicture() {
		camera.autoFocus(mAutoFocusCallback);
	}
	
	Camera.AutoFocusCallback mAutoFocusCallback = new Camera.AutoFocusCallback() {
		@Override
		public void onAutoFocus(boolean success, Camera camera) {
				
			try{
			camera.takePicture(mShutterCallback, null, jpegCallback);
			}catch(Exception e){
			}
		}
	};
	
	Camera.ShutterCallback mShutterCallback = new ShutterCallback() {
		@Override
		public void onShutter() {
			// TODO Auto-generated method stub
		}
	};
	
	PictureCallback rawCallback = new PictureCallback() {
		public void onPictureTaken(byte[] data, Camera camera) {
			// Log.d(TAG, "onPictureTaken - raw");
		}
	};

	PictureCallback jpegCallback = new PictureCallback() {
		@SuppressWarnings("deprecation")
		public void onPictureTaken(byte[] data, Camera camera) {

			FileOutputStream outStream = null;
			Calendar c = Calendar.getInstance();
			File videoDirectory = new File(path);

			if (!videoDirectory.exists()) {
				videoDirectory.mkdirs();
			}

			try {
				// Write to SD Card
				outStream = new FileOutputStream(path + c.getTime().getSeconds() + ".jpg");
				outStream.write(data);
				outStream.close();


			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {

			}
			
			
			Bitmap realImage;
			 final BitmapFactory.Options options = new BitmapFactory.Options();
			  options.inSampleSize = 5;
			   
			    options.inPurgeable=true;                   //Tell to gc that whether it needs free memory, the Bitmap can be cleared

			    options.inInputShareable=true;              //Which kind of reference will be used to recover the Bitmap data after being clear, when it will be used in the future

		
			realImage = BitmapFactory.decodeByteArray(data,0,data.length,options);
			ExifInterface exif = null;
			try {
				exif = new ExifInterface(path + c.getTime().getSeconds()
						+ ".jpg");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				Log.d("EXIF value",
						exif.getAttribute(ExifInterface.TAG_ORIENTATION));
				if (exif.getAttribute(ExifInterface.TAG_ORIENTATION)
						.equalsIgnoreCase("1")) {
					realImage = rotate(realImage, 90);
				} else if (exif.getAttribute(ExifInterface.TAG_ORIENTATION)
						.equalsIgnoreCase("8")) {
					realImage = rotate(realImage, 90);
				} else if (exif.getAttribute(ExifInterface.TAG_ORIENTATION)
						.equalsIgnoreCase("3")) {
					realImage = rotate(realImage, 90);
				} else if (exif.getAttribute(ExifInterface.TAG_ORIENTATION)
						.equalsIgnoreCase("0")) {
					realImage = rotate(realImage, 90);
				}
			} catch (Exception e) {

			}
			imageView_photo.setImageBitmap(realImage);
			imageView_foto.setClickable(true);
			camera.startPreview();
			progressLayout.setVisibility(View.GONE);
			buttonExit.setClickable(true);

		}
	};
	
	public static Bitmap rotate(Bitmap source, float angle) {
		Matrix matrix = new Matrix();
		matrix.postRotate(angle);
		return Bitmap.createBitmap(source, 0, 0, source.getWidth(),
				source.getHeight(), matrix, false);
	}
}
