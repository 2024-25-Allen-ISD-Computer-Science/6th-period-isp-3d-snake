// package application;

// import static io.github.libsdl4j.api.Sdl.SDL_Init;
// import static io.github.libsdl4j.api.Sdl.SDL_Quit;
// import static io.github.libsdl4j.api.SdlSubSystemConst.SDL_INIT_EVERYTHING;
// import static io.github.libsdl4j.api.error.SdlError.SDL_GetError;
// import static io.github.libsdl4j.api.event.SDL_EventType.SDL_KEYDOWN;
// import static io.github.libsdl4j.api.event.SDL_EventType.SDL_QUIT;
// import static io.github.libsdl4j.api.event.SDL_EventType.SDL_WINDOWEVENT;
// import static io.github.libsdl4j.api.event.SdlEvents.SDL_PollEvent;
// import static io.github.libsdl4j.api.video.SDL_GLattr.SDL_GL_BLUE_SIZE;
// import static io.github.libsdl4j.api.video.SDL_GLattr.SDL_GL_DEPTH_SIZE;
// import static io.github.libsdl4j.api.video.SDL_GLattr.SDL_GL_DOUBLEBUFFER;
// import static io.github.libsdl4j.api.video.SDL_GLattr.SDL_GL_GREEN_SIZE;
// import static io.github.libsdl4j.api.video.SDL_GLattr.SDL_GL_RED_SIZE;
// import static io.github.libsdl4j.api.video.SDL_WindowFlags.SDL_WINDOW_OPENGL;
// import static
// io.github.libsdl4j.api.video.SDL_WindowFlags.SDL_WINDOW_RESIZABLE;
// import static io.github.libsdl4j.api.video.SDL_WindowFlags.SDL_WINDOW_SHOWN;
// import static io.github.libsdl4j.api.video.SdlVideo.SDL_CreateWindow;
// import static io.github.libsdl4j.api.video.SdlVideo.SDL_GL_CreateContext;
// import static io.github.libsdl4j.api.video.SdlVideo.SDL_GL_LoadLibrary;
// import static io.github.libsdl4j.api.video.SdlVideo.SDL_GL_SetAttribute;
// import static
// io.github.libsdl4j.api.video.SdlVideoConst.SDL_WINDOWPOS_CENTERED;

// import java.awt.Dimension;
// import java.awt.Toolkit;

// import io.github.libsdl4j.api.event.SDL_Event;
// import io.github.libsdl4j.api.render.SDL_Renderer;
// import io.github.libsdl4j.api.video.SDL_GLContext;
// import io.github.libsdl4j.api.video.SDL_Window;

// public class SDL {

// public boolean gameLoop;
// public SDL_Window window;
// public SDL_Renderer renderer;
// public String error;

// public SDL() {
// // Initializing every input
// int resultCode = SDL_Init(SDL_INIT_EVERYTHING);
// if (resultCode != 0) {
// // 0 would mean a success, anything above 1 would be a fail
// error = SDL_GetError();
// throw new IllegalArgumentException(error + "\n'Couldn't initialize
// everything'\n~ Bozo the Clown");
// }

// //
// // Window
// //
// Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
// int width = (int) screen.getWidth();
// int height = (int) screen.getHeight();

// window = SDL_CreateWindow("3D Snake", SDL_WINDOWPOS_CENTERED,
// SDL_WINDOWPOS_CENTERED, width, height,
// SDL_WINDOW_RESIZABLE | SDL_WINDOW_SHOWN | SDL_WINDOW_OPENGL);
// if (window == null) {
// // if the window wasn't created, it returns as null
// error = SDL_GetError();
// throw new IllegalArgumentException(error + "\n'Couldn't create the window'\n~
// some bozo");
// }

// SDL_GLContext openGL = SDL_GL_CreateContext(window);
// if (openGL == null) {
// // if the context wasn't created, it returns as null
// error = SDL_GetError();
// throw new IllegalArgumentException(
// error + "\n'OpenGL used Die Bozo!'\n'It was super effective!'\n(OpenGl
// couldn't be created)");
// }

// int glAttributes = 0;
// // # of bits for respective processes.
// glAttributes += SDL_GL_SetAttribute(SDL_GL_RED_SIZE, 5);
// glAttributes += SDL_GL_SetAttribute(SDL_GL_BLUE_SIZE, 5);
// glAttributes += SDL_GL_SetAttribute(SDL_GL_GREEN_SIZE, 5);
// glAttributes += SDL_GL_SetAttribute(SDL_GL_DEPTH_SIZE, 16); // overlapping
// graphics
// glAttributes += SDL_GL_SetAttribute(SDL_GL_DOUBLEBUFFER, 1);
// if (glAttributes != 0) {
// error = SDL_GetError();
// throw new IllegalArgumentException(error
// + "\n'OpenGL used Clownin Around!'\n'It was super effective!'\n(OpenGl
// attributes had an error)");
// }

// // import OpenGL library
// int glLib = SDL_GL_LoadLibrary(null);
// if (glLib != 0) {
// error = SDL_GetError();
// throw new IllegalArgumentException(
// error + "\n'Ain't gonna lie, the graphics are bozos'\n(OpenGl attributes had
// an error)");
// }

// // lib test
// // Pointer genBuffer = SDL_GL_GetProcAddress("glGenBuffers");
// // GLfloat test = null;

// // GL test
// float[] v1 = {1f, 1f, 1f};
// float[] v2 = {0f, 0f, 0f};
// float[] v3 = {-1f, -1f, -1f};

// byte[] green = {0, (byte) 255, 0, (byte) 255};

// glBegin();

// // int openGlRenderer = SDL_GL_MakeCurrent(window, openGL);
// // if (openGlRenderer != 0) {
// // // if making the window OpenGl failed, it doesn't return as 0.
// // error = SDL_GetError();
// // throw new IllegalArgumentException(error
// // + "\n'OpenGL used Clownin Around!'\n'It was super effective!'\n(OpenGl
// // couldn't be synced with the window)");
// // }

// // // Creates the renderer (non opengl)
// // renderer = SDL_CreateRenderer(window, -1, SDL_RENDERER_PRESENTVSYNC);
// // if (renderer == null) {
// // // if the renderer wasn't created, it returns as null
// // error = SDL_GetError();
// // throw new IllegalArgumentException(error +
// // "\n'The renderer saw a bozo (you) and dipped.'\n(the renderer couldn't be
// // created)");
// // }

// // Render test
// // int renderTest = SDL_SetRenderDrawColor(renderer, (byte) 255, (byte) 0,
// // (byte) 0, (byte) 255);
// // if (renderTest != 0) {
// // // if the test failed, then it runs an error msg.
// // error = SDL_GetError();
// // throw new IllegalArgumentException(error + "\n'My bozo-sense is
// // tingling.'\n(the render test failed)");
// // }
// // SDL_RenderClear(renderer);
// // SDL_RenderPresent(renderer);

// // Event & Game Loop
// SDL_Event event = new SDL_Event();
// gameLoop = true;
// while (gameLoop) {
// // Updates what is being rendered.
// // SDL_RenderPresent(renderer);

// // Keyboard Inputs

// // Queues up the next event to deal with; 0 means no events queued
// while (SDL_PollEvent(event) != 0) {
// switch (event.type) {
// case SDL_WINDOWEVENT:
// break;
// case SDL_KEYDOWN:
// gameLoop = false;
// break;
// case SDL_QUIT:
// gameLoop = false;
// break;

// default:
// // so if the event isn't stated here, it just "deals with it"™️
// break;
// }

// }
// }

// // Quits the app
// SDL_Quit();
// }
// }

// /**
// * Daemon thread to run in the background and detect system events.
// */
// class SystemDaemon {
// public SystemDaemon() {

// }

// public void run() {

// }
// }