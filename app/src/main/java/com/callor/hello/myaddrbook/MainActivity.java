package com.callor.hello.myaddrbook;

import android.database.sqlite.SQLiteDatabase;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.callor.hello.myaddrbook.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SQLiteDatabase db = new DBHelper(this).getReadableDatabase();

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // 이름 검사
                if(binding.textName.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this,"이름은 필수 항목입니다",Toast.LENGTH_LONG).show(); ;
                    binding.textName.requestFocus();
                    return ;
                }
                // 생일 검사
                if(binding.textBirth.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this,"생일은 필수 항목입니다",Toast.LENGTH_LONG).show(); ;
                    binding.textBirth.requestFocus();
                    return ;
                } else {

                    // 날짜입력이 형식에 맞는지 검사하는 코드
                    Calendar calendar = Calendar.getInstance();
                    try {

                        SimpleDateFormat df = new SimpleDateFormat("yyyymmdd");
                        Date ndate = df.parse(binding.textBirth.getText().toString());
                        calendar.setTime(ndate);

                        calendar.setTime(
                                (new SimpleDateFormat("yyyymmdd")).parse(
                                binding.textBirth.getText().toString()));

                        long date = calendar.getTimeInMillis();

                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this,"날짜 형식이 잘못되었습니다(eg yyyymmdd)",Toast.LENGTH_LONG).show();
                        return;
                    }
                }

                // 전화번호 검사
                if(binding.textTel.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this,"전화번호는 필수 항목입니다",Toast.LENGTH_LONG).show(); ;
                    binding.textTel.requestFocus();
                    return ;
                }
                // 입력된 값을 VO에 저장
                AddrTableVO vo = makeVO();

                // DBHelper.insertDB method를 호출하여 저장
                DBHelper dbHelper = new DBHelper(MainActivity.this);
                long insertRow = dbHelper.insertDB(vo);
                Toast.makeText(MainActivity.this,"ID"+insertRow,Toast.LENGTH_SHORT).show();

                // 리스트 갱신
//                modifyList();

            }
        });
        makeViewList(); // Listview 보이기

    }

    // 입력된 데이터를 VO에 넣는 메서드
    private AddrTableVO makeVO(){
        AddrTableVO vo = new AddrTableVO();
        vo.setSname(binding.textName.getText().toString());
        vo.setSbirth(binding.textBirth.getText().toString());
        vo.setStel(binding.textTel.getText().toString());
        return vo;
    }

    private void makeViewList(){
        // DBHelper와 RecyclerAdapter를 이용하여 ListView 완성
        // 데이터를 읽기 위해서 Helper Loadding
        DBHelper dbHelper = new DBHelper(this);

        // DB을 읽어서 addrDTO에 받기
        List<AddrTableVO> addrDTO = dbHelper.readFromDB();

//        binding.listView.setHasFixedSize(true); // 사이즈 고정
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.listView.setLayoutManager(linearLayoutManager);

        RcAdapter rcAdapter = new RcAdapter(this,addrDTO);
        binding.listView.setAdapter(rcAdapter);


    }






    // 리스트 갱신
    // 매우 ** 한 방식
    private void modifyList(){
        DBHelper dbHelper = new DBHelper(this);
        List<AddrTableVO> addrDTO = dbHelper.readFromDB();

        binding.listView.setHasFixedSize(true); // 사이즈 고정
        binding.listView.setLayoutManager(new LinearLayoutManager(this));
        binding.listView.setAdapter(new RcAdapter(this,addrDTO));


//        list.remove(position);
//        recycler.removeViewAt(position);
//        mAdapter.notifyItemRemoved(position);
//        mAdapter.notifyItemRangeChanged(position, list.size());

//        UPDATE THE DATA: The only things I had to do is
//        mAdapter.notifyDataSetChanged();

    }

}










