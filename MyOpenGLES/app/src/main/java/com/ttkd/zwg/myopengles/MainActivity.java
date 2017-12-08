package com.ttkd.zwg.myopengles;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.GestureDetector;

public class MainActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GLSurfaceView glSurfaceView=new GLSurfaceView(this);
        MyRenderer3D myRenderer=new MyRenderer3D();
        glSurfaceView.setRenderer(myRenderer);
        setContentView(glSurfaceView);
    }
}
