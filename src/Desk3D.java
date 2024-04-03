import javax.swing.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import java.awt.event.*;

public class Desk3D extends JFrame implements GLEventListener, MouseListener, MouseMotionListener {

    private static final long serialVersionUID = 1L;
    private int prevMouseX, prevMouseY;
    private float rotateX = 0;
    private float rotateY = 0;
    private FPSAnimator animator;

    private String color;

    private float[] colorArr;

    public Desk3D(String color) {
        setTitle("Desk Renderer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        GLCanvas glcanvas = new GLCanvas(capabilities);
        glcanvas.addGLEventListener(this);
        glcanvas.addMouseListener(this);
        glcanvas.addMouseMotionListener(this);

        getContentPane().add(glcanvas);
        setSize(800, 600);
        setLocationRelativeTo(null);
        this.color = color;
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
        animator = new FPSAnimator(glcanvas, 60);
        animator.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Desk3D renderer = new Desk3D("DarkRed");
            renderer.setVisible(true);
        });
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f); // Background color
        gl.glEnable(GL2.GL_DEPTH_TEST);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        animator.stop();
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

        gl.glLoadIdentity();
        gl.glTranslatef(0, 0, -5); // Move the chair away from the camera
        gl.glRotatef(rotateX, 1, 0, 0);
        gl.glRotatef(rotateY, 0, 1, 0);

        // Render 3D chair
        renderChair(gl);

        gl.glFlush();
    }

    private void renderChair(GL2 gl) {
        // Render chair
        gl.glColor3fv(colorArr , 0);
        gl.glPushMatrix();
        gl.glTranslatef(0.0f, -0.5f, 0.0f); // Translate to sit on the ground
        // Seat
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex3f(-0.7f, 0.0f, 0.7f);
        gl.glVertex3f(0.7f, 0.0f, 0.7f);
        gl.glVertex3f(0.7f, 0.0f, -0.7f);
        gl.glVertex3f(-0.7f, 0.0f, -0.7f);
        gl.glEnd();

        // Backrest
//        gl.glBegin(GL2.GL_QUADS);
//        gl.glVertex3f(0.7f, 0.0f, 0.7f);
//        gl.glVertex3f(-0.7f, 0.0f, 0.7f);
//        gl.glVertex3f(-0.7f, 1.0f, 0.7f);
//        gl.glVertex3f(0.7f, 1.0f, 0.7f);
//        gl.glEnd();

        // Legs
        gl.glTranslatef(-0.6f, 0.0f, 0.6f);
        drawLeg(gl);
        gl.glTranslatef(1.2f, 0.0f, 0.0f);
        drawLeg(gl);
        gl.glTranslatef(0.0f, 0.0f, -1.2f);
        drawLeg(gl);
        gl.glTranslatef(-1.2f, 0.0f, 0.0f);
        drawLeg(gl);

        gl.glPopMatrix();
    }

    private void drawLeg(GL2 gl) {
        // Draw leg
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex3f(-0.1f, -1.0f, 0.1f); // Adjusted y-coordinate to start from the bottom of the seat
        gl.glVertex3f(0.1f, -1.0f, 0.1f);
        gl.glVertex3f(0.1f, 0.0f, 0.1f);
        gl.glVertex3f(-0.1f, 0.0f, 0.1f);

        gl.glVertex3f(0.1f, -1.0f, 0.1f);
        gl.glVertex3f(0.1f, -1.0f, -0.1f);
        gl.glVertex3f(0.1f, 0.0f, -0.1f);
        gl.glVertex3f(0.1f, 0.0f, 0.1f);

        gl.glVertex3f(0.1f, -1.0f, -0.1f);
        gl.glVertex3f(-0.1f, -1.0f, -0.1f);
        gl.glVertex3f(-0.1f, 0.0f, -0.1f);
        gl.glVertex3f(0.1f, 0.0f, -0.1f);

        gl.glVertex3f(-0.1f, -1.0f, -0.1f);
        gl.glVertex3f(-0.1f, -1.0f, 0.1f);
        gl.glVertex3f(-0.1f, 0.0f, 0.1f);
        gl.glVertex3f(-0.1f, 0.0f, -0.1f);
        gl.glEnd();
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

        rotateY += deltaX;
        rotateX += deltaY;

        prevMouseX = e.getX();
        prevMouseY = e.getY();

        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
}
