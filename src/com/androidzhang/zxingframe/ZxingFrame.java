package com.androidzhang.zxingframe;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.WriterException;
import com.zxing.activity.CaptureActivity;

public class ZxingFrame extends Activity {

	private EditText resultTextView;
	private Button scanBarCodeButton;
	private ImageView iv_qr_image;
	
	protected int mScreenWidth ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zxing_frame);

		resultTextView = (EditText) this.findViewById(R.id.scan_result);

		scanBarCodeButton = (Button) this.findViewById(R.id.bt_bigin_scan);

		iv_qr_image = (ImageView)findViewById(R.id.iv_qr_image);
		
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		mScreenWidth = dm.widthPixels;
		
		scanBarCodeButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent openCameraIntent = new Intent(ZxingFrame.this,
						CaptureActivity.class);
				startActivityForResult(openCameraIntent, 0);
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			Bundle bundle = data.getExtras();
			String scanResult = bundle.getString("result");
			resultTextView.setText(scanResult);
		}
	}

	
	//qr code generator
	public void Create2QR(View v){
		String uri = resultTextView.getText().toString();
//		Bitmap bitmap = BitmapUtil.create2DCoderBitmap(uri, mScreenWidth/2, mScreenWidth/2);
		Bitmap bitmap;
		try {
			bitmap = BitmapUtil.createQRCode(uri, mScreenWidth);
			
			if(bitmap != null){
				iv_qr_image.setImageBitmap(bitmap);
			}
			
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
