package com.example.bcsd5.calculator;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.bcsd5.MainActivity;
import com.example.bcsd5.R;
import com.example.bcsd5.calculator.Calculator;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.zip.Inflater;

public class CalcFragment extends Fragment implements View.OnClickListener {

    private EditText editText;
    private String expr, result;
    private Calculator calculator = null;

    private AlertDialog alertDialog;

    public CalcFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calc, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        Button buttonBackspace, buttonClear, buttonOpeningBrace, buttonClosingBrace, buttonEqual, buttonAdd, buttonSub, buttonMul, buttonDiv, buttonDot, button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;

        buttonBackspace = view.findViewById(R.id.button_backspace);
        buttonClear = view.findViewById(R.id.button_clear);
        buttonOpeningBrace = view.findViewById(R.id.button_opening_brace);
        buttonClosingBrace = view.findViewById(R.id.button_closing_brace);
        buttonEqual = view.findViewById(R.id.button_equal);
        buttonAdd = view.findViewById(R.id.button_add);
        buttonSub = view.findViewById(R.id.button_sub);
        buttonMul = view.findViewById(R.id.button_mul);
        buttonDiv = view.findViewById(R.id.button_div);
        buttonDot = view.findViewById(R.id.button_dot);
        button0 = view.findViewById(R.id.button_0);
        button1 = view.findViewById(R.id.button_1);
        button2 = view.findViewById(R.id.button_2);
        button3 = view.findViewById(R.id.button_3);
        button4 = view.findViewById(R.id.button_4);
        button5 = view.findViewById(R.id.button_5);
        button6 = view.findViewById(R.id.button_6);
        button7 = view.findViewById(R.id.button_7);
        button8 = view.findViewById(R.id.button_8);
        button9 = view.findViewById(R.id.button_9);

        buttonBackspace.setOnClickListener(this);
        buttonClear.setOnClickListener(this);
        buttonOpeningBrace.setOnClickListener(this);
        buttonClosingBrace.setOnClickListener(this);
        buttonEqual.setOnClickListener(this);
        buttonAdd.setOnClickListener(this);
        buttonSub.setOnClickListener(this);
        buttonMul.setOnClickListener(this);
        buttonDiv.setOnClickListener(this);
        buttonDot.setOnClickListener(this);
        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);

        editText = view.findViewById(R.id.edit_text_calc);
        editText.setTextIsSelectable(true);
        //editText.setShowSoftInputOnFocus(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_backspace:
                String expr = editText.getText().toString();
                if (editText.getText().toString().length() > 0)
                    editText.setText(expr.substring(0, expr.length() - 1));
                break;
            case R.id.button_clear:
                editText.setText("");
                break;
            case R.id.button_equal:
                calculator = new Calculator(editText.getText().toString());
                editText.setText(calculator.calculate());
                break;
            case R.id.button_opening_brace:
            case R.id.button_closing_brace:
            case R.id.button_add:
            case R.id.button_sub:
            case R.id.button_mul:
            case R.id.button_div:
            case R.id.button_dot:
            case R.id.button_0:
            case R.id.button_1:
            case R.id.button_2:
            case R.id.button_3:
            case R.id.button_4:
            case R.id.button_5:
            case R.id.button_6:
            case R.id.button_7:
            case R.id.button_8:
            case R.id.button_9:
                editText.setText(editText.getText().toString() +
                        ((Button) v).getText());
                break;
        }
    }

    public void save() {
        if (editText.getText().toString().length() <= 0) {
            Snackbar.make(getActivity().getWindow().getDecorView(),
                    "수식이 입력되지 않았습니다.",
                    BaseTransientBottomBar.LENGTH_SHORT).show();
            return;
        }
        if (calculator == null) calculator = new Calculator(editText.getText().toString());
        calculator.calculate();

        expr = calculator.getExpr();
        result = calculator.getResultStr();

        if (((MainActivity) getActivity()).getMenu() != null &&
                !((MainActivity) getActivity()).getMenu().findItem(R.id.item_load_calc).isVisible())
            ((MainActivity) getActivity()).getMenu().findItem(R.id.item_load_calc).setVisible(true);

        Snackbar.make(getActivity().getWindow().getDecorView(),
                "계산 결과가 저장되었습니다.",
                BaseTransientBottomBar.LENGTH_SHORT).show();
    }

    public void load() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_calc_load, null);
        ((TextView) view.findViewById(R.id.text_view_calc_load_result)).setText(result);
        ((TextView) view.findViewById(R.id.text_view_calc_load_expr)).setText(expr);
        if (alertDialog == null)
            alertDialog = new AlertDialog.Builder(getActivity())
                    .setView(view)
                    .setTitle("저장된 계산 결과")
                    .setPositiveButton("확인", null)
                    .create();

        alertDialog.show();
    }

    public boolean isSaved() {
        return expr != null && result != null;
    }
}
