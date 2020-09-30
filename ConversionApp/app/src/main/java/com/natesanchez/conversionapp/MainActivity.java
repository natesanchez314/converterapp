package com.natesanchez.conversionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

  double miles ;
  double kilos ;
  boolean toKM = true;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  public void radioOnClick(View v) {
    switch (v.getId()) {
      case R.id.milesToKilometers:
        toKM = true;
      case R.id.kilometersToMiles:
        toKM = false;
    }
  }

  public double convertClicked(boolean toKM, double initial) {
    try {
      if (toKM) {
        return initial * 1.609;
      } else {
        return initial * .621;
      }
    } catch (IllegalArgumentException e) {
      return -1;
    }
  }
}