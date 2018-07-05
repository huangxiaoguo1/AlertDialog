# AlertDialog

### 效果

![image](https://raw.githubusercontent.com/huangxiaoguo1/AlertDialog/master/app/src/main/assets/jdfw.gif)  

### 构造函数

    themeResId：样式
    Builder(Context context, int themResId)

### 引用方法

```
allprojects {

    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
    
}
  
dependencies {

    implementation 'com.github.huangxiaoguo1:AlertDialog:1.1.1'
    
}
  
```

### 使用介绍

####  基本使用

```
AlertDialog dialog = new AlertDialog.Builder(this)
                        .setContentView(R.layout.layout_dialog)
                        .setTitle(R.id.text1, "购物车")
                        .setTitle(R.id.text2, "消息")
                        .setTitle(R.id.btn, "发送")
                        .setOnClicklistener(R.id.text1, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
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
```

#### 方法简介（Builder方式链式调用）

###### 传入布局 

    id的形式传入：
    setContentView(int layoutResId)
    
    view的形式传入
    setContentView(View view) 

###### Dialog自带监听

    取消监听：
    setOnCancelListener(OnCancelListener onCancelListener)
    
    页面消失监听：
    setOnDismissListener(OnDismissListener onDismissListener)
    
    按键监听
    setOnKeyListener(OnKeyListener onKeyListener)
    
###### 设置文本
 
    传入控件id和内容
    setTitle(int viewId, CharSequence text)
    
###### 自己的布局监听

    传入控件的id和监听回调
    setOnClicklistener(int viewId, View.OnClickListener listener)
    
###### 设置宽度全屏

    fullWidth()

###### 位置在底部

    是否带有动画
    fromBottom(boolean isAnimation)
    
###### 设置位置

     setGravity(int gravity)
     
###### 设置宽高

    setWidthAndHeight(int width, int height)
    
###### 添加默认动画
        
    默认动画为缩放动画
    addDefaultAnimation()

###### 添加动画

    传入动画（例如：R.style.dialog_scale_anim）
    addAnimations(int styleAnimation)
    
###### 触摸屏幕外部是否可以取消

    true：可以消失，flase：不可消失
    setCancelable(boolean isCancelable)

#### 操作dialog进行设置

###### 使用简例

```
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
```

###### 设置内容

       控件id和内容
       setText(int viewId, CharSequence text)

###### 获取控件

   
    控件id
    getView(int viewId)

###### 设置点击

    控件id和监听回调
    setOnClickListener(int viewId, View.OnClickListener listener)



    

  
  
