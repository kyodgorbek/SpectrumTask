package yodgorbekkomilov.edgar.spectrumtask

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_launcher.*

class LauncherActivity :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        memberList.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@LauncherActivity,MemberActivity::class.java)
            startActivity(intent)
        })

        companyList.setOnClickListener(View.OnClickListener {
            val companyIntent = Intent(this@LauncherActivity,MainActivity::class.java)
            startActivity(companyIntent)
        })
    }
}
