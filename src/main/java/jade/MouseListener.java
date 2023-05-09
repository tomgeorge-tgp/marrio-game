package jade;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseListener {
    private static MouseListener instance;
    private double scrollX,scrollY;
    private double xPos,yPos,lastY,lastX;
    private boolean mouseButtonPressed[]=new boolean[3]; // we consider we have 3 mouse button
    private boolean isDragging;

    private MouseListener(){
        this.scrollX = 0.0;
        this.scrollY = 0.0;
        this.xPos = 0.0;
        this.yPos = 0.0;
        this.lastX = 0.0;
        this.lastY = 0.0;
    }

    //this is to create an object instance for the mouseListener
    public static MouseListener get() {
        if (MouseListener.instance == null) {
            MouseListener.instance = new MouseListener();
        }

        return MouseListener.instance;
    }

    //To get the x and y positon and dragging or not
    public static void mousePosCallback(long window, double xpos, double ypos) {
        if (!Window.getImguiLayer().getGameViewWindow().getWantCaptureMouse()) {
            clear();
        }
        if (get().mouseButtonDown > 0) {
            get().isDragging = true;
        }
        //to store the previous x and y positon of this instance to lastx and lasty
        get().lastX = get().xPos;
        get().lastY = get().yPos;
        //
        get().lastWorldX = get().worldX;
        get().lastWorldY = get().worldY;
        //to store the currrent x and y
        get().xPos = xpos;
        get().yPos = ypos;
    }
    //To check if there is any mouse buuton being clicked
    public static void mouseButtonCallback(long window, int button, int action, int mods) {
        if (action == GLFW_PRESS) {
//            get().mouseButtonDown++;

            if (button < get().mouseButtonPressed.length) {  //to check if the mouse button clicked is among 3 we considered
                get().mouseButtonPressed[button] = true;
            }
        } else if (action == GLFW_RELEASE) {
//            get().mouseButtonDown--;

            if (button < get().mouseButtonPressed.length) {
                get().mouseButtonPressed[button] = false;
                get().isDragging = false;
            }
        }
    }
    //To find  the offset of the x and y scroll
    public static void mouseScrollCallback(long window, double xOffset, double yOffset) {
        get().scrollX = xOffset;
        get().scrollY = yOffset;
    }

    //To find if we are the end of the frame
    public static void endFrame() {
        get().scrollY = 0.0;
        get().scrollX = 0.0;
        get().lastX=get().xPos;
        get().lastY=get().yPos;
    }

   public static float getX() {
        return (float)get().xPos;
   }
    public static float getY() {
        return (float)get().yPos;
    }
    public static float getDx() {
        return (float)(get().lastX-get().xPos);
    }

}
