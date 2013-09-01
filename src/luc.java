
import processing.core.*;
import org.jbox2d.common.*;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class luc extends PApplet{
  PBox2D box2d;


int iScreenHeight =800;
int iScreenWidth = 600;
int iFrameRate = 40;
int iPixDefaultSize = 8;
int iPixDefaultHalfSize = round(iPixDefaultSize/2.f);
LWind oWind = new LWind(20.0f, PI/2, 0, 200, 600, 500,this);
LWind oWind2 = new LWind(2.0f, -PI/2, 200, 0, 600, 250,this);

LEarth oEarth = new LEarth(this);
float GTime = 0;
float GTimeStep = (float)1./(float)iFrameRate;

int defaultcolor = color(56,75,90);
ArrayList<LBody> arBody = new ArrayList<>();

LAll All = new LAll();

public void setup() 
{
  box2d = new PBox2D(this);
  box2d.createWorld();
  box2d.setGravity(0, 0);
  frameRate(iFrameRate);
  size(iScreenHeight, iScreenWidth); //228
  background(192, 64, 0);
  arBody.add(oWind);
  arBody.add(oEarth); 
   // box2d.world.setContactListener(new LTouchings());

  PImage  img = loadImage("krona.png");
  LForm TreeLeaves = new LForm(img, color(0, 0, 0),this);
  for (int i=0;i<TreeLeaves.arBits.length;i++ ) {
    LLeaf Leaf = new LLeaf(TreeLeaves.arBits[i].x, TreeLeaves.arBits[i].y,this);
    arBody.add(Leaf);
    Leaf.stability = randomGaussian()*3000+80000;//random(500,4000);
    Leaf.arBodiesAffect.add(oWind);
    Leaf.arBodiesAffect.add(oEarth);
  }
  
  
  PImage  imgWood = loadImage("stvol.png");
  LForm TreeWood = new LForm(imgWood, color(0, 0, 0),this);
  for (int i=0;i<TreeWood.arBits.length;i++ ) {
    LWood Wood = new LWood(TreeWood.arBits[i].x, TreeWood.arBits[i].y,this);
    Wood.stability = 500000;
    arBody.add(Wood);
    Wood.arBodiesAffect.add(oWind);
    Wood.arBodiesAffect.add(oEarth);
  }

  //arBody.get(15).stability = 15000;
  PImage  imgg = loadImage("ground2_filled.png");//"zemlya.png");
  LForm Grounds = new LForm(imgg, color(0, 0, 0),this);
  for (int i=0;i<Grounds.arBits.length;i++) {
    LGround NewGround = new LGround(Grounds.arBits[i].x, Grounds.arBits[i].y,this);
    arBody.add(NewGround);
  } 
  
  ArrayList <LCloud> Clouds = new ArrayList <LCloud>();
    PImage  imgCloud = loadImage("cloud_snake.png");
  LForm frmCloud = new LForm(imgCloud, color(0, 0, 0),this);
  for (int i=0;i<frmCloud.arBits.length;i++ ) {
    for(int j=0;j<random(1,5);j++){
          LCloud cloud = new LCloud(600 + frmCloud.arBits[i].x,10 + frmCloud.arBits[i].y,this);
          Clouds.add(cloud);
    }
  } 
  arBody.addAll(Clouds);
  LCloudSet CloudSet = new LCloudSet(Clouds,this);
  arBody.add(CloudSet);
            CloudSet.arBodiesAffect.add(oWind2);
             CloudSet.arBodiesAffect.add(oWind);
          //CloudSet.arBodiesAffect.add(oEarth);
  //arBody.add(Grounder);
  arBody.add(oWind2);
  oWind2.AngleDeviation = PI/12;
  oWind2.ForcePhase = PI/5;
  All.addBodies(arBody);
}

public void draw() {
 background(220, 207, 175);
 All.startIteration();
  while(All.hasNext()){
    LBody Body = All.getNext();
    Body.Display();
    Body.ApplyForce();
  }
    All.flush();
//println(activeIt);  
  //arBody.addAll(BodiesToAdd);
  box2d.step(GTimeStep, 5, 3);
  GTime=GTime+GTimeStep;
}

//LWind NewWind;
int prevMX=0;
int prevMY=0;
public void mouseClicked() {
  LWind NewWind = new LWind(40.0f, PI, mouseX - 50, mouseY - 50, 100, 100,this);
  /*for (LBody oBody: arBody) {   
    if(oBody instanceof LLeaf || oBody instanceof LCloud)  
      oBody.arBodiesAffect.add(NewWind);
  }
  arBody.add(NewWind);*/
  LWorm oWorm = new LWorm(mouseX,mouseY,this);
    All.addBody(oWorm);
}


public void keyPressed() 
{

  for (LBody oBody: arBody) {   
    if(oBody instanceof LBasicBody){
      
      LBasicBody BB = (LBasicBody)oBody;
      Vec2 pos = BB.getPosition();
      Vec2 normalize = new Vec2(pos.x/pos.length(),pos.y/pos.length());
      
      Vec2 vecForce = new Vec2(random(-3,3),random(-3,3));
      Vec2 vecImpulse = new Vec2(random(-10,10),random(-10,10));
      LDestructor dest = new LDestructor(vecForce, vecImpulse,this);
      BB.stability = -1;
      BB.arBodiesAffect.clear();
      BB.arBodiesAffect.add(dest);
      //((LBasicBody)oBody).PhBody.applyForceToCenter(new Vec2(random(-100,100),random(-100,100)));
    //((LBasicBody)oBody).PhBody.applyLinearImpulse(new Vec2(random(-10,10),random(-10,10)),((LBasicBody)oBody).PhBody.getWorldCenter());
    }
    else{
      
    }
  }
}
}
