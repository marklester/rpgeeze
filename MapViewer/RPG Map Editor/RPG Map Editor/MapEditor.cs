using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace RPG_Map_Editor
{
    public partial class MapEditor : Form
    {
        private State mouseState;
        private int width;
        private int height;

        public MapEditor()
        {
            InitializeComponent();
        }

        private void submit_Click(object sender, EventArgs e)
        {
            width = xBox.Text;
            height = yBox.Text;
            createMap(width, height);
        }

        private void createMap(int x, int y)
        {
        }

        private void grassTerrain_Click(object sender, EventArgs e)
        {
            if(mouseState == State.grass)
                mouseState = State.none;
            mouseState = State.grass;
        }

        private void mountainTerrain_Click(object sender, EventArgs e)
        {
            if (mouseState == State.mountain)
                mouseState = State.none;
            mouseState = State.mountain;
        }

        private void waterTerrain_Click(object sender, EventArgs e)
        {
            if (mouseState == State.water)
                mouseState = State.none;
            mouseState = State.water;
        }

        private void boulder_Click(object sender, EventArgs e)
        {
            if (mouseState == State.boulder)
                mouseState = State.none;
            mouseState = State.boulder;
        }

        private void sword_Click(object sender, EventArgs e)
        {
            if (mouseState == State.boulder)
                mouseState = State.none;
            mouseState = State.boulder;
        }

        private void crossbow_Click(object sender, EventArgs e)
        {
            if (mouseState == State.bow)
                mouseState = State.none;
            mouseState = State.bow;
        }

        private void shield_Click(object sender, EventArgs e)
        {
            if (mouseState == State.shield)
                mouseState = State.none;
            mouseState = State.shield;
        }

        private void armor_Click(object sender, EventArgs e)
        {
            if (mouseState == State.armor)
                mouseState = State.none;
            mouseState = State.armor;
        }

        private void potion_Click(object sender, EventArgs e)
        {
            if (mouseState == State.potion)
                mouseState = State.none;
            mouseState = State.potion;
        }

        private void mana_Click(object sender, EventArgs e)
        {
            if (mouseState == State.mana)
                mouseState = State.none;
            mouseState = State.mana;
        }

    }

    enum State { 
        grass, 
        mountain, 
        water, 
        boulder, 
        sword, 
        shield, 
        bow, 
        boots, 
        armor, 
        potion, 
        mana, 
        none};
}
