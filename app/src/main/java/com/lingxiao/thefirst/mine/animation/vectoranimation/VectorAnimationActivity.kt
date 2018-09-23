package com.lingxiao.thefirst.mine.animation.vectoranimation

import android.annotation.TargetApi
import android.graphics.drawable.Animatable
import android.graphics.drawable.Animatable2
import android.graphics.drawable.AnimatedVectorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.lingxiao.thefirst.R
import com.lingxiao.thefirst.base.BaseActivity
import kotlinx.android.synthetic.main.activity_vector_animation.*

class VectorAnimationActivity : BaseActivity(), View.OnClickListener {
    override fun getLayoutResource(): Int {
        return R.layout.activity_vector_animation
    }

    override fun initView(savedInstanceState: Bundle?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            iv_vector.setImageResource(R.drawable.animation_vector)
            var drawable = iv_vector.drawable
            (drawable as AnimatedVectorDrawable).registerAnimationCallback(object : Animatable2.AnimationCallback() {
                override fun onAnimationEnd(drawable: Drawable?) {
                }

                @TargetApi(Build.VERSION_CODES.M)
                override fun onAnimationStart(drawable: Drawable?) {
                    (drawable as AnimatedVectorDrawable).unregisterAnimationCallback(this)
                }
            })
            iv_vector.setOnClickListener(this)

//            iv_vector2.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.animation_vector2))
            iv_vector2.setImageResource(R.drawable.animation_vector2)
            iv_vector2.setOnClickListener(this)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.iv_vector, R.id.iv_vector2 -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    var drawable = (v as ImageView).drawable
                    if (drawable is Animatable) {
                        (drawable as Animatable).start()
                    }
                }
            }
        }
    }
}
