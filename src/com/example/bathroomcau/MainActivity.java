package com.example.bathroomcau;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button calButton = null;
	private EditText boxNum = null;
	private TextView pos = null;
	private TextView posinfo = null;
	
	private class ShowListener implements OnClickListener{
		private Toast mToast;
		public void onClick(View v){
			if(boxNum.getText().length()!= 0)
			{
				int num = Integer.parseInt(boxNum.getText().toString());
				posinfo.setText(num+"对应的位置是：");
				switch (num%4)
				{
				case 1: pos.setText("左上"); break;
				case 2: pos.setText("右上"); break;
				case 3: pos.setText("左下"); break;
				case 0: pos.setText("右下"); break;
				}
					
			}
			else
			{
				//posinfo.setText("请输入数字");
				showToast("请输入数字");
				setShakeAnimation();
			}
			//完成显示后，关闭键盘
			InputMethodManager imm =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); 
			imm.hideSoftInputFromWindow(boxNum.getWindowToken(), 0); 
		}
		
		public void setShakeAnimation(){
	    	boxNum.setAnimation(shakeAnimation(5));
	    }

		public Animation shakeAnimation(int counts){
	    	Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
	    	translateAnimation.setInterpolator(new CycleInterpolator(counts));
	    	translateAnimation.setDuration(1000);
	    	return translateAnimation;
	    }
		
		private void showToast(String msg){
			if(mToast == null){
				mToast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
			}else{
				mToast.setText(msg);
			}
			mToast.show();
		}
		
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		calButton = (Button)super.findViewById(R.id.calculate);
		boxNum = (EditText)super.findViewById(R.id.boxnum);
		pos = (TextView)super.findViewById(R.id.result);
		posinfo = (TextView)super.findViewById(R.id.resultinfo);
		calButton.setOnClickListener(new ShowListener());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
