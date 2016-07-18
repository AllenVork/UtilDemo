package zhangruofan.rxjavademo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import zhangruofan.rxjavademo.operators.Retry

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    fun initView() {
        retrywhen.setOnClickListener({
            Retry.retryWithCounts()
        })

        retryWithDelay.setOnClickListener({
            Retry.retryWithDelay()
        })

        retryWithDelayAndCount.setOnClickListener({
            Retry.limitedRetriesWithVariableDelay()
        })
    }
}
