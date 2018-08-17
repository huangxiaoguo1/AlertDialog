package tsou.cn.alertdialog;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tsou.cn.lib_hxgdialog.dialog.AlertDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    /**
     * 默认样式
     */
    private Button mBtn1;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBtn1 = (Button) findViewById(R.id.btn1);
        mBtn1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn1:
                dialog = new AlertDialog.Builder(this)
                        .setContentView(R.layout.layout_dialog)
                        .setTitle(R.id.text1, "购物车")
                        .setTitle(R.id.text2, "消息")
                        .setTitle(R.id.btn, "发送")
                        .setShowView(R.id.text2,false)
                        .setOnClicklistener(R.id.text1, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                Toast.makeText(MainActivity.this,"点击了购物车",Toast.LENGTH_LONG).show();
                            }
                        })
                        .setOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {
                                Toast.makeText(MainActivity.this,"取消了",Toast.LENGTH_LONG).show();
                            }
                        })
                        .addDefaultAnimation()
                        .setCancelable(true)
                        .fullWidth()
                        .fromBottom(true)
                        .show();
                /**
                 * 如果有输入框或者特殊要求的对象可以使用dialog对象进行操作
                 */
                final EditText inputText = dialog.getView(R.id.et_text);

                dialog.setOnClickListener(R.id.btn, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Toast.makeText(MainActivity.this,"发送=>" + inputText.getText().toString().trim(),Toast.LENGTH_LONG).show();
                    }
                });
                break;
        }
    }
}

