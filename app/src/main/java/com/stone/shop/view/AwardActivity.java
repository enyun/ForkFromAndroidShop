package com.stone.shop.view;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.ad.android.sdk.api.AdSdk;
import com.stone.shop.R;
import com.stone.shop.model.LuckyUser;

/**
 * 每日一抽页面
 * @date 2014-5-18
 * @author Stone
 */
public class AwardActivity extends Activity {
	
	private EditText etAwardNew;
	private EditText etAwardOld;
	
	private String awardNew;
	private String awardOld;
	
	private Handler mHandler = new Handler() {  
		  @Override  
		  public void handleMessage(Message msg) {  
		      switch (msg.what) {
				case 0:
					initView();
					break;
				default:
					break;
				}
		  }
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_award);
		
		insertAds();
		initData();
	}
	
	private void initView() {
		etAwardNew = (EditText) findViewById(R.id.et_award_new);
		etAwardOld = (EditText) findViewById(R.id.et_award_old);
		
		etAwardNew.setText(awardNew);
		etAwardOld.setText(awardOld);
	}
	
	private void insertAds() {
		AdSdk ad = AdSdk.getInstace(getApplicationContext()); // 获取广告实例
		// 设置banner广告账号
		ad.setInsertAdPid("f173465fb990d5df91e42a54149eb635");
		ad.setAccount("596017443@qq.com"); // 设置用户账号可不设置
		// 为这个banner广告指派一种布局方式
		RelativeLayout layout = new RelativeLayout(getApplicationContext());
		@SuppressWarnings("deprecation")
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.FILL_PARENT,
				RelativeLayout.LayoutParams.FILL_PARENT);
		addContentView(layout, params); // 将layout追加到当前activity的contentview
		if (ad.isInsertAdPrepared()) { // 在需要显示插屏广告时检测插屏广告是否已准备好
			ad.showInsertAd(getApplicationContext(), layout); // 显示banner广告
		}

	}
	
	private void initData() {
		BmobQuery<LuckyUser> query = new BmobQuery<LuckyUser>();
		query.order("-updateAt");
		query.findObjects(this, new FindListener<LuckyUser>() {
			
			@Override
			public void onSuccess(List<LuckyUser> list) {
				awardNew = list.get(0).getUsername()+"      " +list.get(0).getAward();
				awardOld = list.get(1).getUsername()+"      " +list.get(1).getAward();
				Message msg = new Message();
				msg.what = 0;
				mHandler.sendMessage(msg);
			}
			
			@Override
			public void onError(int arg0, String arg1) {
				toast("获取中奖名单失败");
			}
		});
	}
	
	private void toast(String toast) {
		Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
	}
	
	public void clickBack(View v) {
		finish();
	}
	

}
