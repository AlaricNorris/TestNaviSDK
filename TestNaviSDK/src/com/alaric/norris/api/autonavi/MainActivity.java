package com.alaric.norris.api.autonavi ;

import java.util.ArrayList ;
import java.util.List ;
import android.app.Activity ;
import android.app.Fragment ;
import android.content.Intent ;
import android.os.Bundle ;
import android.view.KeyEvent ;
import android.view.LayoutInflater ;
import android.view.Menu ;
import android.view.MenuItem ;
import android.view.View ;
import android.view.ViewGroup ;
import com.amap.api.navi.AMapNavi ;
import com.amap.api.navi.AMapNaviView ;
import com.amap.api.navi.AMapNaviViewListener ;
import com.amap.api.navi.model.NaviLatLng ;

public class MainActivity extends Activity implements AMapNaviViewListener {

	private AMapNaviView mAmapAMapNaviView ;

	/**
	* 初始化
	* @param savedInstanceState
	*/
	private void init(Bundle savedInstanceState) {
		mAmapAMapNaviView = (AMapNaviView) findViewById(R.id.map) ;
		mAmapAMapNaviView.onCreate(savedInstanceState) ;
// 设置导航界面监听
		mAmapAMapNaviView.setAMapNaviViewListener(this) ;
		List<NaviLatLng> mStartPoints = new ArrayList<NaviLatLng>() ;
		List<NaviLatLng> mWayPoints = new ArrayList<NaviLatLng>() ;
		List<NaviLatLng> mEndPoints = new ArrayList<NaviLatLng>() ;
		mStartPoints.add(new NaviLatLng(32.028744 , 118.83482)) ;
		mEndPoints.add(new NaviLatLng(32.03311 , 118.812847)) ;
		AMapNavi.getInstance(this).calculateDriveRoute(mStartPoints , mEndPoints , null ,
				AMapNavi.DrivingDefault) ;
// 设置模拟速度
		AMapNavi.getInstance(this).setEmulatorNaviSpeed(100) ;
// 开启模拟导航
		AMapNavi.getInstance(this).startNavi(AMapNavi.EmulatorNaviMode) ;
	}

	/**
	* 导航界面左下角返回按钮回调
	*
	*/
	@ Override
	public void onNaviCancel() {
		finish() ;
	}

	/**
	* 导航界面右下角功能设置按钮回调
	*
	*/
	@ Override
	public void onNaviSetting() {
	}

	@ Override
	public void onNaviMapMode(int arg0) {
// TODO Auto-generated method stub
	}

	/**
	* 返回键盘监听
	*
	*/
	@ Override
	public boolean onKeyDown(int keyCode , KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK) {
			finish() ;
		}
		return super.onKeyDown(keyCode , event) ;
	}

//------------------------------生命周期方法---------------------------
	@ Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState) ;
		mAmapAMapNaviView.onSaveInstanceState(outState) ;
	}

	@ Override
	public void onResume() {
		super.onResume() ;
		mAmapAMapNaviView.onResume() ;
	}

	@ Override
	public void onPause() {
		super.onPause() ;
		mAmapAMapNaviView.onPause() ;
	}

	@ Override
	public void onDestroy() {
		super.onDestroy() ;
		mAmapAMapNaviView.onDestroy() ;
	}

	@ Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState) ;
		setContentView(R.layout.activity_main) ;
//		if(savedInstanceState == null) {
//			getFragmentManager().beginTransaction().add(R.id.container , new PlaceholderFragment())
//					.commit() ;
//		}
		init(savedInstanceState) ;
	}

	@ Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main , menu) ;
		return true ;
	}

	@ Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId() ;
		if(id == R.id.action_settings) {
			return true ;
		}
		return super.onOptionsItemSelected(item) ;
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@ Override
		public View onCreateView(LayoutInflater inflater , ViewGroup container ,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main , container , false) ;
			return rootView ;
		}
	}
}
