package ui

import java.awt.Toolkit
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JDialog





class JoptionPaneCopy {
    var frame = JFrame()
    var optionPane = JOptionPane()
    var icon = ImageIcon("yourFile.gif")


    //optionPane.setMessageType("")
    init {
        optionPane.setMessage("Add the below code In XYZ file:" + "\n" +"\n"+
                "String fcmRegId = FirebaseInstanceId.getInstance().getToken(); " +"\n"+
                "clevertapDefaultInstance.pushFcmRegistrationId(fcmRegId,true);" + "\n")
        optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        var jButton=getButton(optionPane, "Copy", icon);
        var jButton1=getButton(optionPane, "Copy1", icon);
        optionPane.options = arrayOf<Any>(jButton!!)
        val dialog = optionPane.createDialog(frame, "Icon/Text Button")
        dialog.isVisible = true


    }

    fun getButton(optionPane: JOptionPane, text: String?, icon: Icon?): JButton? {
        val button = JButton(text, icon)
        val actionListener = ActionListener { // optionPane.getMessage();
            // Return current text label, instead of argument to method
            optionPane.value = button.text
            setStringToClipboard(optionPane.message.toString())
            println(optionPane.message)
        }
        button.addActionListener(actionListener)
        return button
    }
    fun getStringFromClipboard(): String? {
        val transferable = Toolkit.getDefaultToolkit().systemClipboard.getContents(null)
        try {
            if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                return transferable.getTransferData(DataFlavor.stringFlavor) as String
            }
        } catch (e: UnsupportedFlavorException) {
            println("Clipboard content flavor is not supported " + e.message)
        } catch (e: IOException) {
            println("Clipboard content could not be retrieved " + e.message)
        }
        return null
    }

    // This method writes a string to the clipboard.
    fun setStringToClipboard(stringContent: String?) {
        val stringSelection = StringSelection(stringContent)
        Toolkit.getDefaultToolkit().systemClipboard.setContents(stringSelection, null)
    }


}