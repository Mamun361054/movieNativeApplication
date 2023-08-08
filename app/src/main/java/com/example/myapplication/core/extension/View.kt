package com.example.myapplication.core.extension

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.BaseTarget
import com.bumptech.glide.request.target.SizeReadyCallback
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition

fun View.cancelTransition(){
    transitionName = null
}

fun View.isVisible() = this.visibility == View.VISIBLE

fun View.visible(){
    this.visibility = View.VISIBLE
}
fun View.invisible(){
    this.visibility = View.GONE
}
fun ImageView.loadFromUrl(url: String) = Glide.with(this.context.applicationContext).load(url)
    .transition(DrawableTransitionOptions.withCrossFade()).into(this)

fun ImageView.loadUrlAndPostponeEnterTransition(url: String, activity: FragmentActivity) {
    val target: Target<Drawable> = ImageViewBaseTarget(this,activity)
}

private class ImageViewBaseTarget(var imageView: ImageView?, var activity: FragmentActivity?) :
    BaseTarget<Drawable>() {
    override fun onLoadFailed(errorDrawable: Drawable?) {
        super.onLoadFailed(errorDrawable)
        activity?.supportPostponeEnterTransition()
    }

    override fun getSize(cb: SizeReadyCallback) = cb.onSizeReady(SIZE_ORIGINAL, SIZE_ORIGINAL)

    override fun removeCallback(cb: SizeReadyCallback){
        imageView = null
        activity = null
    }

    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
       imageView?.setImageDrawable(resource)
       activity?.supportStartPostponedEnterTransition()
    }
}

