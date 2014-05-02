package Graphic;

import java.awt.Dimension;

import javax.swing.JPanel;

public abstract class TetraComponent extends JPanel {
    
    protected int w, h;
    
    protected String mPath; //ONLY FOR DEVELOPMENT
    
    public TetraComponent(){
    
        mPath= "../";// A commenter sous Ecllipse
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(w, h);
    }
    
    public int getH(){
        return h;
    }
    
    public int getW(){
        return w;
    }
    
    abstract public void update();
}
