package com.stone.shop.view;

import com.stone.shop.R;
import com.stone.shop.adapter.GridAdapter;
import com.stone.ui.MyGridView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

/**
 * 商品主界面
 * @date  2014-4-24
 * @author Stone
 */
public class ShopActivity extends Activity implements  OnItemClickListener{
	
	@SuppressWarnings("unused")
	private static final String TAG = "ShopActivity" ;
	
	private MyGridView gvSchoolClass;	//学习小菜
	private MyGridView gvOutClass;		//疯狂小菜
	
	//V1.9.2-------------------------------------
	private MyGridView gvFoodClass;  	//吃饭小菜(小菜点餐)
	private MyGridView gvGiftClass;		//购物小菜(校园服务)
	//V1.9.2-------------------------------------
	
	//private ImageView imgLoc;
	//private ImageView imgSearch;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop);
		
		initView();
	}
	
	/**
	 * 初始化组件并适配数据
	 */
	public void initView() {
		gvFoodClass = (MyGridView) findViewById(R.id.gv_food_class);
		gvGiftClass = (MyGridView) findViewById(R.id.gv_gift_class);
		gvOutClass = (MyGridView) findViewById(R.id.gv_out_class);
		gvSchoolClass = (MyGridView) findViewById(R.id.gv_school_class);
		
		//V1.9.2-------------------------------------
		//小菜点餐
		gvFoodClass.setAdapter(new GridAdapter(this, 0));
		gvFoodClass.setOnItemClickListener(this);
		
		//校园服务
		gvGiftClass.setAdapter(new GridAdapter(this, 1));
		gvGiftClass.setOnItemClickListener(this);
		//V1.9.2-------------------------------------
		
		gvOutClass.setAdapter(new GridAdapter(this, 2));
		gvOutClass.setOnItemClickListener(this);
		
		gvSchoolClass.setAdapter(new GridAdapter(this, 3));
		gvSchoolClass.setOnItemClickListener(this);
		
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Log.i("GridView点击了： ", "position"+position);
		//toast("点击了： " + position);
		switch (parent.getId()) {
		
		//点击 学习小菜 中的子项（1代表第一个GridView, (position+1)代表GridView中第几个元素）
		case R.id.gv_school_class:
			//教学类的做特别处理
			if(position==0) {
				Intent toBXTActivity = new Intent(ShopActivity.this, BXTActivity.class);
				startActivity(toBXTActivity);
			} else {
				toShopAllActivity( GridAdapter.mSchoolTexts[position], "1"+(position+1) );
			}
			
			break;
			
		//-------------------------------------------------------------------------------
			
		//小菜点餐
		case R.id.gv_food_class:
			toShopAllActivity( GridAdapter.mFoodTexts[position], "2"+(position+1) );
			break;
			
		//校园服务
		case R.id.gv_gift_class:
			Intent intent = new Intent();
			if(position==0) {
				intent = new Intent(ShopActivity.this, BXTActivity.class);
				startActivity(intent);
			} else if(position==5) {
				intent = new Intent(ShopActivity.this, SecondTradeActivity.class);
				intent.putExtra("title", "二手交易");
				startActivity(intent);
			} else {
				toShopAllActivity( GridAdapter.mGiftTexts[position], "3"+(position+1) );
			}
			break;
		//-------------------------------------------------------------------------------	
			
			
		//点击 疯狂小菜 中的子项
		case R.id.gv_out_class:
			toShopAllActivity( GridAdapter.mOutTexts[position], "4"+(position+1) );
			break;
		default:
			break;
		}
		
	}
	
	@SuppressWarnings("unused")
	private void toast(String toast) { 
		Toast.makeText(this, toast,  Toast.LENGTH_SHORT).show();
	};
	
	/**
	 * @param title		父分类标题
	 * @param type		
	 */
	private void toShopAllActivity(String title, String type) {
		Intent toShopAll = new Intent(ShopActivity.this, ShopAllActivity.class);
		toShopAll.putExtra("title", title);
		//当前点击的项的父分类
		toShopAll.putExtra("type", type);
		startActivity(toShopAll);
	}
	
}
