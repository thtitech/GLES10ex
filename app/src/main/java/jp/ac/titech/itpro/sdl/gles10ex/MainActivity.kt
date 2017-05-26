package jp.ac.titech.itpro.sdl.gles10ex

import android.opengl.GLSurfaceView
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.SeekBar


class MainActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {

    private var glView: GLSurfaceView? = null
    private var renderer: SimpleRenderer? = null
    private var rotationBarX: SeekBar? = null
    private var rotationBarY: SeekBar? = null
    private var rotationBarZ: SeekBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        setContentView(R.layout.activity_main)
        glView = findViewById(R.id.glview) as GLSurfaceView

        rotationBarX = findViewById(R.id.rotation_bar_x) as SeekBar
        rotationBarY = findViewById(R.id.rotation_bar_y) as SeekBar
        rotationBarZ = findViewById(R.id.rotation_bar_z) as SeekBar
        rotationBarX!!.setOnSeekBarChangeListener(this)
        rotationBarY!!.setOnSeekBarChangeListener(this)
        rotationBarZ!!.setOnSeekBarChangeListener(this)

        renderer = SimpleRenderer()
        renderer!!.addObj(Cube(0.5f, 0f, 0.2f, -3f))
        renderer!!.addObj(Pyramid(0.5f, 0f, 0f, 0f))
        glView!!.setRenderer(renderer)
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
        glView!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
        glView!!.onPause()
    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        if (seekBar === rotationBarX)
            renderer!!.setRotationX(progress.toFloat())
        else if (seekBar === rotationBarY)
            renderer!!.setRotationY(progress.toFloat())
        else if (seekBar === rotationBarZ)
            renderer!!.setRotationZ(progress.toFloat())
    }

    override fun onStartTrackingTouch(seekBar: SeekBar) {}

    override fun onStopTrackingTouch(seekBar: SeekBar) {}

    companion object {
        private val TAG = "MainActivity"
    }

}
