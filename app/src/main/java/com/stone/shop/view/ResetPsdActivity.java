package com.stone.shop.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.listener.ResetPasswordListener;

import com.stone.shop.R;
import com.stone.util.Util;

public class ResetPsdActivity extends Activity implements OnClickListener{
	
	
	@SuppressWarnings("unused")
	private static final String TAG = "ResetPsdActivity";
	
	private EditText etVerifiedEmail;
	private Button btnSendEmail;
	
	private RelativeLayout rlResetPsdFinished;
	private Button btnBackToLogin;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reset_psd);
		
		initView();
	}
	
	private void initView()
	{
		etVerifiedEmail = (EditText) findViewById(R.id.et_email);
		btnSendEmail = (Button) findViewById(R.id.btn_send_email);
		
		rlResetPsdFinished = (RelativeLayout) findViewById(R.id.rl_reset_psd_finished);
		btnBackToLogin = (Button) findViewById(R.id.btn_back_to_login);
		
		btnSendEmail.setOnClickListener(this);
		btnBackToLogin.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_send_email:
			String email = etVerifiedEmail.getText().toString();
			if(email.equals(""))
			{
				toast("请输入邮箱地址");
			}
			else if(!Util.isEmailValid(email)) {
				toast("请输入有效的邮箱地址");
			}
			else {
				sendVerifiedEmail(email);
			}
			break;
			
		case R.id.btn_back_to_login:
			Intent toLoginActivity = new Intent(ResetPsdActivity.this, LoginActivity.class);
			startActivity(toLoginActivity);
			break;
		default:
			break;
		}
		
	}
	
	//找回密码：给邮箱发送验证消息
	private void sendVerifiedEmail(final String emailAddress)
	{
		BmobUser.resetPassword(this, emailAddress, new ResetPasswordListener() {
		    @Override
		    public void onSuccess() {
		    	etVerifiedEmail.setVisibility(View.GONE);
		    	btnSendEmail.setVisibility(View.GONE);
		    	rlResetPsdFinished.setVisibility(View.VISIBLE);
		        //toast("重置密码请求成功，请到" + emailAddress + "邮箱进行密码重置操作");
		    }
		    @Override
		    public void onFailure(int code, String e) {
		        toast("重置密码失败:" + e);
		    }
		});
	}
	
	private void toast(String toast)
	{
		Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
	}
}
