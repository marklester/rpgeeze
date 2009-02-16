namespace RPG_Map_Editor
{
    partial class MapEditor
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.grassTerrain = new System.Windows.Forms.Button();
            this.mountainTerrain = new System.Windows.Forms.Button();
            this.waterTerrain = new System.Windows.Forms.Button();
            this.boulder = new System.Windows.Forms.Button();
            this.sword = new System.Windows.Forms.Button();
            this.shield = new System.Windows.Forms.Button();
            this.crossbow = new System.Windows.Forms.Button();
            this.boots = new System.Windows.Forms.Button();
            this.armor = new System.Windows.Forms.Button();
            this.potion = new System.Windows.Forms.Button();
            this.mana = new System.Windows.Forms.Button();
            this.submit = new System.Windows.Forms.Button();
            this.xBox = new System.Windows.Forms.TextBox();
            this.yBox = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.stateLabel = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.SuspendLayout();
            // 
            // pictureBox1
            // 
            this.pictureBox1.Location = new System.Drawing.Point(23, 12);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(551, 603);
            this.pictureBox1.TabIndex = 0;
            this.pictureBox1.TabStop = false;
            // 
            // grassTerrain
            // 
            this.grassTerrain.Image = global::RPG_Map_Editor.Properties.Resources.GrassTerrain;
            this.grassTerrain.Location = new System.Drawing.Point(661, 69);
            this.grassTerrain.Name = "grassTerrain";
            this.grassTerrain.Size = new System.Drawing.Size(30, 30);
            this.grassTerrain.TabIndex = 1;
            this.grassTerrain.UseVisualStyleBackColor = true;
            this.grassTerrain.Click += new System.EventHandler(this.grassTerrain_Click);
            // 
            // mountainTerrain
            // 
            this.mountainTerrain.Image = global::RPG_Map_Editor.Properties.Resources.MountainTerrain;
            this.mountainTerrain.Location = new System.Drawing.Point(661, 105);
            this.mountainTerrain.Name = "mountainTerrain";
            this.mountainTerrain.Size = new System.Drawing.Size(30, 30);
            this.mountainTerrain.TabIndex = 2;
            this.mountainTerrain.UseVisualStyleBackColor = true;
            this.mountainTerrain.Click += new System.EventHandler(this.mountainTerrain_Click);
            // 
            // waterTerrain
            // 
            this.waterTerrain.Image = global::RPG_Map_Editor.Properties.Resources.WaterTerrain;
            this.waterTerrain.Location = new System.Drawing.Point(661, 141);
            this.waterTerrain.Name = "waterTerrain";
            this.waterTerrain.Size = new System.Drawing.Size(30, 30);
            this.waterTerrain.TabIndex = 3;
            this.waterTerrain.UseVisualStyleBackColor = true;
            this.waterTerrain.Click += new System.EventHandler(this.waterTerrain_Click);
            // 
            // boulder
            // 
            this.boulder.Image = global::RPG_Map_Editor.Properties.Resources.Boulder;
            this.boulder.Location = new System.Drawing.Point(726, 69);
            this.boulder.Name = "boulder";
            this.boulder.Size = new System.Drawing.Size(30, 30);
            this.boulder.TabIndex = 4;
            this.boulder.UseVisualStyleBackColor = true;
            this.boulder.Click += new System.EventHandler(this.boulder_Click);
            // 
            // sword
            // 
            this.sword.Image = global::RPG_Map_Editor.Properties.Resources.sword;
            this.sword.Location = new System.Drawing.Point(660, 191);
            this.sword.Name = "sword";
            this.sword.Size = new System.Drawing.Size(30, 30);
            this.sword.TabIndex = 5;
            this.sword.UseVisualStyleBackColor = true;
            this.sword.Click += new System.EventHandler(this.sword_Click);
            // 
            // shield
            // 
            this.shield.Image = global::RPG_Map_Editor.Properties.Resources.shield;
            this.shield.Location = new System.Drawing.Point(696, 191);
            this.shield.Name = "shield";
            this.shield.Size = new System.Drawing.Size(30, 30);
            this.shield.TabIndex = 6;
            this.shield.UseVisualStyleBackColor = true;
            this.shield.Click += new System.EventHandler(this.shield_Click);
            // 
            // crossbow
            // 
            this.crossbow.Image = global::RPG_Map_Editor.Properties.Resources.crossbow;
            this.crossbow.Location = new System.Drawing.Point(660, 227);
            this.crossbow.Name = "crossbow";
            this.crossbow.Size = new System.Drawing.Size(30, 30);
            this.crossbow.TabIndex = 7;
            this.crossbow.UseVisualStyleBackColor = true;
            this.crossbow.Click += new System.EventHandler(this.crossbow_Click);
            // 
            // boots
            // 
            this.boots.Image = global::RPG_Map_Editor.Properties.Resources.boots;
            this.boots.Location = new System.Drawing.Point(696, 227);
            this.boots.Name = "boots";
            this.boots.Size = new System.Drawing.Size(30, 30);
            this.boots.TabIndex = 8;
            this.boots.UseVisualStyleBackColor = true;
            // 
            // armor
            // 
            this.armor.Image = global::RPG_Map_Editor.Properties.Resources.redarmor;
            this.armor.Location = new System.Drawing.Point(696, 263);
            this.armor.Name = "armor";
            this.armor.Size = new System.Drawing.Size(30, 30);
            this.armor.TabIndex = 9;
            this.armor.UseVisualStyleBackColor = true;
            this.armor.Click += new System.EventHandler(this.armor_Click);
            // 
            // potion
            // 
            this.potion.BackgroundImage = global::RPG_Map_Editor.Properties.Resources.potionlife;
            this.potion.Location = new System.Drawing.Point(732, 191);
            this.potion.Name = "potion";
            this.potion.Size = new System.Drawing.Size(30, 30);
            this.potion.TabIndex = 10;
            this.potion.UseVisualStyleBackColor = true;
            this.potion.Click += new System.EventHandler(this.potion_Click);
            // 
            // mana
            // 
            this.mana.BackgroundImage = global::RPG_Map_Editor.Properties.Resources.manna;
            this.mana.Location = new System.Drawing.Point(732, 227);
            this.mana.Name = "mana";
            this.mana.Size = new System.Drawing.Size(30, 30);
            this.mana.TabIndex = 11;
            this.mana.UseVisualStyleBackColor = true;
            this.mana.Click += new System.EventHandler(this.mana_Click);
            // 
            // submit
            // 
            this.submit.Location = new System.Drawing.Point(681, 428);
            this.submit.Name = "submit";
            this.submit.Size = new System.Drawing.Size(75, 23);
            this.submit.TabIndex = 12;
            this.submit.Text = "Submit";
            this.submit.UseVisualStyleBackColor = true;
            this.submit.Click += new System.EventHandler(this.submit_Click);
            // 
            // xBox
            // 
            this.xBox.Location = new System.Drawing.Point(681, 376);
            this.xBox.Name = "xBox";
            this.xBox.Size = new System.Drawing.Size(103, 20);
            this.xBox.TabIndex = 13;
            // 
            // yBox
            // 
            this.yBox.Location = new System.Drawing.Point(681, 402);
            this.yBox.Name = "yBox";
            this.yBox.Size = new System.Drawing.Size(103, 20);
            this.yBox.TabIndex = 14;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(658, 376);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(17, 13);
            this.label1.TabIndex = 15;
            this.label1.Text = "X:";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(658, 402);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(17, 13);
            this.label2.TabIndex = 16;
            this.label2.Text = "Y:";
            // 
            // stateLabel
            // 
            this.stateLabel.AutoSize = true;
            this.stateLabel.Location = new System.Drawing.Point(658, 35);
            this.stateLabel.Name = "stateLabel";
            this.stateLabel.Size = new System.Drawing.Size(0, 13);
            this.stateLabel.TabIndex = 17;
            // 
            // MapEditor
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(840, 641);
            this.Controls.Add(this.stateLabel);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.yBox);
            this.Controls.Add(this.xBox);
            this.Controls.Add(this.submit);
            this.Controls.Add(this.mana);
            this.Controls.Add(this.potion);
            this.Controls.Add(this.armor);
            this.Controls.Add(this.boots);
            this.Controls.Add(this.crossbow);
            this.Controls.Add(this.shield);
            this.Controls.Add(this.sword);
            this.Controls.Add(this.boulder);
            this.Controls.Add(this.waterTerrain);
            this.Controls.Add(this.mountainTerrain);
            this.Controls.Add(this.grassTerrain);
            this.Controls.Add(this.pictureBox1);
            this.Name = "MapEditor";
            this.Text = "Map Maker Pro";
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.PictureBox pictureBox1;
        private System.Windows.Forms.Button grassTerrain;
        private System.Windows.Forms.Button mountainTerrain;
        private System.Windows.Forms.Button waterTerrain;
        private System.Windows.Forms.Button boulder;
        private System.Windows.Forms.Button sword;
        private System.Windows.Forms.Button shield;
        private System.Windows.Forms.Button crossbow;
        private System.Windows.Forms.Button boots;
        private System.Windows.Forms.Button armor;
        private System.Windows.Forms.Button potion;
        private System.Windows.Forms.Button mana;
        private System.Windows.Forms.Button submit;
        private System.Windows.Forms.TextBox xBox;
        private System.Windows.Forms.TextBox yBox;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label stateLabel;
    }
}

