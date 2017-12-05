package com.ttkd.zwg.myopengles;

import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Administrator on 2017-12-04.
 */

public class MyRenderer implements GLSurfaceView.Renderer {
    private FloatBuffer triangleDataBuffer;
    private IntBuffer triangleColorBuffer;
    private FloatBuffer rectDataBuffer;
    private IntBuffer rectColorBuffer;
    private FloatBuffer rectDataBuffer2;
    private FloatBuffer pentacleBuffer;
    private float[] trinagleData=new float[]{
            0.1f,0.6f,0.0f,
            -0.3f,0.0f,0.0f,
            0.3f,0.1f,0.0f
    };
    private int[] trinagleColor=new int[]{
            65535,0,0,0,
            0,65535,0,0,
            0,0,65535,0,
    };

    private float[] rectData=new float[]{
            0.4f,0.4f,0.0f,
            0.4f,-0.4f,0.0f,
            -0.4f,0.4f,0.0f,
            -0.4f,-0.4f,0.0f
    };

    private int[] rectColor=new int[]{
            0,65535,0,0,
            0,0,65535,0,
            65535,0,0,0,
            65535,65535,0,

    };

    private float[] rectData2=new float[]{
            -0.4f,0.4f,0.0f,
            0.4f,0.4f,0.0f,
            0.4f,-0.4f,0.0f,
            -0.4f,-0.4f,0.0f
    };

    private float[] pentacle=new float[]{
            0.4f,0.4f,0.0f,
            -0.2f,0.3f,0.0f,
            0.5f,0.0f,0f,
            -0.4f,0.0f,0f,
            -0.1f,-0.3f,0f
    };



    public MyRenderer(){
        triangleDataBuffer=floatBufferUtil(trinagleData);
        rectDataBuffer=floatBufferUtil(rectData);
        rectDataBuffer2=floatBufferUtil(rectData2);
        pentacleBuffer=floatBufferUtil(pentacle);
        triangleColorBuffer=intBufferUtil(trinagleColor);
        rectColorBuffer=intBufferUtil(rectColor);
    }

    /**
     * 该方法可以对OpenGL ES执行一些无需任何改变的初始化。
     */
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
       /**
        * 关闭抗抖动
        *用于禁用OpenGL ES某方面的特性。改代码用于关闭抗抖动，这样可以提高性能。
        */
        gl.glDisable(GL10.GL_DITHER);

        /**
         * 设置系统对透视进行修正
         * 用于对OpenGL ES某方面进行修正
         */
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);

        /**
         * 用于设置OpenGL ES清屏所有的颜色
         * 用于对OpenGL ES某方面进行修正
         */
        gl.glClearColor(0,0,0,0);

        /**
         * 用于设置OpenGL ES的阴影模式。
         * 此处为阴影平滑模式
         */
        gl.glShadeModel(GL10.GL_SMOOTH);

        /**
         * 用于启用OpenGL ES某方面的特性。
         * 此处用于启动OpenGL ES的深度测试。所谓深度测试就是让OpenGL ES负责跟踪
         * 每个物体在Z轴上的深度，这样就可以避免后面的物体遮挡前面的物体。
         */
        gl.glEnable(GL10.GL_DEPTH_TEST);
//        设置深度测试的类型
        gl.glDepthFunc(GL10.GL_LEQUAL);

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        /**
         * 设置3D视窗的大小及位置
         */
        gl.glViewport(0,0,width,height);

        /**
         * 将当前矩阵模式设置为投影矩阵
         * 设置视图的矩阵模型，通常可接受 GL10.GL_PROJECTION、GL10.GL_MODELVIEW
         * 当调用gl.glMatrixMode(GL10.GL_PROJECTION)指定将屏幕设为透视图
         * （要想看到逼真的3维物体，这是必要的）这意味越远的东西看起来越小
         * 当调用gl.glMatrixMode(GL10.GL_MODELVIEW)将当前矩阵模式设为模型视图矩阵，
         * 这意味着任何新的变换都会影响该矩阵中所有的物体
         */
        gl.glMatrixMode(GL10.GL_PROJECTION);

        /**
         * 初始化单位矩阵
         * 相当于reset()方法，用于初始化单位矩阵
         */
        gl.glLoadIdentity();

        /**
         * 计算透视窗的宽度、高度比
         */
        float ratio=(float) width/height;

        /**
         * 设置透视视窗的空间大小
         * 设置透视投影的空间大小，前两个参数用于设置X轴上的最大最小坐标值
         * 中间两个参数用于设置Y轴上的最大最小坐标值;
         * 后两个参数用于设置Z轴上所能绘制的场景深度的最小最大值
         */
        gl.glFrustumf(-ratio,ratio,-1,1,1,10);

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        /**
         * 清除屏幕缓存和深度缓存
         */
         gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT);
        /**
         * 启用顶点坐标数组
         */
         gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        /**
         * 启用顶点颜色数组
         */
         gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
         gl.glMatrixMode(GL10.GL_MODELVIEW);


//=============绘制第一个图形==================
         gl.glLoadIdentity();
         gl.glTranslatef(-0.32f,0.35f,-1.2f);
        /**
         * 设置顶点的位置数据。
         */
         gl.glVertexPointer(3,GL10.GL_FLOAT,0,triangleDataBuffer);
        /**
         * 设置顶点的颜色数据。
         */
         gl.glColorPointer(4,GL10.GL_FIXED,0,triangleColorBuffer);
        /**
         * 调用该方法绘制平面。
         */
         gl.glDrawArrays(GL10.GL_TRIANGLES,0,3);

//=============绘制第二个图形==================
        gl.glLoadIdentity();
        gl.glTranslatef(0.6f,0.8f,-1.5f);
        gl.glVertexPointer(3,GL10.GL_FLOAT,0,rectDataBuffer);
        gl.glColorPointer(4,GL10.GL_FIXED,0,rectColorBuffer);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP,0,4);

//=============绘制第三个图形==================
        gl.glLoadIdentity();
        gl.glTranslatef(-0.4f,-0.5f,-1.5f);
        gl.glVertexPointer(3,GL10.GL_FLOAT,0,rectDataBuffer2);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP,0,4);
//=============绘制第四个图形==================
        gl.glLoadIdentity();
        gl.glTranslatef(0.4f,-0.5f,-1.5f);
        gl.glColor4f(1.0f,0.2f,0.2f,0.0f);

        gl.glVertexPointer(3,GL10.GL_FLOAT,0,pentacleBuffer);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP,0,5);

        gl.glFinish();
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
    }

    private  FloatBuffer floatBufferUtil(float[] arr){
        FloatBuffer mBuffer;
        ByteBuffer qbb=ByteBuffer.allocateDirect(arr.length*4);
        qbb.order(ByteOrder.nativeOrder());
        mBuffer=qbb.asFloatBuffer();
        mBuffer.put(arr);
        mBuffer.position(0);
        return mBuffer;
    }

    private IntBuffer intBufferUtil(int[] arr){
        IntBuffer mBuffer;
        ByteBuffer qbb=ByteBuffer.allocateDirect(arr.length*4);
        qbb.order(ByteOrder.nativeOrder());
        mBuffer=qbb.asIntBuffer();
        mBuffer.put(arr);
        mBuffer.position(0);
        return mBuffer;
    }

}
