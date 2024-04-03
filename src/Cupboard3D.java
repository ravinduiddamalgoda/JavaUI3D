import javax.swing.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.event.*;

public class Cupboard3D extends JFrame implements GLEventListener, MouseListener, MouseMotionListener {

    private static final long serialVersionUID = 1L;
    private float angleX = 0;
    private float angleY = 0;
    private int prevMouseX, prevMouseY;
    private GLUT glut;
    private String color;

    private float[] colorArr;

    public Cupboard3D(String color) {
        setTitle("3D Cupboard Renderer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.color = color;

        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        GLCanvas glcanvas = new GLCanvas(capabilities);
        glcanvas.addGLEventListener(this);
        glcanvas.addMouseListener(this);
        glcanvas.addMouseMotionListener(this);

        getContentPane().add(glcanvas);
        setSize(800, 600);
        setLocationRelativeTo(null);
        float[] brown = {0.5f, 0.35f, 0.05f};
        float[] black = {0.0f, 0.0f, 0.0f};
        float[] darkRed = {0.5f, 0.0f, 0.0f};
        float[] darkBlue = {0.0f, 0.0f, 0.5f};
        float[] darkBrown = {0.3f, 0.2f, 0.1f};
        if(color.equalsIgnoreCase("BLACK")){
            this.colorArr = black;
        }else if (color.equalsIgnoreCase("DarkRed")){
            this.colorArr = darkRed;
        }else if (color.equalsIgnoreCase("Brown")){
            this.colorArr = brown;
        }else if (color.equalsIgnoreCase("DarkBlue")){
            this.colorArr = darkBlue;
        }else if (color.equalsIgnoreCase("DarkBrown")){
            this.colorArr = darkBrown;
        }else{
            this.colorArr = brown;
        }
        glut = new GLUT();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Define color
            float[] brown = {0.5f, 0.35f, 0.05f};
            float[] black = {0.0f, 0.0f, 0.0f};
            float[] darkRed = {0.5f, 0.0f, 0.0f};
            float[] darkBlue = {0.0f, 0.0f, 0.5f};
            float[] darkBrown = {0.3f, 0.2f, 0.1f};


            Cupboard3D renderer = new Cupboard3D("black");
            renderer.setVisible(true);
        });
    }

    @Override
    public void init(GLAutoDrawable drawable) {
//        GL2 gl = drawable.getGL().getGL2();
//        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f); // Background color
//        gl.glEnable(GL2.GL_DEPTH_TEST);
//        gl.glEnable(GL2.GL_LIGHTING);
//        gl.glEnable(GL2.GL_LIGHT0);
//
//        // Set light properties
//        float[] lightAmbient = {0.2f, 0.2f, 0.2f, 1.0f};
//        float[] lightDiffuse = {1.0f, 1.0f, 1.0f, 1.0f};
//        float[] lightSpecular = {1.0f, 1.0f, 1.0f, 1.0f};
//        float[] lightPosition = {0.0f, 1.0f, 0.0f, 1.0f};
//
//        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_AMBIENT, lightAmbient, 0);
//        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, lightDiffuse, 0);
//        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_SPECULAR, lightSpecular, 0);
//        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, lightPosition, 0);
//
//        // Set material properties
//        float[] ambient = {0.2f, 0.2f, 0.2f, 1.0f};  // Ambient color
//        float[] diffuse = {0.8f, 0.8f, 0.8f, 1.0f};  // Diffuse color
//        float[] specular = {0.5f, 0.5f, 0.5f, 1.0f}; // Specular color
//        float[] shininess = {50.0f};                 // Shininess
//
//        // Set material properties for front face
//        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_AMBIENT, ambient, 0);
//        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_DIFFUSE, diffuse, 0);
//        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SPECULAR, specular, 0);
//        gl.glMaterialfv(GL2.GL_FRONT, GL2.GL_SHININESS, shininess, 0);
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f); // Background color
        gl.glEnable(GL2.GL_DEPTH_TEST);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {}

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

        gl.glLoadIdentity();
        gl.glTranslatef(0, 0, -6); // Move the cupboard away from the camera
        gl.glRotatef(-150, 0, 1, 0); // Rotate slightly for better view
        gl.glRotatef(angleX, 0, 1, 0); // Rotate around Y-axis
        gl.glRotatef(angleY, 1, 0, 0); // Rotate around X-axis

        renderCupboard(gl);

        gl.glFlush();
    }

    private void renderCupboard(GL2 gl) {
        // Set color
        gl.glColor3fv(colorArr, 0); // Use the color array passed to the constructor

        // Render cupboard body
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, -1.0f, 0.0f); // Translate to sit on the ground
        gl.glScalef(1.5f, 2.0f, 0.8f); // Scale to adjust dimensions
        gl.glBegin(GL2.GL_QUADS);
        // Front face
        gl.glVertex3f(-0.5f, -0.5f, 0.5f);
        gl.glVertex3f(0.5f, -0.5f, 0.5f);
        gl.glVertex3f(0.5f, 0.5f, 0.5f);
        gl.glVertex3f(-0.5f, 0.5f, 0.5f);
        // Back face
        gl.glVertex3f(-0.5f, -0.5f, -0.5f);
        gl.glVertex3f(-0.5f, 0.5f, -0.5f);
        gl.glVertex3f(0.5f, 0.5f, -0.5f);
        gl.glVertex3f(0.5f, -0.5f, -0.5f);
        // Top face
        gl.glVertex3f(-0.5f, 0.5f, -0.5f);
        gl.glVertex3f(-0.5f, 0.5f, 0.5f);
        gl.glVertex3f(0.5f, 0.5f, 0.5f);
        gl.glVertex3f(0.5f, 0.5f, -0.5f);
        // Bottom face
        gl.glVertex3f(-0.5f, -0.5f, -0.5f);
        gl.glVertex3f(0.5f, -0.5f, -0.5f);
        gl.glVertex3f(0.5f, -0.5f, 0.5f);
        gl.glVertex3f(-0.5f, -0.5f, 0.5f);
        // Right face
        gl.glVertex3f(0.5f, -0.5f, -0.5f);
        gl.glVertex3f(0.5f, 0.5f, -0.5f);
        gl.glVertex3f(0.5f, 0.5f, 0.5f);
        gl.glVertex3f(0.5f, -0.5f, 0.5f);
        // Left face
        gl.glVertex3f(-0.5f, -0.5f, -0.5f);
        gl.glVertex3f(-0.5f, -0.5f, 0.5f);
        gl.glVertex3f(-0.5f, 0.5f, 0.5f);
        gl.glVertex3f(-0.5f, 0.5f, -0.5f);
        gl.glEnd();
        gl.glPopMatrix();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        double aspect = (double) width / (double) height;
        gl.glFrustum(-aspect, aspect, -1.0, 1.0, 2.0, 10.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        prevMouseX = e.getX();
        prevMouseY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
        int deltaX = e.getX() - prevMouseX;
        int deltaY = e.getY() - prevMouseY;

        angleY += deltaX * 0.5f;
        angleX += deltaY * 0.5f;

        prevMouseX = e.getX();
        prevMouseY = e.getY();

        GLCanvas canvas = (GLCanvas) e.getSource();
        canvas.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
}
