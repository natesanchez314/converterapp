package com.natesanchez.conversionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

  private EditText userInput;
  private TextView history;
  private TextView inputLabel;
  private TextView outPutLabel;
  private TextView outputValText;
  boolean toKM = true;
  StringBuilder sb = new StringBuilder();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    inputLabel = findViewById(R.id.initialVal);
    outPutLabel = findViewById(R.id.convertedVal);
    userInput = findViewById(R.id.editTextInput);
    outputValText = findViewById(R.id.output);
    history = findViewById(R.id.results);
    history.setMovementMethod(new ScrollingMovementMethod());
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    outState.putString("HISTORY", history.getText().toString());
    outState.putString("OUTPUTTEXT", outputValText.getText().toString());
    super.onSaveInstanceState(outState);
  }

  @Override
  protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    history.setText(savedInstanceState.getString("HISTORY"));
    outputValText.setText(savedInstanceState.getString("OUTPUTTEXT"));
  }

  public void radioOnClick(View v) {
    switch (v.getId()) {
      case R.id.milesToKilometers:
        toKM = true;
        inputLabel.setText(getString(R.string.milabel));
        outPutLabel.setText(getString(R.string.kmlabel));
        break;
      case R.id.kilometersToMiles:
        toKM = false;
        inputLabel.setText(getString(R.string.kmlabel));
        outPutLabel.setText(getString(R.string.milabel));
        break;
    }
  }

  public void convertOnClick(View v) {
    double inputVal = Double.parseDouble(userInput.getText().toString());
    double outputVal;
    if (toKM) {
      outputVal = inputVal * 1.60934;
    } else {
      outputVal = inputVal * 0.621371;
    }
    userInput.setText("");
    outputValText.setText(String.format(Locale.getDefault(),"%.1f", outputVal));
    if (toKM) {
      sb.insert(0, String.format(Locale.getDefault(), "%.1f Mi ==> %.1f Km\n", inputVal, outputVal));
    } else {
      sb.insert(0, String.format(Locale.getDefault(), "%.1f Km ==> %.1f Mi\n", inputVal, outputVal));
    }
    history.setText(sb.toString());
  }

  public void clearOnClick(View v) {
    userInput.setText("");
    outputValText.setText("");
    history.setText("");
  }
}