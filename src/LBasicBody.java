
import org.jbox2d.common.*;
import org.jbox2d.collision.shapes.*;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.contacts.*;


class LBasicBody extends LBody{
  LBasicBody(luc in_parent){super(in_parent);};
  float stability = 100000;
  
  Body PhBody;
  
  float rotter;
  float MaxRotter = 10000;
  
  LGround Rot;
  
  void Display(){
    parent.noStroke();
    parent.fill(parent.color(parent.defaultcolor));
    Vec2 vecPosition = getPosition();
    Vec2 vecSize = getSize();
    parent.rect(vecPosition.x ,vecPosition.y+vecSize.y*(1-rotter/MaxRotter),vecSize.x,vecSize.y);
}
  
   void CreateBody(BodyDef bd,FixtureDef fixtureDef,float pX, float pY){
    bd.fixedRotation = true;
    bd.position.set(parent.box2d.coordPixelsToWorld(pX+parent.iPixDefaultHalfSize,pY+parent.iPixDefaultHalfSize));
    PhBody = parent.box2d.createBody(bd);
    PolygonShape PhShape = new PolygonShape();
    float bW = parent.box2d.scalarPixelsToWorld(parent.iPixDefaultHalfSize);
    float bH = parent.box2d.scalarPixelsToWorld(parent.iPixDefaultHalfSize);
    PhShape.setAsBox(bW, bH);
    fixtureDef.shape = PhShape;
    MassData md = new MassData();
    md.mass = 1;
    md.center.setZero();
    PhBody.createFixture(fixtureDef);
    PhBody.setMassData(md);
  }
  
  Vec2 getPosition() {
        Fixture fd = PhBody.getFixtureList();
    
    Vec2 g = parent.box2d.coordWorldToPixels(PhBody.getPosition());
    Vec2 ret = g.sub(getSize().mul(0.5f));
    return ret;
  }
  
  Vec2 getSize() {
    Fixture fd = PhBody.getFixtureList();
    //if (fd == null) parent.println(this.getClass());
    PolygonShape thisSahpe =  (PolygonShape) fd.getShape(); 
    Vec2 v1 = parent.box2d.coordWorldToPixels(thisSahpe.getVertex(0));
    Vec2 v2 = parent.box2d.coordWorldToPixels(thisSahpe.getVertex(2));
    return new Vec2((v2.x-v1.x), (-v2.y+v1.y));
  }
  
  void setForcesFromBodiesAffect(){
    Vec2 SumForce = new Vec2(0,0);
    for (LBody oLForceBody: arBodiesAffect) {
      Vec2 f = oLForceBody.GetForce(getPosition()); 
      stability -= (f.length());
      SumForce.addLocal(f); 
      if (stability<0){ 
              PhBody.setActive(true);
        PhBody.applyForceToCenter(SumForce);}
    }
  }
  
  void setRotter(){
    ContactEdge ce = PhBody.getContactList(); 
    while(ce != null){
      Contact c = ce.contact;
      Fixture f1 = c.getFixtureA();
      Fixture f2 = c.getFixtureB();
      Object o1 = f1.getBody().getUserData();
      Object o2 = f2.getBody().getUserData();
      if(o2.getClass() == LGround.class || o1.getClass() == LGround.class ){
        rotter-=parent.GTimeStep;
        if(rotter <= 0){
          //ConstructGround = true;
          if(o2.getClass() == LGround.class)
            Rot = (LGround)o2;
          if(o1.getClass() == LGround.class)
            Rot = (LGround)o1;    
          Rot.rotterMass++;    
          parent.All.removeBody(this);
        }
        break;
      }
      ce = ce.next;
    }        
  }
  
  
    void destroy() {
      parent.box2d.destroyBody(PhBody);
    }
  
  
}
