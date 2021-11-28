package com.example.game101.Stage1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.game101.Mession1S1Activity;
import com.example.game101.gamepanel.CountBallon;
import com.example.game101.gamepanel.GameOver;
import com.example.game101.gamepanel.Joystick;
import com.example.game101.gamepanel.PauseGame;
import com.example.game101.gamepanel.Performance;
import com.example.game101.gamepanel.TimeGame;
import com.example.game101.objects.AnimationBombe;
import com.example.game101.objects.AnimationEnemy;
import com.example.game101.objects.AnimationPlayer;
import com.example.game101.objects.Backround;
import com.example.game101.objects.Ballon;
import com.example.game101.objects.Bombe;
import com.example.game101.objects.Circle;
import com.example.game101.objects.Enemy;
import com.example.game101.objects.EnemyProduction;
import com.example.game101.objects.Player;
import com.example.game101.objects.Rectangle;
import com.example.game101.objects.Spell;
import com.example.game101.sound.SoungBall;
import com.example.game101.utils.TestCollissionEnemy;
import com.example.game101.utils.TrajetEnemyUtils;
import com.example.game101.utils.Utils;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;


public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private TimeGame timeGame;
    private final Joystick joystick;
    private final Player player;

    private GameLoop gameLoop;
    private Context context;

    public List<Enemy> enemyList = new ArrayList<Enemy>();
    public List<Enemy> newEnemyList;
    public boolean isNewListEnemy = false;

    public List<EnemyProduction> enemyProductionList = new ArrayList<EnemyProduction>();
    public List<EnemyProduction> newEnemyProductionList;
    public boolean isNewListEnemyProduction = false;

    private List<Spell> spellList = new ArrayList<Spell>();

    private List<Ballon> ballonList = new ArrayList<Ballon>();

    private int joystickPointerId = 0;
    private int numberOfSpellsToCast = 0;
    private GameOver gameOver;
    private Performance performance;
    private GameDisplay gameDisplay;

    private Backround background;

    private List<Rectangle> rectangleList = new ArrayList<Rectangle>();
    private TestCollissionEnemy testCollissionEnemy;
    private CountBallon countBallon;

    public AnimationPlayer animationPlayer;
    public AnimationEnemy animationEnemy;

    // private VelocityPlayer velocityPlayer;
    private SoungBall soungBall;

    private PauseGame pauseGame;

    private boolean isRun = true;
    private Mession1S1Activity mainActivity;

    private AnimationBombe animationBombe;
    private Bombe bombe;


    public Game(Context context, int screenX, int screenY, Mession1S1Activity mainActivity) {
        super(context);

        this.mainActivity = mainActivity;

        animationBombe = new AnimationBombe(getResources());
        animationPlayer = new AnimationPlayer(getResources(), context);
        animationEnemy = new AnimationEnemy(getResources(), context);

        this.bombe = new Bombe(getResources(), context, this,animationBombe, 75f,250f);
        this.countBallon = new CountBallon(getResources(), context, screenX, screenY);
        this.pauseGame = new PauseGame(getResources(), context, screenX, screenY);

        this.background = new Backround(getResources());

        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        this.context = context;
        gameLoop = new GameLoop(this, surfaceHolder);

        performance = new Performance(context, gameLoop);
        gameOver = new GameOver(context, screenX, screenY);
        //Initialize game objects
        this.joystick = new Joystick(getResources(),250, screenY/2+100, 70, 40, screenX, screenY);

        rectangleList.add(new Rectangle(context, Color.argb(255,0,0,0),0,0,-700,-125,2100,-100));
        rectangleList.add(new Rectangle(context, Color.argb(255,0,0,0),0,0,-625,-175,-600,2100));
        rectangleList.add(new Rectangle(context, Color.argb(255,0,0,0),0,0,2000,-175,2025,2075));
        rectangleList.add(new Rectangle(context, Color.argb(255,0,0,0),0,0,-700,2000,2100,2025));
        rectangleList.add(new Rectangle(context, Color.argb(255,255,255,1),0,0,100,100,300,300));

        //  background = new Backround(screenX, screenY, getResources(),screenX * 0.2f,screenY * 0.35f);
        ballonList.add(new Ballon(context, animationPlayer, getResources(),50f,200f));
        ballonList.add(new Ballon(context, animationPlayer, getResources(),50f,1000f));
        ballonList.add(new Ballon(context, animationPlayer, getResources(),50f,1800f));

        ballonList.add(new Ballon(context, animationPlayer, getResources(),700f,200f));
        ballonList.add(new Ballon(context, animationPlayer, getResources(),700f,1000f));
        ballonList.add(new Ballon(context, animationPlayer, getResources(),700f,1800f));

        ballonList.add(new Ballon(context, animationPlayer, getResources(),1500f,200f));
        ballonList.add(new Ballon(context, animationPlayer, getResources(),1500f,1000f));
        ballonList.add(new Ballon(context, animationPlayer, getResources(),1500f,1800f));

        ballonList.add(new Ballon(context, animationPlayer, getResources(),1700f,1700f));


        player = new Player(animationPlayer, getResources(), context, this.joystick, 0, 0, 40, rectangleList, ballonList, screenX, screenY);

        this.timeGame = new TimeGame(context, screenX, screenY, player);

        //enemy = new Enemy(getContext(), player, 500,200,30);

        //this.velocityPlayer = new VelocityPlayer(context, this.player);

        //Initialise game display and center at around the player
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        gameDisplay = new GameDisplay(displayMetrics.widthPixels, displayMetrics.heightPixels, player);

      //  background = new Backround(screenX, screenY, getResources(),screenX * 0.2f,screenY * 0.35f);

        testCollissionEnemy = new TestCollissionEnemy(this,getResources(), getContext(), player, rectangleList );

        TrajetEnemyUtils.setRectangles(this.rectangleList);



        soungBall = new SoungBall(context);
        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction() & MotionEvent.ACTION_MASK;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:

                if(!isRun){

                    if(pauseGame.isInputPlay(event.getX(), event.getY())){
                        setPlay();
                    }else if(pauseGame.isInputReplay(event.getX(), event.getY())){
                        onRestart();
                    }else if(pauseGame.isInputHome(event.getX(), event.getY())){

                    }
                    return true;
                }else{

                    if(pauseGame.isInputPause(event.getX(), event.getY())){
                        setPause();
                        return true;
                    }

                    if (joystick.getIsPressed()) {
                        soungBall.playFire();
                        numberOfSpellsToCast++;
                    } else if (joystick.isPressed((double) event.getX(), (double) event.getY())) {
                        joystickPointerId = event.getPointerId(event.getActionIndex());
                        joystick.setIsPressed(true);
                    } else {
                        soungBall.playFire();
                        numberOfSpellsToCast++;
                    }
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if (joystick.getIsPressed()) {
                    joystick.setActuator((double) event.getX(), (double) event.getY());
                }
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if(joystickPointerId == event.getPointerId(event.getActionIndex())){
                    joystick.setIsPressed(false);
                    joystick.resetActuator();
                }

                return true;

        }
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if(gameLoop.getState().equals(Thread.State.TERMINATED)){
            gameLoop = new GameLoop(this, holder);
        }
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        this.background.draw(canvas, gameDisplay);
        try{
            for (int i=0; i<4; i++) {
                rectangleList.get(i).draw(canvas, gameDisplay);
            }
        }catch (ConcurrentModificationException e){
            e.printStackTrace();
        }
        //background.draw(canvas,gameDisplay);
        try{
            for (Ballon ballon : ballonList) {
                ballon.draw(canvas, gameDisplay);
            }
        }catch (ConcurrentModificationException e){
            e.printStackTrace();
        }
         //enemy.draw(canvas);

        try{
            for (Enemy enemy : enemyList) {
                enemy.draw(canvas, gameDisplay);
            }
        }catch (ConcurrentModificationException e){
            e.printStackTrace();
        }

        try{
            for (EnemyProduction enemy : enemyProductionList) {
                enemy.draw(canvas, gameDisplay);
            }
        }catch (ConcurrentModificationException e){
            e.printStackTrace();
        }

        try{
            for (Spell spell : spellList) {
                spell.draw(canvas, gameDisplay);
            }
        }catch (ConcurrentModificationException e){
            e.printStackTrace();
        }

        this.bombe.draw(canvas, gameDisplay);
        this.countBallon.draw(canvas);
        joystick.draw(canvas);
        player.draw(canvas, gameDisplay);
        timeGame.draw(canvas);
        pauseGame.draw(canvas);
        performance.draw(canvas);
        if(!isRun){
            gameOver.draw(canvas);
        }

    }

    public void update() {
        if(player.getHealthPoints() <= 0 || countBallon.somme == 10) {
            setPause();
        }

        if(!isRun){
            return;
        }
        joystick.update();
        player.update();
        while(numberOfSpellsToCast > 0){
            spellList.add(new Spell(getContext(), player, rectangleList, this));
            numberOfSpellsToCast--;
        }

        try{
            for (Enemy enemy : enemyList) {
                enemy.update();
            }
        }catch (ConcurrentModificationException e){
            e.printStackTrace();
        }

        try{
            Iterator<Spell> iteratorSpell = spellList.iterator();
            while (iteratorSpell.hasNext()) {
                Spell spell = iteratorSpell.next();
                spell.update();
            }
        }catch (ConcurrentModificationException e){
            e.printStackTrace();
        }

        Iterator<Ballon> iteratorBallon = ballonList.iterator();
        try{
            while (iteratorBallon.hasNext()) {
                Ballon ballon = iteratorBallon.next();
                if (Utils.getDistanceBetweenPoints(ballon.x, ballon.y, player.getPositionX(), player.getPositionY()) < 80) {
                    this.countBallon.somme++;
                    iteratorBallon.remove();
                }
            }
        }catch (ConcurrentModificationException e){
            e.printStackTrace();
        }
        try{
            Iterator<Enemy> iteratorEnemy = enemyList.iterator();
            while (iteratorEnemy.hasNext()) {
                Enemy enemy = iteratorEnemy.next();
                if (Circle.isColliding(enemy, player)) {
                    //player.setHealthPoints(enemy.grade);
                    iteratorEnemy.remove();

                    continue;
                }
                Iterator<Spell> iteratorSpell = spellList.iterator();
                while (iteratorSpell.hasNext()) {
                    Circle spell = iteratorSpell.next();
                    if (Circle.isColliding(spell, enemy)) {
                        iteratorSpell.remove();
                        iteratorEnemy.remove();
                        break;
                    }
                }

            }
        }catch (ConcurrentModificationException e){
            e.printStackTrace();
        }

        if(isNewListEnemy){
            enemyList = newEnemyList;
            isNewListEnemy = false;
        }

        if(isNewListEnemyProduction){
            enemyProductionList = newEnemyProductionList;
            isNewListEnemyProduction = false;
        }

        gameDisplay.update();


    }

    void setPause(){
        if(this.countBallon.somme == 10){
            gameOver.setWin();
        }else if(player.getHealthPoints() <= 0){
            gameOver.setGameOver();
        }
        this.pauseGame.isRun = false;
        this.isRun = false;
        testCollissionEnemy.isRun = false;
        timeGame.isRun = false;
        animationPlayer.isRun = false;



    }

    void setPlay(){
        this.pauseGame.isRun = true;
        this.isRun = true;
        testCollissionEnemy.isRun = true;
        timeGame.isRun=true;
        animationPlayer.isRun = true;

    }

    public void pause() {
        gameLoop.stopLoop();
    }

    private void onRestart(){
        background.recycleImages();
        animationPlayer.recycleImages();
        pauseGame.recycleImages();
        player.recycleImages();
        countBallon.recycleImages();
        Mession1S1Activity.restartActivity(mainActivity);
       //mainActivity.onRestartActivity();
    }

    public void deleteSpell(Spell spell){
        Iterator<Spell> iteratorSpell = spellList.iterator();
        while (iteratorSpell.hasNext()) {
            Spell spell2 = iteratorSpell.next();
            if (spell2 == spell) {
                iteratorSpell.remove();
                break;
            }
        }
    }
}
