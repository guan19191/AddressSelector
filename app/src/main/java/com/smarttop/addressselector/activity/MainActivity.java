package com.smarttop.addressselector.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smarttop.addressselector.R;
import com.smarttop.library.bean.City;
import com.smarttop.library.bean.County;
import com.smarttop.library.bean.Province;
import com.smarttop.library.bean.Street;
import com.smarttop.library.utils.LogUtil;
import com.smarttop.library.widget.AddressSelector;
import com.smarttop.library.widget.BottomDialog;
import com.smarttop.library.widget.OnAddressSelectedListener;


/**
 * Created by smartTop on 2016/12/6.
 */

public class MainActivity extends Activity implements View.OnClickListener, OnAddressSelectedListener, AddressSelector.OnDialogCloseListener {
    private TextView tv_selector_area;
    private BottomDialog dialog;
    private String provinceCode;
    private String cityCode;
    private String countyCode;
    private String streetCode;
    private LinearLayout content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_selector_area = (TextView) findViewById(R.id.tv_selector_area);
        content = (LinearLayout) findViewById(R.id.content);
        tv_selector_area.setOnClickListener(this);
        AddressSelector selector = new AddressSelector(this);
//        //获取数据库管理
//        AddressDictManager addressDictManager = selector.getAddressDictManager();
//        AdressBean.ChangeRecordsBean changeRecordsBean = new AdressBean.ChangeRecordsBean();
//        changeRecordsBean.parentId = 0;
//        changeRecordsBean.name = "测试省";
//        changeRecordsBean.id = 35;
//        addressDictManager.inserddress(changeRecordsBean);//对数据库里增加一个数据
        selector.setOnAddressSelectedListener(new OnAddressSelectedListener() {
            @Override
            public void onAddressSelected(Province province, City city, County county, Street street) {

            }
        });
        View view = selector.getView();
        content.addView(view);
    }


    @Override
    public void onClick(View view) {
        if (dialog != null) {
            dialog.show();
        } else {
            dialog = new BottomDialog(this);
            dialog.setOnAddressSelectedListener(this);
            dialog.setDialogDismisListener(this);
            dialog.show();
        }
    }

    @Override
    public void onAddressSelected(Province province, City city, County county, Street street) {
        provinceCode = (province == null ? "" : province.code);
        cityCode = (city == null ? "" : city.code);
        countyCode = (county == null ? "" : county.code);
        streetCode = (street == null ? "" : street.code);
        LogUtil.d("数据", "省份id=" + provinceCode);
        LogUtil.d("数据", "城市id=" + cityCode);
        LogUtil.d("数据", "乡镇id=" + countyCode);
        LogUtil.d("数据", "街道id=" + streetCode);
        String s = (province == null ? "" : province.name) + (city == null ? "" : city.name) + (county == null ? "" : county.name) +
                (street == null ? "" : street.name);
        tv_selector_area.setText(s);
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void dialogclose() {
        if(dialog!=null){
            dialog.dismiss();
        }
    }
}
