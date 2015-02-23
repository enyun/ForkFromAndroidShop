package com.stone.shop.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import cn.bmob.v3.update.BmobUpdateAgent;

import com.ad.android.sdk.api.AdSdk;
import com.stone.shop.R;
import com.stone.shop.adapter.MineSoftAdapter;

/**
 * 软件相关
 * @date 2014-5-21 
 * @author Stone
 */
public class MineSoftActivity extends Activity implements OnItemClickListener{
	
	
	private String[] softItemNames = {"意见反馈", "检查更新", "使用协议", "关于我们"};
	private String[] softItemContents = {"", "", "", ""};
	private ListView lvMineSoft;
	
	private MineSoftAdapter softListAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_soft);
		
		insertAds();
		initView();
	}
	
	private void initView() {
		lvMineSoft = (ListView) findViewById(R.id.lv_mine_soft);
		softListAdapter = new MineSoftAdapter(this, softItemNames, softItemContents);
		lvMineSoft.setAdapter(softListAdapter);
		lvMineSoft.setOnItemClickListener(this);
	}
	
	private void insertAds() {
		AdSdk ad = AdSdk.getInstace(getApplicationContext()); // 获取广告实例
		// 设置banner广告账号
		ad.setInsertAdPid("1e852fc5e06b230c559310a3daf876cc");
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


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		switch (position) {
		case 0: 
			Intent toFeedBack = new Intent(MineSoftActivity.this, FeedBackActivity.class);
			startActivity(toFeedBack);
			break;
		case 1:
			BmobUpdateAgent.forceUpdate(this);
			break;
		case 2:
			break;
		case 3:
			Intent toAboutSoft = new Intent(MineSoftActivity.this, AboutActivity.class);
			startActivity(toAboutSoft);
			break;

		default:
			break;
		}
		
	}
	
	public void clickSoftBack(View v) {
		finish();
	}
	
	@SuppressWarnings("unused")
	private void toast(String toast) {
		Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
	}
}
