package net.lzzy.algorithm;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AndroidException;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import net.lzzy.algorithm.algorlib.BaseSort;
import net.lzzy.algorithm.algorlib.DirectSort;
import net.lzzy.algorithm.algorlib.SortFactory;

import java.util.Calendar;
import java.util.Objects;
import java.util.Random;

/**
 * @author Administrator
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Integer[] items;
    private EditText edtItems;
    private TextView tvResult;
    private Spinner spinner;

    private  void initspinner(){
        Spinner spinner=findViewById(R.id.spinner);
        spinner.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,SortFactory.getSortName()));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtItems = findViewById(R.id.activity_main_edt_items);
        findViewById(R.id.activity_main_btn_generate).setOnClickListener(this);
        findViewById(R.id.activity_main_btn_sort).setOnClickListener(this);
        tvResult = findViewById(R.id.activity_main_tv_result);
        spinner=findViewById(R.id.spinner);
        initspinner();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_main_btn_generate:
                generateItems();
                displayItems(edtItems);
                break;
            case R.id.activity_main_btn_sort:
                BaseSort<Integer>sort= SortFactory.getInstance(spinner.getSelectedItemPosition(),items);
                BaseSort<Integer> sortnotnull= Objects.requireNonNull(sort);
                sortnotnull.getDuration();

               String resul=sortnotnull.getResult();
//                String resu=sort.getResult();
                tvResult.setText(resul);
                //displayItems(tvResult);
//                new AlertDialog.Builder(MainActivity.this).setTitle("排序完成")
//                        .setMessage("对比次数:"+sort.getComparecount()+
//                                "\n交换次数:"+sort.getSwacount()+
//                                "\n移动次数:"+sort.getMovestep()+
//                                "\n运行时长:"+sort.getDuration())
//                        .show();
//                displayItems(tvResult);
                break;
            default:
                break;
        }
    }

    private void displayItems(TextView tv) {
        String display = "";
        for (Integer i : items) {
            display = display.concat(i + ",");
        }
        display = display.substring(0, display.length() - 1);
        tv.setText(display);
    }

    private void directSort() {
        //todo:直接选择排序的具体实现
        //todo:for循环
        //分为有序区和无序区，每一趟排序都在无序区依次对比，记录最小的元素
        //然后把无序区第一个元素和最小元素进行交换，无序区少一，有序区多一个，依此循环
        //元素数量为0
        for (int i=0;i<items.length-1;i++){
            int minpos=i;
            for (int j=i+1;j<items.length;j++){
                if (items[minpos].compareTo(items[j])>0){
                    minpos=j;
                }

            }
            swap(minpos,i);
        }
    }

    //todo:直接插入排序法。

    private void insertSort(){
        for (int i=1;i<items.length;i++){
            int j=i-1;
            if (items[j].compareTo(items[i])<0){
                continue;
            }
            Integer tmp=items[i];
            while (j>=0&&items[j].compareTo(tmp)>0){
                items[j+1]=items[j];
                j--;
            }
            items[j+1]=tmp;
        }
    }

    private void swap(int minpos, int i) {
        int tmp=items[minpos];
        items[minpos]=items[i];
        items[i]=tmp;
    }

    private void generateItems() {
        items = new Integer[10];
        Random generator = new Random();
        for (int i = 0; i < items.length; i++) {
            items[i] = generator.nextInt(99);
        }
    }

}
