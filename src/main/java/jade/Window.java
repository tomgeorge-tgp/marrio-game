package jade;

//import static java.lang.constant.ConstantDescs.NULL;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.openal.ALC10.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;
//Singleton class restricts the instantiation of a class and ensures that only one instance of the class exists in the Java Virtual Machine.
public class Window {
    private int width,height;
    private String title;

    private long glfwWindow;
    private static Window window =null;
    private Window(){
        this.width=1920;
        this.height=1080;
        this.title="Mario";
    }

    public static Window get(){
        if(Window.window == null)
        {
            Window.window=new Window();
        }
        return Window.window;

    }

    public void run(){
//        System.out.println("Hello LWjgl " + Runtime.Version.getVersion() +"!");
        System.out.println("Hello LWjgl " );
        init();
        loop();

        // Free the memory that we have allocated for the window
          glfwFreeCallbacks(glfwWindow);
          glfwDestroyWindow(glfwWindow);

        // Terminate GLFW and the free the error callback
          glfwTerminate();
          glfwSetErrorCallback(null).free();
    }
    public void init(){
        //Setup an error callback
//        System.err("some issue in init");
        GLFWErrorCallback.createPrint(System.err).set();//to print the error in console

        //Initialize GLFW
           if(!glfwInit())    //glfwInit to initilaize GLFW inbuild
            {
                throw new IllegalStateException("Unable to initialize GLFW.");
            }

        //Configure GLFW
            glfwDefaultWindowHints();  //like window size close button resize button etc
            glfwWindowHint(GLFW_VISIBLE,GLFW_FALSE);//to make the window visible only if the window is ready
            glfwWindowHint(GLFW_RESIZABLE,GLFW_TRUE);
            glfwWindowHint(GLFW_MAXIMIZED,GLFW_TRUE);//initially in max size

        //Create the window
            glfwWindow=glfwCreateWindow(this.width,this.height,this.title,NULL,NULL); //return the memeory address of the window
        //check if window is created
            if (glfwWindow == NULL)
                throw new IllegalStateException("Failed to create the GLFW window.");

        //Make the OpenGL context current
            glfwMakeContextCurrent(glfwWindow);

        //Enable v-sync for buffer swapping
            glfwSwapInterval(1);


        //Make the window visible
            glfwShowWindow(glfwWindow);


        //This line is critical for LWJGL's interoperation with GLFW's
       //OpenGL context,or any context that is managed externally.
       //LWGL detects the context that is current in the current thread
       //bindings available for use.
       GL.createCapabilities();
    }
    public void loop(){
        while (!glfwWindowShouldClose(glfwWindow))
        {
            //Poll events
              glfwPollEvents();
              glClearColor(1.0f,0.0f,0.0f,1.0f);  //to add red colour to the screen
              glClear(GL_COLOR_BUFFER_BIT);// this is to display the frame

              glfwSwapBuffers(glfwWindow);  //to swap frame the content to be displyed on the screen


        }
    }
}
