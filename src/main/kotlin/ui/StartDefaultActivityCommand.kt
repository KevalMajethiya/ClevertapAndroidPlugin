package ui


import com.android.ddmlib.IDevice
import com.android.ddmlib.MultiLineReceiver
import com.android.tools.idea.run.activity.ActivityLocator.ActivityLocatorException
import com.android.tools.idea.run.activity.DefaultActivityLocator
import com.google.common.base.Joiner
import com.google.common.base.Strings
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.ThrowableComputable
import org.jetbrains.android.facet.AndroidFacet
import java.util.*
import java.util.concurrent.TimeUnit

class StartDefaultActivityCommand(private val withDebugger: Boolean,var template_type:String,var title:String,var message:String,var msg_summary:String,var subtitle:String,
                                  var bg:String,var big_img:String,var ico:String,var dl1:String,var titlte_clr:String,var msg_clr:String,
                                  var small_icon_clr:String,var dl2:String,var dln:String,var img1:String,var img2:String,var img3:String,var imgn:String,var default_dl:String,
                                  var dl3:String, var dl4:String, var dl5:String,
                                  var bt1:String, var bt2:String, var bt3:String, var st1:String,var  st2:String,var st3:String,
                                  var price1:String,var price2:String,var price3:String,
                                  var img4:String,var img5:String,
                                  var title_alt:String,var msg_alt:String,var big_img_alt:String,
                                  var timer_threshold:String,var timer_end:String,
                                  var video_url:String,var small_view:String,var event_name:String,
                                  var event_property1:String,var event_property2:String,var event_property3:String,
                                  var input_label:String,var input_auto_open:String,var input_feedback:String,
                                  var product_display_action:String,var product_display_linear:String,var product_display_action_clr:String) : Command {
    override fun run(project: Project, device: IDevice, facet: AndroidFacet, packageName: String): Boolean {
        try {
            val activityName = getDefaultActivityName(facet, device)
            val receiver = StartActivityReceiver()
           //final// val shellCommand = startActivity(packageName, activityName, withDebugger)
            //var shellCommand = "./adb shell am start -n com.example.minal/.MainActivity"
            //var shellCommand = "am start -n com.example.minal/.MainActivity"
            //var shellCommand = "am start -n com.example.minal/.MainActivity"
            //val shellCommand = "am start-service com.example.pt_demo1.Push_Templates/.pushtemplate_service; "
            //val shellCommand ="am start -a android.intent.action.VIEW -d \"app://clevertaplugin/$template_type/$title/$message/$msg_summary/$subtitle/$bg/$big_img/$ico/$dl1/$titlte_clr/$msg_clr/$small_icon_clr\" com.example.pt_demo1"
            //val shellCommand ="am start -a android.intent.action.VIEW -d \"app://clevertaplugin/$template_type/$title/$message/$msg_summary/$subtitle/$big_img/$ico/$dl1/$titlte_clr/$msg_clr/$small_icon_clr/$bg/$dl2/$dln/$img1/$img2/$img3/$imgn\" com.example.pt_demo1"
            //val shellCommand ="am start -a android.intent.action.VIEW -d \"app://clevertaplugin/$template_type/$title/$message/$msg_summary/$subtitle/" +
//                    "$big_img/$ico/$dl1/$titlte_clr/$msg_clr/$small_icon_clr/" +
//                    "$bg/$dl2/$dln/$img1/$img2/$img3/$imgn/" +
//                    "$default_dl/$dl3/$dl4/$dl5/$bt1/$bt2/$bt3/$st1/$st2/$st3/$price1/$price2/$price3/$img4/" +
//                    "$img5/$title_alt/$msg_alt/$big_img_alt/$timer_threshold/$timer_end/$video_url/$small_view/" +
//                    "$event_name/$event_property1/$event_property2/$event_property3/" +
//                    "$input_label/$input_auto_open/$input_feedback/" +
//                    "$product_display_action/$product_display_linear/$product_display_action_clr\" " +
//                    "com.example.pt_demo1"




            if(template_type=="Basic")
            {
                val shellCommand ="am start -a android.intent.action.VIEW -d \"app://clevertaplugin/$template_type/$title/$message/$msg_summary/$subtitle/$bg/$big_img/$ico/$dl1/$titlte_clr/$msg_clr/$small_icon_clr\" com.example.pttesting"
                device.executeShellCommand(shellCommand, receiver, 15L, TimeUnit.SECONDS)
                //val shellCommand ="am start -a android.intent.action.VIEW -d \"app://clevertaplugin/$template_type\" com.example.pttesting"
                //device.executeShellCommand(shellCommand, receiver, 15L, TimeUnit.SECONDS)
            }
            if(template_type=="Auto Carousel")
            {
                val shellCommand ="am start -a android.intent.action.VIEW -d \"app://clevertaplugin/$template_type/$title/$message/$msg_summary/$subtitle/$dl1/$img1/$img2/$img3/$imgn/$bg/$ico/$titlte_clr/$msg_clr/$small_icon_clr\" com.example.pt_demo1"
                device.executeShellCommand(shellCommand, receiver, 15L, TimeUnit.SECONDS)
            }
            if(template_type=="Manual Carousel")
            {
                val shellCommand ="am start -a android.intent.action.VIEW -d \"app://clevertaplugin/$template_type/$title/$message/$msg_summary/$subtitle/$dl1/$dl2/$dln/$img1/$img2/$img3/$imgn/$bg/$ico/$titlte_clr/$msg_clr/$small_icon_clr\" com.example.pt_demo1"
                device.executeShellCommand(shellCommand, receiver, 15L, TimeUnit.SECONDS)
            }
            if(template_type=="Rating")
            {
                val shellCommand ="am start -a android.intent.action.VIEW -d \"app://clevertaplugin/$template_type/$title/$message/$msg_summary/$subtitle/$default_dl/$dl1/$dl2/$dl3/$dl4/$dl5/$ico/$titlte_clr/$msg_clr/$small_icon_clr\" com.example.pt_demo1"
                device.executeShellCommand(shellCommand, receiver, 15L, TimeUnit.SECONDS)
            }
            if(template_type=="Product Catalog")
            {
                val shellCommand ="am start -a android.intent.action.VIEW -d \"app://clevertaplugin/$template_type/$title/$message/$subtitle/$img1/$img2/$img3/$bt1/$bt2/$bt3/$st1/$st2/$st3/$dl1/$dl2/$dl3/$price1/$price2/$price3/$bg/$product_display_action/$product_display_linear/$product_display_action_clr/$titlte_clr/$msg_clr/$small_icon_clr\" com.example.pt_demo1"
                device.executeShellCommand(shellCommand, receiver, 15L, TimeUnit.SECONDS)
            }
            if(template_type=="Five Icons")
            {
                val shellCommand ="am start -a android.intent.action.VIEW -d \"app://clevertaplugin/$template_type/$img1/$img2/$img3/$img4/$img5/$dl1/$dl2/$dl3/$dl4/$dl5/$bg/$small_icon_clr\" com.example.pt_demo1"
                device.executeShellCommand(shellCommand, receiver, 15L, TimeUnit.SECONDS)
            }
            if(template_type=="Timer")
            {
                val shellCommand ="am start -a android.intent.action.VIEW -d \"app://clevertaplugin/$template_type/$title/$title_alt/$message/$msg_alt/$msg_summary/$subtitle/$dl1/$big_img/$big_img_alt/$bg/$timer_threshold/$timer_end/$titlte_clr/$msg_clr/$small_icon_clr\" com.example.pt_demo1"
                device.executeShellCommand(shellCommand, receiver, 15L, TimeUnit.SECONDS)
            }
            if(template_type=="Video")
            {
                val shellCommand ="am start -a android.intent.action.VIEW -d \"app://clevertaplugin/$template_type/$title/$message/$msg_summary/$subtitle/$big_img/$video_url/$bg/$dl1/$titlte_clr/$msg_clr/$small_icon_clr/$ico\" com.example.pt_demo1"
                device.executeShellCommand(shellCommand, receiver, 15L, TimeUnit.SECONDS)
            }
            if(template_type=="Zero Bezel")
            {
                val shellCommand ="am start -a android.intent.action.VIEW -d \"app://clevertaplugin/$template_type/$title/$message/$msg_summary/$subtitle/$big_img/$small_view/$dl1/$titlte_clr/$msg_clr/$small_icon_clr/$ico\" com.example.pt_demo1"
                device.executeShellCommand(shellCommand, receiver, 15L, TimeUnit.SECONDS)
            }
            if(template_type=="Input Box")
            {
                val shellCommand ="am start -a android.intent.action.VIEW -d \"app://clevertaplugin/$template_type/$title/$message/$msg_summary/$subtitle/$big_img/$big_img_alt/$event_name/$event_property1/$event_property2/$event_property3/$input_label/$input_auto_open/$input_feedback/$dl1/$titlte_clr/$msg_clr/$small_icon_clr/$ico\" com.example.pt_demo1"
                device.executeShellCommand(shellCommand, receiver, 15L, TimeUnit.SECONDS)
            }




//            if (withDebugger) {
//                Debugger(project, device, packageName).attach()
//            }
//            if (receiver.isSuccess) {
//                info(String.format("<b>%s</b> started on %s", packageName, device.name))
//                return true
//            } else {
//                error(String.format("<b>%s</b> could not be started on %s. \n\n<b>ADB Output:</b> \n%s", packageName, device.name, receiver.message))
//            }
        } catch (e: Exception) {
            error("Start fail... " + e.message)
        }
        return false
    }

    @Throws(ActivityLocatorException::class)
    private fun getDefaultActivityName(facet: AndroidFacet, device: IDevice): String {
        return ApplicationManager.getApplication()
            .runReadAction(ThrowableComputable<String, ActivityLocatorException?> { DefaultActivityLocator(facet).getQualifiedActivityName(device) })
    }

    class StartActivityReceiver : MultiLineReceiver() {
        var message = "Nothing Received"
        var currentLines: MutableList<String?> = ArrayList()
        override fun processNewLines(strings: Array<String>) {
            for (s in strings) {
                if (!Strings.isNullOrEmpty(s)) {
                    currentLines.add(s)
                }
            }
            computeMessage()
        }

        private fun computeMessage() {
            message = Joiner.on("\n").join(currentLines)
        }

        override fun isCancelled(): Boolean {
            return false
        }

        val isSuccess: Boolean
            get() = currentLines.size in 1..2
    }

}