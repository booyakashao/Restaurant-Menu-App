package com.example.wen.foodmenuprinter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wen.database.model.TaxRate;

public class ChangeTaxRateActivity extends BaseActivityForCommon {

    EditText taxRateText;
    Button taxRateSubmitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_tax_rate);

        taxRateText = (EditText) findViewById(R.id.editTaxRate);
        taxRateSubmitButton = (Button) findViewById(R.id.taxRateSubmitButton);
        taxRateSubmitButton.setOnClickListener(setTaxRate());

        TaxRate currentTaxRate = taxRateDAO.getTaxRate();

        taxRateText.setText(CommonUtils.convertToPercentage(currentTaxRate.getTaxRate()));
    }

    private View.OnClickListener setTaxRate() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taxRateDAO.setOrCreateTaxRate(Double.parseDouble(taxRateText.getText().toString()));
                Intent returnToMainMenu = new Intent(ChangeTaxRateActivity.this, MainMenuActivity.class);
                ChangeTaxRateActivity.this.startActivity(returnToMainMenu);
                finish();
            }
        };
        
    }

}
