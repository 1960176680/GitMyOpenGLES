package com.ttkd.zwg.myopengles;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GLSurfaceView glSurfaceView=new GLSurfaceView(this);
        MyRenderer myRenderer=new MyRenderer();
        glSurfaceView.setRenderer(myRenderer);
        setContentView(glSurfaceView);
    }
}
