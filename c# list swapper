using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ListSwapper
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void add1Button_Click(object sender, EventArgs e)
        {
            if (text1Input.Text != "") {
                list1.Items.Add(text1Input.Text);
            }
          
        }

        private void add2Button_Click(object sender, EventArgs e)
        {
            if (text2Input.Text != "")
            {
                list2.Items.Add(text2Input.Text);
            }
        }

        private void swapLeftButton_Click(object sender, EventArgs e)
        {
            if (list2.SelectedItem != null)
            {

            list1.Items.Add(list2.SelectedItem);
            list2.Items.Remove(list2.SelectedItem);

            }
        }

        private void swapRightButton_Click(object sender, EventArgs e)
        {
            if (list1.SelectedItem != null)
            {
            list2.Items.Add(list1.SelectedItem);
            list1.Items.Remove(list1.SelectedItem);
            }
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }
    }
}
