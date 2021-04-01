package ui

import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*


internal class rdemo : JFrame(), ActionListener {
    var rb1: JRadioButton
    var rb2: JRadioButton
    var b: JButton
    var tf: JTextField
    override fun actionPerformed(e: ActionEvent) {
        //RadioListener myListener = new RadioListener();
        if (rb1.isSelected) {
            tf.isVisible = true
            //tf.setEnabled(true);
//JOptionPane.showMessageDialog(this,"You are Male.");
        }
        if (rb2.isSelected) {
//kj    rdJOptionPane.showMessageDialog(this,"You are Female.");
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            rdemo()
        }
    }

    init {
        tf = JTextField()
        //tf.setEnabled(false);
        tf.isVisible = false
        tf.setBounds(100, 25, 100, 30)
        rb1 = JRadioButton("Yes")
        rb1.setBounds(100, 50, 100, 30)
        rb2 = JRadioButton("NO")
        rb2.setBounds(100, 100, 100, 30)
        rb1.addActionListener(this)
        val bg = ButtonGroup()
        bg.add(rb1)
        bg.add(rb2)
        b = JButton("click")
        b.setBounds(100, 150, 80, 30)
        //b.addActionListener(this);
        add(rb1)
        add(tf)
        add(rb2)
        add(b)
        setSize(300, 300)
        layout = null
        isVisible = true
    }
}