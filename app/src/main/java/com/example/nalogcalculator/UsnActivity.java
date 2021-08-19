package com.example.nalogcalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class UsnActivity extends AppCompatActivity implements View.OnClickListener{

    TextView quarter1; //текст заголовок 1 квартал
    TextView quarter2; //текст заголовок 2 квартал
    TextView quarter3; //текст заголовок 3 квартал
    TextView quarter4; //текст заголовок 4 квартал

    EditText inputQuarter1; //ввод суммы 1 квартал
    EditText inputQuarter2; //ввод суммы 2 квартал
    EditText inputQuarter3; //ввод суммы 3 квартал
    EditText inputQuarter4; //ввод суммы 4 квартал

    Button button_sum; //кнопка считать
    Button button_clear; //кнопка считать
    Button button_back; //кнопка назад

    SharedPreferences sPref; //сохранялка текста

    final String SAVED_TEXT_INPUT_QUARTER1 = "saved_text_quarter1"; //константа для сохранения текста ввода суммы
    final String SAVED_TEXT_INPUT_QUARTER2 = "saved_text_quarter2"; //константа для сохранения текста ввода суммы
    final String SAVED_TEXT_INPUT_QUARTER3 = "saved_text_quarter3"; //константа для сохранения текста ввода суммы
    final String SAVED_TEXT_INPUT_QUARTER4 = "saved_text_quarter4"; //константа для сохранения текста ввода суммы

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usn_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        inputQuarter1 = (EditText) findViewById(R.id.input_sum_quarter1); //ввод суммы 1 квартал
        inputQuarter2 = (EditText) findViewById(R.id.input_sum_quarter2); //ввод суммы 2 квартал
        inputQuarter3 = (EditText) findViewById(R.id.input_sum_quarter3); //ввод суммы 3 квартал
        inputQuarter4 = (EditText) findViewById(R.id.input_sum_quarter4); //ввод суммы 4 квартал

        quarter1 = (TextView) findViewById(R.id.quarter1); //текст заголовок 1 квартал
        quarter2 = (TextView) findViewById(R.id.quarter2); //текст заголовок 2 квартал
        quarter3 = (TextView) findViewById(R.id.quarter3); //текст заголовок 3 квартал
        quarter4 = (TextView) findViewById(R.id.quarter4); //текст заголовок 4 квартал


        button_sum = (Button) findViewById(R.id.button_sum); //определяем кнопку считать
        button_clear = (Button) findViewById(R.id.button_clear); //определяем кнопку считать
        button_back = (Button) findViewById(R.id.button_back); //определяем кнопку считать

        loadText(); //загрузает сохранненный текст

        //обработчик
        button_sum.setOnClickListener(this); //посчитать
        button_clear.setOnClickListener(this); //очистить
        button_back.setOnClickListener(this); //назад
    }

    @Override
    public void onClick(View v) {

            switch (v.getId()) {
                case R.id.button_sum: //посчитать
                    // Говорим между какими Activity будет происходить связь
                    Intent intent = new Intent(this, UsnActivityOutput.class);

                    // проверка на отсутвствие значения
                    if (TextUtils.isEmpty(inputQuarter1.getText().toString()) ||
                            TextUtils.isEmpty(inputQuarter2.getText().toString()) ||
                            TextUtils.isEmpty(inputQuarter3.getText().toString()) ||
                            TextUtils.isEmpty(inputQuarter4.getText().toString())) {
                        Toast.makeText(UsnActivity.this, "Введите значение равное или больше 0", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // по ключу мы будем получать значение с Intent
                    intent.putExtra("inputQuarter1", inputQuarter1.getText().toString());
                    intent.putExtra("inputQuarter2", inputQuarter2.getText().toString());
                    intent.putExtra("inputQuarter3", inputQuarter3.getText().toString());
                    intent.putExtra("inputQuarter4", inputQuarter4.getText().toString());

                    // показываем новое Activity
                    startActivity(intent);
                    break;
                default:
                    break;

                case R.id.button_clear: //очистить
                    inputQuarter1.setText("0");// очищаем поле дохода
                    inputQuarter2.setText("0");// очищаем поле дохода
                    inputQuarter3.setText("0");// очищаем поле дохода
                    inputQuarter4.setText("0");// очищаем поле дохода
                    break;

                case R.id.button_back: //назад
                    finish();
                    break;
            }
    }
    private void saveText() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_TEXT_INPUT_QUARTER1, inputQuarter1.getText().toString());
        ed.putString(SAVED_TEXT_INPUT_QUARTER2, inputQuarter2.getText().toString());
        ed.putString(SAVED_TEXT_INPUT_QUARTER3, inputQuarter3.getText().toString());
        ed.putString(SAVED_TEXT_INPUT_QUARTER4, inputQuarter4.getText().toString());
        ed.commit();
    }

    private void loadText() {
        sPref = getPreferences(MODE_PRIVATE);
        String saved_text_quarter1 = sPref.getString(SAVED_TEXT_INPUT_QUARTER1, "");
        String saved_text_quarter2 = sPref.getString(SAVED_TEXT_INPUT_QUARTER2, "");
        String saved_text_quarter3 = sPref.getString(SAVED_TEXT_INPUT_QUARTER3, "");
        String saved_text_quarter4 = sPref.getString(SAVED_TEXT_INPUT_QUARTER4, "");
        inputQuarter1.setText(saved_text_quarter1);
        inputQuarter2.setText(saved_text_quarter2);
        inputQuarter3.setText(saved_text_quarter3);
        inputQuarter4.setText(saved_text_quarter4);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed(); // выход по кнопке назад
    }
}
