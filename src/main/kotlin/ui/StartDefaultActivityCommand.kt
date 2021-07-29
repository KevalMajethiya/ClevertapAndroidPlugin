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
                                  var bg:String,var big_img:String,var ico:String,var dl1:String,var title_clr:String,var msg_clr:String,
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
                val bigImg =big_img.replace("/","@")
                val Ico= ico.replace("/","@")
                val Dl1 = dl1.replace("/","@")
                val titlteClr =title_clr.replace("#","@")
                val msgClr =msg_clr.replace("#","@")
                val smallIconClr =small_icon_clr.replace("#","@")
                val Bg =bg.replace("#","@")



                val shellCommand ="am start -a android.intent.action.VIEW -d \"app://clevertaplugin/$template_type/$title/$message/$msg_summary/$subtitle/$Bg/$bigImg/$Ico/$Dl1/$titlteClr/$msgClr/$smallIconClr\" $packageName"
                device.executeShellCommand(shellCommand, receiver, 15L, TimeUnit.SECONDS)

//                val shellCommand ="am start -a android.intent.action.VIEW -d \"app://clevertaplugin/templatetype=$template_type/title=$title/message=$message/msg_summary=$msg_summary/subtitle=$subtitle/bg=$ans_bg_value/big_img=$ans_img_val/ico=$ans_ico_val/dl1=$ans_dl1_val/titlte_clr=$ans_titlte_clr_value/msg_clr=$ans_msg_clr_value/small_icon_clr=$ans_small_icon_clr_value\" com.example.pttesting"
//                device.executeShellCommand(shellCommand, receiver, 15L, TimeUnit.SECONDS)
                //val shellCommand ="am start -a android.intent.action.VIEW -d \"app://clevertaplugin/$template_type\" com.example.pttesting"
                //device.executeShellCommand(shellCommand, receiver, 15L, TimeUnit.SECONDS)
//                val shellCommand ="am start -a android.intent.action.VIEW -d \"app://clevertaplugin/Basic/Welcome/Keval/Demo/Hii/#6A0DAD/" +
//                        "https://assets.myntassets.com/f_webp,w_980,c_limit,fl_progressive,dpr_2.0/assets/images/2020/6/9/56ac6102-defc-4491-8898-ffdbf37976ab1591721334133-desktop-banner.jpg/" +
//                        "https://www.searchpng.com/wp-content/uploads/2019/01/Myntra-logo-png-icon.png/" +
//                        "https://www.myntra.com//" +
//                        "#FFFFFF/#FFFFFFr/#FFFFFF  com.example.pttesting"
                //device.executeShellCommand(shellCommand, receiver, 15L, TimeUnit.SECONDS)
            }
            if(template_type=="Auto Carousel")
            {
                val Dl1= dl1.replace("/","@")
                val Img1= img1.replace("/","@")
                val Img2= img2.replace("/","@")
                val Img3= img3.replace("/","@")
                val Imgn= imgn.replace("/","@")
                val Bg= bg.replace("#","@")
                val Ico= ico.replace("/","@")
                val TitleClr= title_clr.replace("#","@")
                val MsgClr= msg_clr.replace("#","@")
                val SmallIconClr= small_icon_clr.replace("#","@")

                val shellCommand ="am start -a android.intent.action.VIEW -d \"app://clevertaplugin/$template_type/$title/$message/$msg_summary/$subtitle/$Dl1/$Img1/$Img2/$Img3/$Imgn/$Bg/$Ico/$TitleClr/$MsgClr/$SmallIconClr\" $packageName"
                device.executeShellCommand(shellCommand, receiver, 15L, TimeUnit.SECONDS)
            }
            if(template_type=="Manual Carousel")
            {
                val Dl1= dl1.replace("/","@")
                val Dl2= dl2.replace("/","@")
                val Dln= dln.replace("/","@")
                val Img1= img1.replace("/","@")
                val Img2= img2.replace("/","@")
                val Img3= img3.replace("/","@")
                val Imgn= imgn.replace("/","@")
                val Bg= bg.replace("#","@")
                val Ico= ico.replace("/","@")
                val TitleClr= title_clr.replace("#","@")
                val MsgClr= msg_clr.replace("#","@")
                val SmallIconClr= small_icon_clr.replace("#","@")

                val shellCommand ="am start -a android.intent.action.VIEW -d \"app://clevertaplugin/$template_type/$title/$message/$msg_summary/$subtitle/$Dl1/$Dl2/$Dln/$Img1/$Img2/$Img3/$Imgn/$Bg/$Ico/$TitleClr/$MsgClr/$SmallIconClr\" $packageName"
                device.executeShellCommand(shellCommand, receiver, 15L, TimeUnit.SECONDS)
            }
            if(template_type=="Rating")
            {
                val DefaultDl= default_dl.replace("/","@")
                val Dl1= dl1.replace("/","@")
                val Dl2= dl2.replace("/","@")
                val Dl3= dl3.replace("/","@")
                val Dl4= dl4.replace("/","@")
                val Dl5= dl5.replace("/","@")
                val Ico= ico.replace("/","@")
                val Bg= bg.replace("#","@")
                val TitleClr= title_clr.replace("#","@")
                val MsgClr= msg_clr.replace("#","@")
                val SmallIconClr= small_icon_clr.replace("#","@")

                val shellCommand ="am start -a android.intent.action.VIEW -d \"app://clevertaplugin/$template_type/$title/$message/$msg_summary/$subtitle/$DefaultDl/$Dl1/$Dl2/$Dl3/$Dl4/$Dl5/$Bg/$Ico/$TitleClr/$MsgClr/$SmallIconClr\" $packageName"
                device.executeShellCommand(shellCommand, receiver, 15L, TimeUnit.SECONDS)
            }
            if(template_type=="Product Catalog")
            {
                val Img1= img1.replace("/","@")
                val Img2= img2.replace("/","@")
                val Img3= img3.replace("/","@")
                val Dl1= dl1.replace("/","@")
                val Dl2= dl2.replace("/","@")
                val Dl3= dl3.replace("/","@")
                val Bg= bg.replace("#","@")
                val Product_display_action_clr= product_display_action_clr.replace("#","@")
                val TitleClr= title_clr.replace("#","@")
                val MsgClr= msg_clr.replace("#","@")
                val SmallIconClr= small_icon_clr.replace("#","@")

                val shellCommand ="am start -a android.intent.action.VIEW -d \"app://clevertaplugin/$template_type/$title/$message/$subtitle/$Img1/$Img2/$Img3/$bt1/$bt2/$bt3/$st1/$st2/$st3/$Dl1/$Dl2/$Dl3/$price1/$price2/$price3/$Bg/$product_display_action/$product_display_linear/$Product_display_action_clr/$TitleClr/$MsgClr/$SmallIconClr\" $packageName"
                device.executeShellCommand(shellCommand, receiver, 15L, TimeUnit.SECONDS)
            }
            if(template_type=="Five Icons")
            {
                val Img1= img1.replace("/","@")
                val Img2= img2.replace("/","@")
                val Img3= img3.replace("/","@")
                val Img4= img4.replace("/","@")
                val Img5= img5.replace("/","@")
                val Dl1= dl1.replace("/","@")
                val Dl2= dl2.replace("/","@")
                val Dl3= dl3.replace("/","@")
                val Dl4= dl4.replace("/","@")
                val Dl5= dl5.replace("/","@")
                val Bg= bg.replace("#","@")
                val SmallIconClr= small_icon_clr.replace("#","@")

                val shellCommand ="am start -a android.intent.action.VIEW -d \"app://clevertaplugin/$template_type/$Img1/$Img2/$Img3/$Img4/$Img5/$Dl1/$Dl2/$Dl3/$Dl4/$Dl5/$Bg/$SmallIconClr\" $packageName"
                device.executeShellCommand(shellCommand, receiver, 15L, TimeUnit.SECONDS)
            }
            if(template_type=="Timer")
            {
                val Dl1= dl1.replace("/","@")
                val BigImg= big_img.replace("/","@")
                val BigImgAlt= big_img_alt.replace("/","@")
                val Bg= bg.replace("#","@")
                val TitleClr= title_clr.replace("#","@")
                val MsgClr= msg_clr.replace("#","@")
                val SmallIconClr= small_icon_clr.replace("#","@")

                val shellCommand ="am start -a android.intent.action.VIEW -d \"app://clevertaplugin/$template_type/$title/$title_alt/$message/$msg_alt/$msg_summary/$subtitle/$Dl1/$BigImg/$BigImgAlt/$Bg/$timer_threshold/$timer_end/$TitleClr/$MsgClr/$SmallIconClr\" $packageName"
                device.executeShellCommand(shellCommand, receiver, 15L, TimeUnit.SECONDS)
            }
//            if(template_type=="Video")
//            {
//                val BigImg= big_img.replace("/","@")
//                val VideoUrl= video_url.replace("/","@")
//                val Bg= bg.replace("#","@")
//                val Dl1= dl1.replace("/","@")
//                val TitleClr= title_clr.replace("#","@")
//                val MsgClr= msg_clr.replace("#","@")
//                val SmallIconClr= small_icon_clr.replace("#","@")
//                val Ico= ico.replace("/","@")
//
//                //val shellCommand ="am start -a android.intent.action.VIEW -d \"app://clevertaplugin/$template_type/$title/$message/$msg_summary/$subtitle/$BigImg/$VideoUrl/$Bg/$Dl1/$TitleClr/$MsgClr/$SmallIconClr/$Ico\" com.example.pttesting/"
//                val shellCommand ="am start -a android.intent.action.VIEW -d \"app://clevertaplugin/$template_type/Welcome/Keval/Demo/Hii/https:@@assets.myntassets.com@f_webp,w_980,c_limit,fl_progressive,dpr_2.0@assets@images@2020@6@9@56ac6102-defc-4491-8898-ffdbf37976ab1591721334133-desktop-banner.jpg/abc/@6A0DAD/https:@@www.myntra.com@/@FFFFFF/@6A0DAD/@FFFFFF/https:@@www.searchpng.com@wp-content@uploads@2019@01@Myntra-logo-png-icon.png\" $packageName"
//                device.executeShellCommand(shellCommand, receiver, 15L, TimeUnit.SECONDS)
//            }
            if(template_type=="Zero Bezel")
            {
                val BigImg= big_img.replace("/","@")
                val Dl1= dl1.replace("/","@")
                val TitleClr= title_clr.replace("#","@")
                val MsgClr= msg_clr.replace("#","@")
                val SmallIconClr= small_icon_clr.replace("#","@")
                val Ico= ico.replace("/","@")

                val shellCommand ="am start -a android.intent.action.VIEW -d \"app://clevertaplugin/$template_type/$title/$message/$msg_summary/$subtitle/$BigImg/$small_view/$Dl1/$TitleClr/$MsgClr/$SmallIconClr/$Ico\" $packageName"
                device.executeShellCommand(shellCommand, receiver, 15L, TimeUnit.SECONDS)
            }
            if(template_type=="Input Box")
            {
                val BigImg= big_img.replace("/","@")
                val BigImgAlt= big_img_alt.replace("/","@")
                val Dl1= dl1.replace("/","@")
                val TitleClr= title_clr.replace("#","@")
                val MsgClr= msg_clr.replace("#","@")
                val SmallIconClr= small_icon_clr.replace("#","@")
                val Ico= ico.replace("/","@")

                val shellCommand ="am start -a android.intent.action.VIEW -d \"app://clevertaplugin/$template_type/$title/$message/$msg_summary/$subtitle/$BigImg/$BigImgAlt/$event_name/$event_property1/$event_property2/$event_property3/$input_label/$input_auto_open/$input_feedback/$Dl1/$TitleClr/$MsgClr/$SmallIconClr/$Ico\" $packageName"
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