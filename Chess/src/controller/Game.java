package controller;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Scanner;

import model.Board;
import model.Fen;
import util.ImageUtil;
import view.CellManager;
import view.Window;


public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = -5505267217615912489L;
	
	private Thread thread;
	private boolean running = false;
	
	public static CellManager cellManager;
	public static Board board;
	
	public static final int WIDTH = 2*400, HEIGHT = 2*(400+50);
	
	
	public static void main(String[] args) {
		new Game();
	}
	
	public Game() {
		
		new Window(WIDTH, HEIGHT, "chess", this);
		
		this.addMouseListener(new KeyInput());
		
		board = new Board();
		
		new ImageUtil();
		
		//initializing board pieces
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", board);
		
	}
	
	public static void newGame() {
		board.clear();
		//initializing board pieces
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", board);
	}
	
	/**
     * Starts the game loop by creating and starting a new thread.
     */
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	/**
     * Stops the game loop by joining the thread.
     */
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * The main game loop responsible for updating and rendering the game.
     * 
     * !!!NOT MY METHOD!!!
     * 
     * I got this method from a youtube video a few years ago
     * I'm pretty sure it's the runtime method used in minecraft but 
     * I couldn't trace it back to anything
     * 
     * I can't provide a source for it but I am not claiming to have written it
     */
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				System.out.println("FPS: " + frames);
				timer += 1000;
				frames = 0;
			}
			
		}
		stop();
	}
	
	/**
     * Updates data in program
     */
	private void tick() {
		
	}
	
	/**
     * Renders each frame
     */
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		
		cellManager.render(g);
		
		g.dispose();
		bs.show();
	}
}
