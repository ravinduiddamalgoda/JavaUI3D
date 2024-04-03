import javax.swing.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLJPanel;
import java.awt.event.*;

public class SquareFloor extends JFrame implements GLEventListener, MouseListener, MouseMotionListener {

    private static final long serialVersionUID = 1L;
    private boolean isDraggingChair = false;
    private boolean isDraggingTable = false;
    private boolean isDraggingCupboard = false;
    private int prevX, prevY;
    private int chairX = 0, chairY = 0; // Position of the chair
    private int tableX = 0, tableY = 0; // Position of the table
    private int cupboardX = 0, cupboardY = 0; // Position of the cupboard

    private float cupboardScale = 5;
    private float tableScale = 1;
    private float chairScale = 1;
    private int windowWidth = 1200; // Width of the window
    private int windowHeight = 900; // Height of the window

    private String tableColor;
    private String chairColor;
    private String cupColor;

    private int floorMargin = 75;

    private String floorColor;

    public SquareFloor(float tableScale , float chairScale , float cupboardScale , String tableColor , String chairColor , String cupColor , String floorColor , int floorMargin) {
        this.tableScale = tableScale;
        this.chairScale = chairScale;
        this.cupboardScale = cupboardScale;
        this.tableColor = tableColor;
        this.chairColor = chairColor;
        this.cupColor = cupColor;
        this.floorColor = floorColor;
        this.floorMargin = floorMargin;

        setTitle("Furniture Renderer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        GLJPanel gljpanel = new GLJPanel(capabilities);
        gljpanel.addGLEventListener(this);
        gljpanel.addMouseListener(this);
        gljpanel.addMouseMotionListener(this);

        getContentPane().add(gljpanel);
        setSize(windowWidth, windowHeight);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SquareFloor renderer = new SquareFloor(1 , 1 , 5 , "red" , "brown" , "blue" , "tan" , 75);
            renderer.setVisible(true);
        });
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f); // Background color
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        // Cleanup
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);


        drawFloorSq(gl , floorColor);
        // Draw furniture
        drawChair(gl, chairX, chairY , chairScale , chairColor);
        drawTable(gl, tableX, tableY , tableScale , tableColor);
        drawCupboard(gl, cupboardX, cupboardY, cupboardScale , cupColor);

        gl.glFlush();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0, windowWidth, windowHeight, 0, -1.0, 1.0); // Adjust as necessary
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    private  void drawFloorSq(GL2 gl , String color){
        // Draw floor
        if (color.equalsIgnoreCase("BEIGE")) {
            gl.glColor3f(0.96f, 0.87f, 0.70f); // Light beige
        } else if (color.equalsIgnoreCase("TAN")) {
            gl.glColor3f(0.82f, 0.71f, 0.55f); // Light tan
        } else if (color.equalsIgnoreCase("LIGHT_GRAY")) {
            gl.glColor3f(0.83f, 0.83f, 0.83f); // Light gray
        } else if (color.equalsIgnoreCase("IVORY")) {
            gl.glColor3f(1.0f, 1.0f, 0.94f); // Ivory
        } else if (color.equalsIgnoreCase("SAND")) {
            gl.glColor3f(0.96f, 0.87f, 0.70f); // Sand color
        } else {
            gl.glColor3f(0.82f, 0.71f, 0.55f); // Default to light tan
        }

        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(floorMargin, floorMargin); // Left-bottom
        gl.glVertex2f(windowWidth-floorMargin, floorMargin); // Right-bottom
        gl.glVertex2f(windowWidth -floorMargin, windowHeight -floorMargin); // Right-top75
        gl.glVertex2f(floorMargin, windowHeight -floorMargin); // Left-top
        gl.glEnd();
    }
    private void drawChair(GL2 gl, int x, int y, float scale , String color) {

        if(color.equalsIgnoreCase("BLACK")){
            gl.glColor3f(0.0f, 0.0f, 0.0f);
        }else if (color.equalsIgnoreCase("BROWN")){
            gl.glColor3f(0.5f, 0.35f, 0.05f);
        }else if (color.equalsIgnoreCase("Blue")){
            gl.glColor3f(0.2f, 0.0f, 1.0f);
        }else if (color.equalsIgnoreCase("RED")){
            gl.glColor3f(1.0f, 0.0f, 0.0f);
        }else if (color.equalsIgnoreCase("WOOD")){
            gl.glColor3f(0.6f, 0.4f, 0.2f);
        }else{
            gl.glColor3f(0.5f, 0.35f, 0.05f); // Brown color
        }
        // Draw chair seat

        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f((100 * scale) + x, (150 * scale) + y); //leftUpper
        gl.glVertex2f((180 * scale) + x, (150 * scale) + y); // rightUpper
        gl.glVertex2f((180 * scale) + x, (180 * scale) + y); // rightDown
        gl.glVertex2f((100 * scale) + x, (180 * scale) + y); // leftDown
        gl.glEnd();

        // Draw chair back
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f((100 * scale) + x, (100 * scale) + y);
        gl.glVertex2f((130 * scale) + x, (100 * scale) + y);
        gl.glVertex2f((130 * scale) + x, (150 * scale) + y);
        gl.glVertex2f((100 * scale) + x, (150 * scale) + y);
        gl.glEnd();

        // Draw chair legs
        gl.glColor3f(0.3f, 0.3f, 0.3f); // Gray color
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f((110 * scale) + x, (180 * scale) + y); // Bottom
        gl.glVertex2f((120 * scale) + x, (180 * scale) + y);
        gl.glVertex2f((120 * scale) + x, (230 * scale) + y); // Top
        gl.glVertex2f((110 * scale) + x, (230 * scale) + y);

        gl.glVertex2f((160 * scale) + x, (180 * scale) + y); // Bottom
        gl.glVertex2f((170 * scale) + x, (180 * scale) + y);
        gl.glVertex2f((170 * scale) + x, (230 * scale) + y); // Top
        gl.glVertex2f((160 * scale) + x, (230 * scale) + y);
        gl.glEnd();
    }


    private void drawTable(GL2 gl, int x, int y, float scale , String color) {

        if(color.equalsIgnoreCase("BLACK")){
            gl.glColor3f(0.0f, 0.0f, 0.0f);
        }else if (color.equalsIgnoreCase("BROWN")){
            gl.glColor3f(0.5f, 0.35f, 0.05f);
        }else if (color.equalsIgnoreCase("BLUE")){
            gl.glColor3f(0.2f, 0.0f, 1.0f);
        }else if (color.equalsIgnoreCase("RED")){
            gl.glColor3f(1.0f, 0.0f, 0.0f);
        }else if (color.equalsIgnoreCase("WOOD")){
            gl.glColor3f(0.6f, 0.4f, 0.2f);
        }else{
            gl.glColor3f(0.6f, 0.4f, 0.2f); // Wood color
        }
        // Draw table top

        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f((100 * scale) + x, (400 * scale) + y); // leftUpper
        gl.glVertex2f((200 * scale) + x, (400 * scale) + y); // rightUpp
        gl.glVertex2f((200 * scale) + x, (430 * scale) + y); // rightDown
        gl.glVertex2f((100 * scale) + x, (430 * scale) + y); // leftDown
        gl.glEnd();

        // Draw table legs
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f((110 * scale) + x, (430 * scale) + y); // Bottom
        gl.glVertex2f((120 * scale) + x, (430 * scale) + y);
        gl.glVertex2f((120 * scale) + x, (470 * scale) + y); // Top
        gl.glVertex2f((110 * scale) + x, (470 * scale) + y);

        gl.glVertex2f((180 * scale) + x, (430 * scale) + y); // Bottom
        gl.glVertex2f((190 * scale) + x, (430 * scale) + y);
        gl.glVertex2f((190 * scale) + x, (470 * scale) + y); // Top
        gl.glVertex2f((180 * scale) + x, (470 * scale) + y);
        gl.glEnd();
    }


    private void drawCupboard(GL2 gl, int x, int y, float scale , String color) {


        // Draw cupboard body
        if(color.equalsIgnoreCase("BLACK")){
            gl.glColor3f(0.0f, 0.0f, 0.0f);
        }else if (color.equalsIgnoreCase("BROWN")){
            gl.glColor3f(0.5f, 0.35f, 0.05f);
        }else if (color.equalsIgnoreCase("BLUE")){
            gl.glColor3f(0.2f, 0.0f, 1.0f);
        }else if (color.equalsIgnoreCase("RED")){
            gl.glColor3f(1.0f, 0.0f, 0.0f);
        }else if (color.equalsIgnoreCase("WOOD")){
            gl.glColor3f(0.6f, 0.4f, 0.2f);
        }else{
            gl.glColor3f(0.2f, 0.3f, 0.5f); // Dark blue color
        }

        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f((150 * scale) + x, (30 * scale) + y); // leftUpper
        gl.glVertex2f((170 * scale) + x, (30 * scale) + y); // rightUpper
        gl.glVertex2f((170 * scale) + x, (50 * scale) + y); // rightDown
        gl.glVertex2f((150 * scale) + x, (50 * scale) + y); // leftDown
        gl.glEnd();

        // Draw cupboard door
        gl.glColor3f(0.7f, 0.7f, 0.7f); // Light gray color
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f((152 * scale) + x, (32 * scale) + y); // leftUpper
        gl.glVertex2f((168 * scale) + x, (32 * scale) + y); // rightUpper
        gl.glVertex2f((168 * scale) + x, (48 * scale) + y); // rightDown
        gl.glVertex2f((152 * scale) + x, (48 * scale) + y); // leftDown
        gl.glEnd();

        // Draw door handle
        gl.glColor3f(0.2f, 0.2f, 0.2f); // Dark gray color
        gl.glBegin(GL2.GL_TRIANGLE_FAN);
        gl.glVertex2f((166 * scale) + cupboardX, (40 * scale) + y); // Center of the circle
        for (int i = 0; i <= 360; i++) {
            double angle = Math.toRadians(i);
            double xCoord = (166 * scale) + cupboardX + Math.cos(angle) * (3 * scale / 2); // Radius of the circle is scaled
            double yCoord = (40 * scale) + y + Math.sin(angle) * (3 * scale / 2);
            gl.glVertex2d(xCoord, yCoord);
        }
        gl.glEnd();
    }





    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        // Check if the mouse is clicked on the chair
        if (x >= (100 * chairScale) + chairX && x <= (300 * chairScale) + chairX &&
                y >= (100 * chairScale) + chairY && y <= (300 * chairScale) + chairY) {
            isDraggingChair = true;
        }

        // Check if the mouse is clicked on the table
        if (x >= (100 * tableScale) + tableX && x <= (400 * tableScale) + tableX &&
                y >= (400 * tableScale) + tableY && y <= (750 * tableScale) + tableY) {
            isDraggingTable = true;
        }

        // Check if the mouse is clicked on the cupboard
        if (x >= ( 140 * cupboardScale) + cupboardX && x <= (180 * cupboardScale) + cupboardX &&
                y >= (20 * cupboardScale ) + cupboardY && y <= (60 * cupboardScale ) + cupboardY) {
            isDraggingCupboard = true;
        }

        prevX = x;
        prevY = y;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isDraggingChair = false;
        isDraggingTable = false;
        isDraggingCupboard = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        if (isDraggingChair) {

            if(x <= floorMargin + 105 || x >=  windowWidth - (floorMargin + 90) ){
                chairX = chairX;
            }else {
                chairX += x - prevX;
            }
//            if(x >= windowWidth - (floorMargin + 100)){
//                chairX = chairX;
//            }else{
//                chairX += x - prevX;
//            }
            if(y >= windowHeight - (floorMargin + 140) || y<=  (floorMargin + 140)){
                chairY = chairY;
            }
            else{
                chairY += y - prevY;
            }
        }

        if (isDraggingTable) {

            if(x <= floorMargin + 105 || x >=  windowWidth - (floorMargin + 90) ){
                tableX = tableX;
            }else {
                tableX += x - prevX;
            }
            if(y >= windowHeight - (floorMargin + 140) || y<=  (floorMargin + 140)){
                tableY = tableY;
            }
            else{
                tableY += y - prevY;
            }


        }

        if (isDraggingCupboard) {

            if(x <= floorMargin + 105 || x >=  windowWidth - (floorMargin + 90) ){
                cupboardX = cupboardX;
            }else {
                cupboardX += x - prevX;
            }
            if(y >= windowHeight - (floorMargin + 140) || y<=  (floorMargin + 140)){
                cupboardY = cupboardY;
            }
            else{
                cupboardY += y - prevY;
            }
        }

        prevX = x;
        prevY = y;

        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {}
}
