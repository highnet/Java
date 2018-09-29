using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Diagnostics;

namespace rgbSlider
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
          
        }

        private void button1_Click(object sender, EventArgs e)
        {
            colorListView.Items.Clear();
        }

        private void trackBar3_Scroll(object sender, EventArgs e)
        {
            colorPanel.BackColor = Color.FromArgb(trackBar1.Value, trackBar2.Value, trackBar3.Value);
            blueValue.Text = trackBar3.Value.ToString();
        }

        private void trackBar2_Scroll(object sender, EventArgs e)
        {
            colorPanel.BackColor = Color.FromArgb(trackBar1.Value, trackBar2.Value, trackBar3.Value);
            greenValue.Text = trackBar2.Value.ToString();
        }

        private void trackBar1_Scroll(object sender, EventArgs e)
        {
            colorPanel.BackColor = Color.FromArgb(trackBar1.Value, trackBar2.Value, trackBar3.Value);
            redValue.Text = trackBar1.Value.ToString();
        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }

        private void groupBox1_Enter(object sender, EventArgs e)
        {

        }

        private void button2_Click(object sender, EventArgs e)
        {
      
            ListViewItem lvi = new ListViewItem();
            lvi.BackColor = Color.FromArgb(trackBar1.Value, trackBar2.Value, trackBar3.Value);
            lvi.Text  = "(" + redValue.Text + ", " + greenValue.Text + ", " + blueValue.Text + ")";
            colorListView.Items.Add(lvi);

            }
        

     


    }
}
