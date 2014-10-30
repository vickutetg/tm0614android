package com.vanlinh.l2electricbill;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText edtName, edtQuantity;
	TextView tvBill, tvFee, tvTotal;
	Button btnCalculate, btnPrint;

	ProgressDialog progressBar;
	private int progressBarStatus = 0;
	private Handler progressBarHandler = new Handler();

	private long fileSize = 0;

	// khoi tao mang Name, mang Cost
	String[] arrName = { "iphone", "galaxy", "lumia" };
	int[] arrCost = { 1000, 600, 300 };
	String iso = ""; // hien thi len textview
	int at = 0; // bien at de xac dinh vi tri trong mang
	int totalFee = 0; // tinh tong chi phi
	int vat = 0; // VAT
	int totalAll = 0; // chi phi tinh ca VAT

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		init();
	}

	public void init() {

		edtName = (EditText) findViewById(R.id.edtName);
		edtQuantity = (EditText) findViewById(R.id.edtQuantity);
		tvBill = (TextView) findViewById(R.id.tvBill);
		tvFee = (TextView) findViewById(R.id.tvFee);
		tvTotal = (TextView) findViewById(R.id.tvTotal);
		btnCalculate = (Button) findViewById(R.id.btnCalculate);
		btnPrint = (Button) findViewById(R.id.btnPrint);

		// xu ly su kien button Calculate
		btnCalculate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String name = edtName.getText().toString().trim();
				String quantity = edtQuantity.getText().toString();
				if (name.equals("") || quantity.equals("")) {
					Toast.makeText(MainActivity.this, "Chưa nhập đủ thông tin",
							Toast.LENGTH_SHORT).show();
				} else {
					// duyet trong mang Name
					// xem co ton tai name khong?
					// neu khong thi notify = Toast
					for (int i = 0; i < arrName.length; i++) {
						if (name.equals(arrName[i])) {
							at = i;
							break;
						} else {
							at = 1000;
						}
					}

					if (at == 1000) {
						Toast.makeText(MainActivity.this,
								"Không tồn tại sản phẩm này",
								Toast.LENGTH_SHORT).show();
					} else {

						int iQuantity = Integer.parseInt(quantity);
						if (iQuantity < 0) {
							Toast.makeText(MainActivity.this, "Nhập sai số",
									Toast.LENGTH_SHORT).show();
						} else {

							int moneyEach = iQuantity * arrCost[at];
							totalFee += moneyEach;
							vat = totalFee / 10;
							totalAll = totalFee * 110 / 100;

							iso = iso + String.format("%d", iQuantity) + "\t\t"
									+ "|" + String.format("%d", arrCost[at])
									+ "\t\t" + "|" + moneyEach + "\n";
							
							tvBill.setText(iso);
							tvFee.setText("Total Fee" + totalFee + "\nVAT"
									+ vat);
							tvTotal.setText("TOTAL (include VAT) \n" + totalAll);
						}

					}

				}
			}

		});

		// xu ly su kien button Print
		btnPrint.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				// AlertDialog.Builder builder = new
				// AlertDialog.Builder(MainActivity.this);
				// builder.setTitle("Printing");
				// // hien thi progess bar
				// builder.setPositiveButton("Done", new
				// DialogInterface.OnClickListener() {
				//
				// @Override
				// public void onClick(DialogInterface dialog, int which) {
				// dialog.cancel();
				// }
				// });
				// builder.create().show();

				progressBar = new ProgressDialog(v.getContext());
				progressBar.setCancelable(true);// cho phép ấn nút Back
				progressBar.setMessage("File downloading ...");
				progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				progressBar.setProgress(0);
				progressBar.setMax(100);
//				progressBar.show();

				// reset trạng thái của progressbar
				progressBarStatus = 0;

				// reset filesize
				fileSize = 0;

				new Thread(new Runnable() {
					public void run() {
						while (progressBarStatus < 100) {
							progressBarStatus = doSomeTasks();

							// dừng 1 giây
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

							// cập nhật progress bar
							progressBarHandler.post(new Runnable() {
								public void run() {
									progressBar.setProgress(progressBarStatus);
								}
							});
						}

//						// file downloaded,
//						if (progressBarStatus >= 100) {
//
//							// dừng lại 2 giây nhưng vẫn hiển thị là 100%
//							try {
//								Thread.sleep(20000);
//							} catch (InterruptedException e) {
//								e.printStackTrace();
//							}
//
//							// đóng progress bar
//							
//							progressBar.dismiss();
//						}
					}
				}).start();
				
				progressBar.setButton(DialogInterface.BUTTON_NEGATIVE,"Done", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
				//show full progress dialog
				progressBar.show();
			}
		});

	}

	// tải 1 file ví dụ đơn giản :
	public int doSomeTasks() {

		while (fileSize <= 10000) {

			fileSize += 2000;

			if (fileSize == 2000) {
				return 20;
			} else if (fileSize == 4000) {
				return 40;
			} else if (fileSize == 6000) {
				return 60;
			} else if (fileSize == 8000) {
				return 80;
			}
		}

		return 100;
	}

}
