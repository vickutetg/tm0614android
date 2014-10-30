package com.vanlinh.l2todo_list;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private EditText edtEnter, edtHour, edtMinute;
	private Button btnAdd;
	private ListView lvList;
	
	private ArrayList<String> list = null;
	private ArrayAdapter<String> adapter = null;
	
	private String event; //dinh dang chuoi in ra

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        
        initListView();
    }
    
    //khoi tao cac widget
    public void init(){
    	
    	edtEnter = (EditText) findViewById(R.id.edtEnter);
    	edtHour = (EditText) findViewById(R.id.edtHour);
    	edtMinute = (EditText) findViewById(R.id.edtMinute);
    	
    	btnAdd = (Button) findViewById(R.id.btnAdd);
    	
    	lvList = (ListView) findViewById(R.id.lvList);
    	
    	//xu ly su kien Add
    	btnAdd.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String enter = edtEnter.getText().toString();
				String hour = edtHour.getText().toString();
				String minute = edtMinute.getText().toString() ;
				
				
				
				//validate
				if(enter.trim().equals("") || hour.trim().equals("") || minute.trim().equals("")){
//					Toast.makeText(MainActivity.this, "Bạn chưa nhập công việc", Toast.LENGTH_LONG).show();
					alertDialog("Bạn chưa nhập đủ dữ liệu");
				}else{
					
					int iHour = Integer.parseInt(hour);
					int iMinute = Integer.parseInt(minute);
					
					if( iHour >= 24 || iHour < 0){
//						Toast.makeText(MainActivity.this, "Nhập sai giờ", Toast.LENGTH_LONG).show();
						alertDialog("Nhập sai giờ");
					}else if( iMinute >= 60 || iMinute < 0){
//						Toast.makeText(MainActivity.this, "Nhập sai phút", Toast.LENGTH_LONG).show();
						alertDialog("Nhập sai phút");
					}else{
						
						event = hour + ":" + minute + " - " + enter;
						list.add(event);
						//sap xep list view
						Collections.sort(list);// có thể dùng Collections.sort(list, new CompareTo(List<String> list))
						// cap nhat giao dien
						adapter.notifyDataSetChanged();
						
						//xoa trang du lieu
						edtEnter.setText("");
						edtHour.setText("");
						edtMinute.setText("");
						edtEnter.requestFocus();
				}
					
					
				}
				
				
			}
		});
    	
    	//xu ly su kien xoa du lieu tren List View
    	//nhan giu item trong 2s de xoa
    	lvList.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				list.remove(arg2);
				adapter.notifyDataSetChanged();
				return false;
			}
		});
    }
    
    //set thong tin vao list view
    public void initListView(){
    	
    	list = new ArrayList<String>();
    	
    	adapter = new ArrayAdapter<String>(MainActivity.this,
    			android.R.layout.simple_list_item_1, list);
    	
    	lvList.setAdapter(adapter);
    	
    }
    
    //Dialog
    public void alertDialog(String message){
    	AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		builder.setTitle("Warning");
		builder.setMessage(message);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		builder.create().show();
    }
    


}
