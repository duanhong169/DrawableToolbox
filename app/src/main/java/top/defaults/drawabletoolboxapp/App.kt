package top.defaults.drawabletoolboxapp

import android.app.Application
import android.content.Context
import me.weishu.reflection.Reflection

class App: Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        Reflection.unseal(base)
    }
}