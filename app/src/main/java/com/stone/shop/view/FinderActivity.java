package com.stone.shop.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.stone.shop.R;

/**
 * 每日一抽页面
 * @date 2014-5-18
 * @author Stone
 */
public class FinderActivity extends Activity implements OnClickListener{
	
	@SuppressWarnings("unused")
	private static final String TAG = "FinderActivity";
	
	private Button btnCampusNews;
	private Button btnWsqToCao;
	private Button btnLuckyAward;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finder);
		
		initView();
	}
	
	private void initView() {
		btnCampusNews = (Button) findViewById(R.id.btn_campus_news);
		btnWsqToCao = (Button) findViewById(R.id.btn_wsq_tocao);
		btnLuckyAward = (Button) findViewById(R.id.btn_lucky_award);
		btnCampusNews.setOnClickListener(this);
		btnWsqToCao.setOnClickListener(this);
		btnLuckyAward.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		
		//校园速推
		case R.id.btn_campus_news:
			Intent toCampusNews = new Intent(FinderActivity.this, HomeActivity.class);
			startActivity(toCampusNews);
			break;
		
		//开心吐槽
		case R.id.btn_wsq_tocao:
			Intent toWsqToCao = new Intent(FinderActivity.this, WsqActivity.class);
			startActivity(toWsqToCao);
			break;
		
		//每日一抽
		case R.id.btn_lucky_award:
			Intent toLuckyAward = new Intent(FinderActivity.this, AwardActivity.class);
			startActivity(toLuckyAward);
			break;
		default:
			break;
		}
	}
	

}
