// package application;

// import java.awt.Dimension;
// import java.awt.Toolkit;

// import com.sun.jna.Pointer;

// import static io.github.libsdl4j.api.Sdl.*;
// import static io.github.libsdl4j.api.SdlSubSystemConst.*;

// import io.github.libsdl4j.api.Sdl;
// import io.github.libsdl4j.api.event.*;
// import io.github.libsdl4j.api.event.SDL_Event.*;
// import io.github.libsdl4j.api.event.events.*;

// import static io.github.libsdl4j.api.error.SdlError.*;

// import static io.github.libsdl4j.api.event.SdlEvents.*;
// import static io.github.libsdl4j.api.event.SDL_EventType.*;

// import io.github.libsdl4j.api.render.SDL_Renderer;
// import static io.github.libsdl4j.api.render.SdlRender.*;
// import static io.github.libsdl4j.api.render.SDL_RendererFlags.*;

// import io.github.libsdl4j.api.video.*;
// import static io.github.libsdl4j.api.video.SdlVideoConst.*;
// import static io.github.libsdl4j.api.video.SdlVideo.*;
// import static io.github.libsdl4j.api.video.SDL_WindowFlags.*;
// import static io.github.libsdl4j.api.video.SDL_GLattr.*;

// import io.github.libsdl4j.api.stdinc.*;

// public class OldSDLDaemon extends Thread {

// public boolean gameLoop;
// public SDL_Window window;
// public SDL_Renderer renderer;
// public String error;
// public SDL_Event event = new SDL_Event();

// public OldSDLDaemon() {
// super("SDL Daemon");

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
// Pointer genBuffer = SDL_GL_GetProcAddress("glGenBuffers");

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
// }

// /**
// *
// */
// public void run() {
// // Updates what is being rendered.
// // SDL_RenderPresent(renderer);

// // Keyboard Inputs

// // Queues up the next event to deal with; 0 means no events queued
// while (AppConstants.gameLoop) {
// while (SDL_PollEvent(event) != 0) {
// switch (event.type) {
// case SDL_WINDOWEVENT:
// break;
// case SDL_KEYDOWN:
// AppConstants.gameLoop = false;
// break;
// case SDL_QUIT:
// AppConstants.gameLoop = false;
// break;

// default:
// // so if the event isn't stated here, it just "deals with it"™️
// break;
// }

// }
// }

// // Uninitializes everything
// SDL_Quit();
// }
// }