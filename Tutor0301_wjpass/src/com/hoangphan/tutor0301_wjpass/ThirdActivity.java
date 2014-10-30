/**
 * http://iziroi.9xkun.com
 * 
 * LICENSE
 * 
 * This source file is belong to iziroi.9xkun.com. Please come to this site and
 * get more source code. Can send email to me at: phantichhoang@gmail.com
 * 
 * @copyright Copyright (c) 2013-2014 iziroi
 * @author hoangpt
 * @version $Id$
 * @since
 */

package com.hoangphan.tutor0301_wjpass;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class ThirdActivity extends Activity {
  
    Intent i3,i1;
    String spinner_template;
    private Spinner spnClothes, spnWeapons, spnMedicines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        spinner_template = getString(R.string.spinner_message_template);

        i3 = getIntent(); //get from scr2
        i1 = new Intent(this, FirstActivity.class);
        i1.putExtras(i3);
    }

    @Override
    protected void onResume() {
      super.onResume();
      spnClothes = (Spinner) findViewById(R.id.spnClothes);
      
      //weapon
      spnWeapons = (Spinner) findViewById(R.id.spnWeapon);
      List<String> weapons = _getWeapons();
      ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, weapons);
      adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      spnWeapons.setAdapter(adapter);
      
      //medicine
      spnMedicines = (Spinner) findViewById(R.id.spnMedicines);
      MedicineAdapter medAdap = new MedicineAdapter(this);
      medAdap.setDropDownViewResource(android.R.layout.simple_list_item_multiple_choice);
      spnMedicines.setAdapter(medAdap);

      //listener for spinner
      spnClothes.setOnItemSelectedListener(new SpinnerListenner());
      spnWeapons.setOnItemSelectedListener(new SpinnerListenner());
      spnMedicines.setOnItemSelectedListener(new SpinnerListenner());
    }

    class SpinnerListenner implements OnItemSelectedListener {

      @Override
      public void onItemSelected(AdapterView<?> parent, View view,
          int position, long id) {
        String selection = parent.getItemAtPosition(position).toString();
        String message = String.format(spinner_template, selection);
        _toaster(message);
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {
      }
    }

    private List<String> _getWeapons() {
      String[] cities = new String[] {"Ghost stick", "Sword", "Bow"};
      List<String> list = Arrays.asList(cities);
      Collections.shuffle(list);
      return list;
    }

    public void gotoFirst(View v){
      //get ui
      String cloth = spnClothes.getSelectedItem().toString();
      String weapon = spnWeapons.getSelectedItem().toString();
      String medicine = spnMedicines.getSelectedItem().toString();
      
      //we use start, not finish i3
      i1.putExtra("cloth", cloth);
      i1.putExtra("weapon", weapon);
      i1.putExtra("medicine", medicine);
      
      startActivity(i1); //we will resume this first screen
    }
    
    private void _toaster(String msg) {
      Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
