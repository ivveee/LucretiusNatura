//package edu; 

import org.jbox2d.dynamics.*;


import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.contacts.Contact;

 class LTouchings implements ContactListener {


  // This function is called when a new collision occurs
   public void beginContact(Contact cp) {
    // Get both fixtures
    Fixture f1 = cp.getFixtureA();
    Fixture f2 = cp.getFixtureB();
    // Get both bodies
    Body b1 = f1.getBody();
    Body b2 = f2.getBody();
    // Get our objects that reference these bodies
    Object o1 = b1.getUserData();
    Object o2 = b2.getUserData();
    if (o1==null || o2==null) return;

    // Touchings of 
    if (o1.getClass() == LLeaf.class && o2.getClass() == LGround.class)
      DealLeafAndGround((LLeaf) o1, (LGround) o2);
    else if (o1.getClass() == LGround.class && o2.getClass() == LLeaf.class)
      DealLeafAndGround((LLeaf) o2, (LGround) o1);
    }

   public void endContact(Contact cp) {
    // Get both fixtures
    Fixture f1 = cp.getFixtureA();
    Fixture f2 = cp.getFixtureB();
    // Get both bodies
    Body b1 = f1.getBody();
    Body b2 = f2.getBody();
    // Get our objects that reference these bodies
    Object o1 = b1.getUserData();
    Object o2 = b2.getUserData();
    if (o1==null || o2==null || 1==1) return;
    // If object 1 is a Box, then object 2 must be a particle
    // Note we are ignoring particle on particle collisions
    if (o1.getClass() == LLeaf.class && o2.getClass() == LGround.class) {
      LLeaf leaf = (LLeaf)o1;
      //if(GTime > leaf.rotter) ;
        //leaf.ConstructGround = true;   
      //else 
        //leaf.tBeginRot = -1;
    } 
    if (o2.getClass() == LLeaf.class && o1.getClass() == LGround.class) {
      LLeaf leaf = (LLeaf)o2;
      //if(GTime > leaf.tBeginRot + leaf.rotter) 
      //  leaf.ConstructGround = true;   
      //else 
      //  leaf.tBeginRot = -1;
    } 
  }
  
  void DealLeafAndGround(LLeaf Leaf, LGround Ground){
      
    //if (Leaf.tBeginRot == -1) ;
        //Leaf.tBeginRot = GTime;
    //else
    
  }

   public void preSolve(Contact contact, Manifold oldManifold) {
    // TODO Auto-generated method stub
  }

   public void postSolve(Contact contact, ContactImpulse impulse) {
    // TODO Auto-generated method stub
  }
}


